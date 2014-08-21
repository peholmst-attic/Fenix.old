package net.pkhapps.fenix.communication.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import net.pkhapps.fenix.communication.security.CommunicationAuthorities;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.DiscardButton;
import net.pkhapps.fenix.core.components.PrimaryButton;
import net.pkhapps.fenix.core.components.TinyLabel;
import net.pkhapps.fenix.core.components.ViewTitleLabel;
import net.pkhapps.fenix.core.i18n.MessageKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.FontAwesomeIcon;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

import javax.annotation.PostConstruct;

/**
 * View for creating and sending new messages.
 */
@VaadinView(name = NewMessageView.VIEW_NAME)
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "net.pkhapps.fenix.communication.sidebar.newMessage.caption", order = 0)
@FontAwesomeIcon(FontAwesome.ENVELOPE)
@PrototypeScope
@Secured(CommunicationAuthorities.SEND_MESSAGES)
public class NewMessageView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "communication/newMessage";

    private final MessageKeyGenerator messages = new MessageKeyGenerator(NewMessageView.class);

    @Autowired
    I18N i18n;

    private ViewTitleLabel title;
    private OptionGroup sendMessageAs;
    private TextArea messageText;
    private TinyLabel charsLeft;
    private TabSheet availableRecipientsTabs;
    private Button addReceipients;
    private Button removeRecipients;
    private Table selectedRecipients;
    private PrimaryButton send;
    private DiscardButton discard;

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        setSizeFull();

        title = new ViewTitleLabel(i18n.get(messages.key("title")));
        addComponent(title);

        sendMessageAs = new OptionGroup(i18n.get(messages.key("sendMessageAs.caption")));
        addComponent(sendMessageAs);
        sendMessageAs.setMultiSelect(true);
        // TODO Make this dynamic
        {
            sendMessageAs.addItem("SMS");
            sendMessageAs.addItem("E-mail");
        }

        messageText = new TextArea(i18n.get(messages.key("messageText.caption")));
        messageText.setWidth(100, Unit.PERCENTAGE);
        messageText.setHeight(80, Unit.PIXELS);
        addComponent(messageText);

        charsLeft = new TinyLabel(i18n.get(messages.key("charsLeft"), 0, 900));
        addComponent(charsLeft);
        setComponentAlignment(charsLeft, Alignment.TOP_RIGHT);

        final HorizontalLayout recipientsLayout = new HorizontalLayout();
        recipientsLayout.setSpacing(true);
        recipientsLayout.setSizeFull();
        addComponent(recipientsLayout);
        setExpandRatio(recipientsLayout, 1);

        availableRecipientsTabs = new TabSheet();
        availableRecipientsTabs.setHeight(100, Unit.PERCENTAGE);
        availableRecipientsTabs.setWidth(200, Unit.PIXELS);
        availableRecipientsTabs.addTab(createContactsTab(), i18n.get(messages.key("contacts.caption")), FontAwesome.USER);
        availableRecipientsTabs.addTab(createGroupsTab(), i18n.get(messages.key("groups.caption")), FontAwesome.USERS);
        recipientsLayout.addComponent(availableRecipientsTabs);

        final VerticalLayout recipientsButtonsLayout = new VerticalLayout();
        recipientsButtonsLayout.setWidth(150, Unit.PIXELS);
        recipientsButtonsLayout.setSpacing(true);
        recipientsLayout.addComponent(recipientsButtonsLayout);

        addReceipients = new Button(i18n.get(messages.key("addRecipients.caption")));
        addReceipients.setWidth(100, Unit.PERCENTAGE);
        recipientsButtonsLayout.addComponent(addReceipients);

        removeRecipients = new Button(i18n.get(messages.key("removeRecipients.caption")));
        removeRecipients.setWidth(100, Unit.PERCENTAGE);
        recipientsButtonsLayout.addComponent(removeRecipients);

        selectedRecipients = new Table(i18n.get(messages.key("selectedRecipients.caption")));
        selectedRecipients.setSizeFull();
        recipientsLayout.addComponent(selectedRecipients);
        recipientsLayout.setExpandRatio(selectedRecipients, 1);

        final HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSizeUndefined();
        buttonsLayout.setSpacing(true);
        addComponent(buttonsLayout);
        setComponentAlignment(buttonsLayout, Alignment.BOTTOM_RIGHT);

        send = new PrimaryButton(i18n.get(messages.key("send.caption")));
        buttonsLayout.addComponent(send);

        discard = new DiscardButton(i18n.get(messages.key("discard.caption")));
        buttonsLayout.addComponent(discard);
    }

    private Component createContactsTab() {
        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        layout.setSpacing(true);

        final TextField search = new TextField();
        search.setInputPrompt(i18n.get(messages.key("contacts.search.inputPrompt")));
        search.setWidth(100, Unit.PERCENTAGE);
        layout.addComponent(search);

        final Table contacts = new Table();
        contacts.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        contacts.setSizeFull();
        layout.addComponent(contacts);
        layout.setExpandRatio(contacts, 1);

        return layout;
    }

    private Component createGroupsTab() {
        return new Label("Groups");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
