package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.security.CommunicationAuthorities;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.DangerousButton;
import net.pkhapps.fenix.core.components.FriendlyButton;
import net.pkhapps.fenix.core.components.ViewTitleLabel;
import net.pkhapps.fenix.core.i18n.MessageKeyGenerator;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.FontAwesomeIcon;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

import javax.annotation.PostConstruct;

/**
 * View for managing contacts.
 */
@VaadinView(name = ContactsView.VIEW_NAME)
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "net.pkhapps.fenix.communication.sidebar.contacts.caption", order = 20)
@FontAwesomeIcon(FontAwesome.USER)
@PrototypeScope
@Secured({CommunicationAuthorities.EDIT_CONTACTS_AND_GROUPS, CommunicationAuthorities.VIEW_CONTACTS_AND_GROUPS})
public class ContactsView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "communication/contacts";

    private static final MessageKeyGenerator messages = new MessageKeyGenerator(ContactsView.class);

    @Autowired
    I18N i18n;

    private ViewTitleLabel title;
    private Table contactsTable;
    private BeanItemContainer<Contact> contactsContainer;
    private FriendlyButton add;
    private Button toggleEdit;
    private DangerousButton delete;
    private ContactForm contactForm;

    @PostConstruct
    void init() {
        setSizeFull();

        title = new ViewTitleLabel(i18n.get(messages.key("title")));
        addComponent(title);

        final HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        splitPanel.setSizeFull();
        addComponent(splitPanel);
        setExpandRatio(splitPanel, 1);

        contactsContainer = new BeanItemContainer<>(Contact.class);
        contactsTable = new Table();
        contactsTable.setContainerDataSource(contactsContainer);
        contactsTable.setVisibleColumns(
                Contact.PROP_DISPLAY_NAME,
                Contact.PROP_CELL_PHONE_NUMBER,
                Contact.PROP_EMAIL,
                Contact.PROP_COMMUNICATION_METHODS);
        contactsTable.setColumnHeader(Contact.PROP_DISPLAY_NAME, i18n.get(messages.key("table.name")));
        contactsTable.setColumnHeader(Contact.PROP_CELL_PHONE_NUMBER, i18n.get(messages.key("table.cellPhone")));
        contactsTable.setColumnHeader(Contact.PROP_EMAIL, i18n.get(messages.key("table.email")));
        contactsTable.setColumnHeader(Contact.PROP_COMMUNICATION_METHODS, i18n.get(messages.key("table.communicationMethods")));
        contactsTable.setSizeFull();
        contactsTable.addStyleName(FenixTheme.TABLE_BORDERLESS);
        splitPanel.setFirstComponent(contactsTable);

        final VerticalLayout editorLayout = new VerticalLayout();
        editorLayout.setSizeFull();
        splitPanel.setSecondComponent(editorLayout);

        contactForm = new ContactForm(i18n);
        contactForm.setSizeFull();
        editorLayout.addComponent(contactForm);
        editorLayout.setExpandRatio(contactForm, 1);

        final HorizontalLayout editorButtons = new HorizontalLayout();
        editorButtons.setMargin(true);
        editorButtons.setSpacing(true);
        editorButtons.setWidth("100%");
        editorLayout.addComponent(editorButtons);

        add = new FriendlyButton(i18n.get(messages.key("add.caption")));
        editorButtons.addComponent(add);
        editorButtons.setExpandRatio(add, 1);

        toggleEdit = new Button(i18n.get(messages.key("edit.caption")));
        editorButtons.addComponent(toggleEdit);
        editorButtons.setComponentAlignment(toggleEdit, Alignment.BOTTOM_RIGHT);

        delete = new DangerousButton(i18n.get(messages.key("delete.caption")));
        editorButtons.addComponent(delete);
        editorButtons.setComponentAlignment(toggleEdit, Alignment.BOTTOM_RIGHT);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

}
