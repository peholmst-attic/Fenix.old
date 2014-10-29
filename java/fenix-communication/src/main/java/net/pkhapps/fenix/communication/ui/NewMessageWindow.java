package net.pkhapps.fenix.communication.ui;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.Resource;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.communication.boundary.ContactGroupService;
import net.pkhapps.fenix.communication.boundary.ContactService;
import net.pkhapps.fenix.communication.boundary.MessageSenderService;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactGroup;
import net.pkhapps.fenix.communication.entity.MessageReceipt;
import net.pkhapps.fenix.communication.entity.MessageReceiptCommunicationMethodStatus;
import net.pkhapps.fenix.communication.entity.Recipient;
import net.pkhapps.fenix.core.components.AbstractWindow;
import net.pkhapps.fenix.core.components.CustomTwinColSelect;
import net.pkhapps.fenix.core.components.PrimaryButton;
import net.pkhapps.fenix.core.components.SmallLabel;
import net.pkhapps.fenix.core.util.ButtonUtils;
import net.pkhapps.fenix.core.util.FutureUtils;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.PrototypeScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Window for creating and sending new messages.
 */
@VaadinComponent
@PrototypeScope
class NewMessageWindow extends AbstractWindow<MessageReceipt> {

    private final ContactService contactService;
    private final ContactGroupService contactGroupService;
    private final MessageSenderService messageSenderService;
    private CommunicationMethodField sendMessageAs;
    private TextArea messageText;
    private SmallLabel usedChars;
    private PrimaryButton send;
    private Button cancel;
    private CustomTwinColSelect<RecipientDescriptor> recipients;
    private ProgressBar sendingProgressBar;

    @Autowired
    NewMessageWindow(I18N i18n, ContactService contactService, ContactGroupService contactGroupService, MessageSenderService messageSenderService) {
        super(i18n);
        this.contactService = contactService;
        this.contactGroupService = contactGroupService;
        this.messageSenderService = messageSenderService;
    }

    @PostConstruct
    protected void init() {
        setWidth("700px");
        setHeight("600px");
        setModal(true);
        setResizable(false);
        center();

        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setSpacing(true);
        root.setMargin(true);
        setContent(root);

        Label title = new Label(getI18N().get(getMessages().key("title")));
        title.addStyleName(FenixTheme.LABEL_H2);
        root.addComponent(title);

        sendMessageAs = new CommunicationMethodField(getI18N().get(getMessages().key("sendMessageAs.caption")), getI18N());
        sendMessageAs.setMultiSelect(true);
        sendMessageAs.addStyleName(FenixTheme.OPTIONGROUP_HORIZONTAL);
        sendMessageAs.setImmediate(true);
        sendMessageAs.addValueChangeListener(e -> updateComponentStates());
        root.addComponent(sendMessageAs);

        messageText = new TextArea(getI18N().get(getMessages().key("messageText.caption")));
        messageText.setWidth(100, Sizeable.Unit.PERCENTAGE);
        messageText.setHeight(80, Unit.PIXELS);
        messageText.addTextChangeListener(this::updateCharsLeft);
        messageText.setImmediate(true);
        root.addComponent(messageText);

        usedChars = new SmallLabel();
        usedChars.setVisible(false);
        root.addComponent(usedChars);
        root.setComponentAlignment(usedChars, Alignment.TOP_RIGHT);

        recipients = new CustomTwinColSelect<>(RecipientDescriptor.class);
        recipients.setCaption(getI18N().get(getMessages().key("recipients.caption")));
        recipients.setSizeFull();
        recipients.setItemIconPropertyId(RecipientDescriptor.PROP_ICON);
        recipients.setItemCaptionPropertyId(RecipientDescriptor.PROP_NAME);
        recipients.setFilterInputPrompt(getI18N().get(getMessages().key("recipients.search.inputPrompt")));
        recipients.setImmediate(true);
        recipients.setItems(getRecipientDescriptors());
        recipients.addValueChangeListener(e -> updateComponentStates());
        root.addComponent(recipients);
        root.setExpandRatio(recipients, 1);


        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        root.addComponent(buttonsLayout);
        root.setComponentAlignment(buttonsLayout, Alignment.BOTTOM_RIGHT);

        sendingProgressBar = new ProgressBar();
        sendingProgressBar.setIndeterminate(true);
        sendingProgressBar.setVisible(false);
        buttonsLayout.addComponent(sendingProgressBar);

        send = new PrimaryButton(getI18N().get(getMessages().key("send.caption")));
        send.setDisableOnClick(true);
        send.addClickListener(ButtonUtils.toClickListener(this::sendAndClose));
        buttonsLayout.addComponent(send);

        cancel = new Button(getI18N().get(getMessages().key("cancel.caption")));
        cancel.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        cancel.addClickListener(ButtonUtils.toClickListener(this::closeWithoutSending));
        buttonsLayout.addComponent(cancel);

        sendMessageAs.focus();
        updateComponentStates();
    }

    private void updateCharsLeft(FieldEvents.TextChangeEvent event) {
        int usedChars = messageText.getValue().length();
        if (event != null) {
            usedChars = event.getText().length();
        }
        this.usedChars.setValue(getI18N().get(getMessages().key("usedChars"), usedChars, messageText.getMaxLength()));
    }

    private void updateComponentStates() {
        getCommunicationMethods()
                .stream()
                .filter(cm -> cm.getMessageMaxLength() > messageText.getMaxLength())
                .forEach(cm -> messageText.setMaxLength(cm.getMessageMaxLength()));
        usedChars.setVisible(messageText.getMaxLength() > -1);
        boolean canSend = getCommunicationMethods().size() > 0
                && recipients.getValue().size() > 0
                && messageText.getValue().length() > 0;

        // TODO Add info messages about why a message cannot be sent
        // TODO Update component states also when the text changes (before the value is changed)
        send.setEnabled(canSend);
    }

    @Override
    public void openWindow(UI ui, Optional<Callback<MessageReceipt>> callback) {
        super.openWindow(ui, callback);
    }

    private void closeWithoutSending() {
        close();
    }

    private void sendAndClose() {
        sendingProgressBar.setVisible(true);
        messageText.setEnabled(false);
        recipients.setEnabled(false);
        sendMessageAs.setEnabled(false);
        final Future<MessageReceipt> messageReceiptFuture = messageSenderService.sendMessage(getMessageText(), getRecipients(), getCommunicationMethods());
        FutureUtils.observe(messageReceiptFuture, value -> processMessageReceipt(value));
    }

    private void processMessageReceipt(MessageReceipt receipt) {
        getUI().access(() -> {
            showMessageReceipt(receipt);
            if (receipt.isSuccessful()) {
                closeWindow(receipt);
            } else {
                sendingProgressBar.setVisible(false);
                messageText.setEnabled(true);
                recipients.setEnabled(true);
                sendMessageAs.setEnabled(true);
                send.setEnabled(true);
            }
        });
    }

    private void showMessageReceipt(MessageReceipt messageReceipt) {
        StringBuilder description = new StringBuilder();
        int failCount = 0;
        int noReceipientsCount = 0;
        description.append(getI18N().get(getMessages().key("status.header")));
        for (MessageReceiptCommunicationMethodStatus status : messageReceipt.getStatus()) {
            description.append("</br><b>");
            description.append(getI18N().get(String.format("%s.%s.caption",
                    CommunicationMethod.class.getCanonicalName(), status.getCommunicationMethod().name())));
            description.append("</b>: ");
            description.append(getI18N().get(String.format("%s.%s.caption", MessageReceiptCommunicationMethodStatus.Code.class.getCanonicalName(),
                    status.getCode().name())));
            if (status.getCode() == MessageReceiptCommunicationMethodStatus.Code.FAILED) {
                ++failCount;
                status.getAdditionalInfo().ifPresent(s -> description.append(String.format(" (%s)", s)));
            } else if (status.getCode() == MessageReceiptCommunicationMethodStatus.Code.NO_RECIPIENTS) {
                ++noReceipientsCount;
            }
        }

        String title = getI18N().get(getMessages().key("status.title.errors"));
        String styleName = FenixTheme.NOTIFICATION_FAILURE;
        int delayMsec = -1;
        if (failCount == 0) {
            title = getI18N().get(getMessages().key("status.title.successful"));
            styleName = FenixTheme.NOTIFICATION_SUCCESS;
            if (noReceipientsCount == 0) {
                delayMsec = 1000;
            }
        }

        Notification notification = new Notification(title, description.toString(), Notification.Type.HUMANIZED_MESSAGE, true);
        notification.setStyleName(styleName);
        notification.setDelayMsec(delayMsec);
        notification.show(Page.getCurrent());
    }

    private String getMessageText() {
        return messageText.getValue();
    }

    @SuppressWarnings("unchecked")
    private Collection<Recipient> getRecipients() {
        Set<RecipientDescriptor> recipients = this.recipients.getValue();
        return recipients.stream().map(RecipientDescriptor::getRecipient).collect(Collectors.toSet());
    }

    @SuppressWarnings("unchecked")
    private Collection<CommunicationMethod> getCommunicationMethods() {
        return (Collection<CommunicationMethod>) sendMessageAs.getValue();
    }

    private Collection<RecipientDescriptor> getRecipientDescriptors() {
        List<Contact> contacts = contactService.findAll();
        List<ContactGroup> groups = contactGroupService.findAll();
        List<RecipientDescriptor> recipientDescriptors = new ArrayList<>(contacts.size() + groups.size());
        contacts.forEach(c -> recipientDescriptors.add(new ContactRecipientDescriptor(c)));
        groups.forEach(g -> recipientDescriptors.add(new GroupRecipientDescriptor(g)));
        return recipientDescriptors;
    }

    public abstract class RecipientDescriptor implements Serializable {

        public static final String PROP_NAME = "name";
        public static final String PROP_ICON = "icon";

        private final Recipient recipient;

        protected RecipientDescriptor(Recipient recipient) {
            this.recipient = recipient;
        }

        public String getName() {
            return getRecipient().getRecipientName();
        }

        public abstract Resource getIcon();

        public Recipient getRecipient() {
            return recipient;
        }
    }

    public class ContactRecipientDescriptor extends RecipientDescriptor {

        protected ContactRecipientDescriptor(Contact recipient) {
            super(recipient);
        }

        @Override
        public Resource getIcon() {
            return FontAwesome.USER;
        }
    }

    public class GroupRecipientDescriptor extends RecipientDescriptor {

        protected GroupRecipientDescriptor(ContactGroup recipient) {
            super(recipient);
        }

        @Override
        public Resource getIcon() {
            return FontAwesome.USERS;
        }
    }
}
