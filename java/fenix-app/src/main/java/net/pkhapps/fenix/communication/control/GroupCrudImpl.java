package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.Group;
import net.pkhapps.fenix.communication.entity.GroupRepository;
import net.pkhapps.fenix.core.annotation.Control;
import net.pkhapps.fenix.core.control.AbstractFireDepartmentSpecificCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.Validator;

/**
 * Default implementation of {@link net.pkhapps.fenix.communication.control.GroupCrud}.
 */
@Control
class GroupCrudImpl extends AbstractFireDepartmentSpecificCrud<Group, GroupRepository> implements GroupCrud {

    @Autowired
    GroupCrudImpl(Validator validator, GroupRepository repository, ApplicationContext applicationContext) {
        super(validator, repository, applicationContext);
    }
}
