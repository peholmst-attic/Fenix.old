package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.GroupRepository;
import net.pkhapps.fenix.core.annotation.Control;
import net.pkhapps.fenix.core.control.AbstractCrudCallback;
import net.pkhapps.fenix.core.control.CrudCallback;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@link net.pkhapps.fenix.core.control.CrudCallback} that removes a contact from all the groups it is a member of
 * before it is deleted.
 */
@Control
class GroupMemberRemovalCallback extends AbstractCrudCallback implements CrudCallback.DeleteCallback<Contact> {

    private final GroupRepository groupRepository;

    @Autowired
    GroupMemberRemovalCallback(GroupRepository groupRepository) {
        super(Contact.class);
        this.groupRepository = groupRepository;
    }

    @Override
    public void beforeDelete(Contact entity) {
        // TODO Implement me!
        throw new UnsupportedOperationException("Implement me!");
    }
}
