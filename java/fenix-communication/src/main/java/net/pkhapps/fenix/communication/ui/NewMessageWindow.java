package net.pkhapps.fenix.communication.ui;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.core.components.AbstractWindow;
import net.pkhapps.fenix.core.components.CustomTwinColSelect;
import net.pkhapps.fenix.core.components.PrimaryButton;
import net.pkhapps.fenix.core.components.SmallLabel;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.PrototypeScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;

import javax.annotation.PostConstruct;

/**
 * Window for creating and sending new messages.
 */
@VaadinComponent
@PrototypeScope
class NewMessageWindow extends AbstractWindow<Object> {

    private CommunicationMethodField sendMessageAs;
    private TextArea messageText;
    private SmallLabel usedChars;
    private PrimaryButton send;
    private Button cancel;
    private CustomTwinColSelect<NewMessageModel.Recipient> recipients;

    @Autowired
    NewMessageWindow(I18N i18n) {
        super(i18n);
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

        recipients = new CustomTwinColSelect<>(CustomTwinColSelect.Direction.HORIZONTAL, NewMessageModel.Recipient.class);
        recipients.setCaption(getI18N().get(getMessages().key("recipients.caption")));
        recipients.setSizeFull();
        recipients.setItemIconPropertyId(NewMessageModel.Recipient.PROP_ICON);
        recipients.setItemCaptionPropertyId(NewMessageModel.Recipient.PROP_NAME);
        recipients.setFilterInputPrompt(getI18N().get(getMessages().key("recipients.search.inputPrompt")));
        recipients.setImmediate(true);
        root.addComponent(recipients);
        root.setExpandRatio(recipients, 1);

        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);
        root.addComponent(buttonsLayout);
        root.setComponentAlignment(buttonsLayout, Alignment.BOTTOM_RIGHT);

        send = new PrimaryButton(getI18N().get(getMessages().key("send.caption")));
        buttonsLayout.addComponent(send);

        cancel = new Button(getI18N().get(getMessages().key("cancel.caption")));
        cancel.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        buttonsLayout.addComponent(cancel);

        sendMessageAs.focus();
    }

    private void updateCharsLeft(FieldEvents.TextChangeEvent event) {
        int usedChars = messageText.getValue().length();
        if (event != null) {
            usedChars = event.getText().length();
        }
        this.usedChars.setValue(getI18N().get(getMessages().key("usedChars"), usedChars, messageText.getMaxLength()));
    }


/*
    @Override
    public void setAvailableRecipients(Collection<NewMessageModel.Recipient> recipients) {
        this.recipients.setItems(recipients);
    }

    @Override
    public void setMaxLength(int maxLength) {
        messageText.setMaxLength(maxLength);
        usedChars.setVisible(maxLength > -1);
        updateCharsLeft(null);
    }

    @Override
    public void showValidationErrors(ValidationFailedException exception) {
        // TODO Implement me!
    }

    @Override
    public void openWindow(Optional<Callback<NewMessagePresenter.CloseStatus>> callback) {
        super.openWindow(callback);
    }*/
}
