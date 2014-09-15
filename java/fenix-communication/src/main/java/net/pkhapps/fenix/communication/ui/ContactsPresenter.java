package net.pkhapps.fenix.communication.ui;

import net.pkhapps.fenix.communication.boundary.ContactService;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.AbstractCrudPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Presenter that contains the logic of the {@link net.pkhapps.fenix.communication.ui.ContactsView}.
 */
@Component
@PrototypeScope
class ContactsPresenter extends AbstractCrudPresenter<Contact, ContactService, ContactsPresenter.ViewDelegate> {

    @Autowired
    ContactsPresenter(ContactService service) {
        super(service);
    }

    @Override
    protected Contact newEntity() {
        return new Contact();
    }

    interface ViewDelegate extends AbstractCrudPresenter.ViewDelegate<Contact> {
    }

}
