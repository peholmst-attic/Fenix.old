package net.pkhapps.fenix.communication.ui;

import net.pkhapps.fenix.communication.boundary.ContactGroupService;
import net.pkhapps.fenix.communication.entity.ContactGroup;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.AbstractCrudPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Presenter that contains the logic of the {@link net.pkhapps.fenix.communication.ui.GroupsView}.
 */
@Component
@PrototypeScope
class GroupsPresenter extends AbstractCrudPresenter<ContactGroup, ContactGroupService, GroupsPresenter.ViewDelegate> {

    @Autowired
    GroupsPresenter(ContactGroupService service) {
        super(service);
    }

    @Override
    protected ContactGroup newEntity() {
        return new ContactGroup();
    }

    interface ViewDelegate extends AbstractCrudPresenter.ViewDelegate<ContactGroup> {
    }
}
