package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import net.pkhapps.fenix.communication.boundary.ContactService;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.core.components.AbstractCrudView;
import net.pkhapps.fenix.core.components.AbstractEntityWindow;
import net.pkhapps.fenix.core.components.ConfirmationWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.vaadin.spring.PrototypeScope;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.FontAwesomeIcon;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

import java.util.Optional;

/**
 * View for managing contacts.
 */
@VaadinView(name = ContactsView.VIEW_NAME)
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "net.pkhapps.fenix.communication.sidebar.contacts.caption", order = 20)
@FontAwesomeIcon(FontAwesome.USER)
@PrototypeScope
class ContactsView extends AbstractCrudView<ContactService, Contact> {

    public static final String VIEW_NAME = "communication/contacts";

    private Table table;

    @Autowired
    public ContactsView(I18N i18n, ApplicationContext applicationContext, ContactService service) {
        super(i18n, applicationContext, service);
    }

    @Override
    protected Component createEntityListing(BeanItemContainer<Contact> container) {
        table = new Table();
        table.setContainerDataSource(container);
        table.setSelectable(true);
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
        table.addValueChangeListener(event -> selectionChanged());
        return table;
    }

    @Override
    protected ConfirmationWindow createConfirmDeleteWindow(Contact entity) {
        ConfirmationWindow window = super.createConfirmDeleteWindow(entity);
        window.setMessage(getI18N().get(getMessages().key("confirmDelete.message"), entity.getDisplayName()));
        window.setConfirmButtonCaption(getI18N().get(getMessages().key("confirmDelete.confirm.caption")));
        return window;
    }

    @Override
    protected Optional<Contact> getSelection() {
        return Optional.ofNullable((Contact) table.getValue());
    }

    @Override
    protected void setSelection(Optional<Contact> selection) {
        table.setValue(selection.orElse(null));
    }

    @Override
    protected Class<Contact> getEntityClass() {
        return Contact.class;
    }

    @Override
    protected void sortEntityListing() {
        table.sort();
    }

    @Override
    protected Class<? extends AbstractEntityWindow<ContactService, Contact>> getEntityWindowClass() {
        return EditContactWindow.class;
    }
}
