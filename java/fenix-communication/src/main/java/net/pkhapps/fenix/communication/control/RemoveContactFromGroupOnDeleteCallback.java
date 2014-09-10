package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactGroupRepository;
import net.pkhapps.fenix.core.boundary.AbstractCrudServiceCallback;
import net.pkhapps.fenix.core.boundary.CrudServiceCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Callback that removes a to be deleted {@link Contact} from all {@link net.pkhapps.fenix.communication.entity.ContactGroup}s it belongs to.
 */
@Component
@Transactional(propagation = Propagation.MANDATORY)
class RemoveContactFromGroupOnDeleteCallback extends AbstractCrudServiceCallback implements CrudServiceCallback.DeleteCallback<Contact> {

    private final ContactGroupRepository contactGroupRepository;

    @Autowired
    RemoveContactFromGroupOnDeleteCallback(ContactGroupRepository contactGroupRepository) {
        super(Contact.class);
        this.contactGroupRepository = contactGroupRepository;
    }

    @Override
    public void beforeDelete(Contact entity) {
        // TODO Implement beforeDelete()
        throw new UnsupportedOperationException("Implement beforeDelete()");
    }
}
