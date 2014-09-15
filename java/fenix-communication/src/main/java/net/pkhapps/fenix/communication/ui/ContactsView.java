package net.pkhapps.fenix.communication.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Table;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.security.CommunicationAuthorities;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.AbstractCrudView;
import net.pkhapps.fenix.core.components.AbstractForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.FontAwesomeIcon;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

/**
 * View for managing contacts.
 */
@VaadinView(name = ContactsView.VIEW_NAME)
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "net.pkhapps.fenix.communication.sidebar.contacts.caption", order = 20)
@FontAwesomeIcon(FontAwesome.USER)
@PrototypeScope
@Secured({CommunicationAuthorities.EDIT_CONTACTS_AND_GROUPS, CommunicationAuthorities.VIEW_CONTACTS_AND_GROUPS})
public class ContactsView extends AbstractCrudView<Contact, ContactsPresenter.ViewDelegate, ContactsPresenter> implements ContactsPresenter.ViewDelegate {

    public static final String VIEW_NAME = "communication/contacts";

    @Autowired
    protected ContactsView(ContactsPresenter presenter, I18N i18n, ApplicationContext applicationContext) {
        super(presenter, i18n, applicationContext);
    }

    @Override
    protected void setUpTable(Table table) {
        table.setVisibleColumns(
                Contact.PROP_DISPLAY_NAME,
                Contact.PROP_CELL_PHONE_NUMBER,
                Contact.PROP_EMAIL,
                Contact.PROP_COMMUNICATION_METHODS);
        table.setColumnHeader(Contact.PROP_DISPLAY_NAME, getI18N().get(getMessages().key("table.name")));
        table.setColumnHeader(Contact.PROP_CELL_PHONE_NUMBER, getI18N().get(getMessages().key("table.cellPhone")));
        table.setColumnHeader(Contact.PROP_EMAIL, getI18N().get(getMessages().key("table.email")));
        table.setColumnHeader(Contact.PROP_COMMUNICATION_METHODS, getI18N().get(getMessages().key("table.communicationMethods")));
        table.setConverter(Contact.PROP_COMMUNICATION_METHODS, new CommunicationMethodConverter(getI18N()));
        table.setSortContainerPropertyId(Contact.PROP_DISPLAY_NAME);
    }

    @Override
    protected Class<? extends AbstractForm<Contact>> getFormClass() {
        return ContactForm.class;
    }

    @Override
    protected Class<Contact> getEntityClass() {
        return Contact.class;
    }
}
