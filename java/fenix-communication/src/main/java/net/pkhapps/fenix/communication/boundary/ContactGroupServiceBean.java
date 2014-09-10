package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.entity.ContactGroup;
import net.pkhapps.fenix.communication.entity.ContactGroupRepository;
import net.pkhapps.fenix.core.boundary.AbstractFireDepartmentSpecificCrudService;
import net.pkhapps.fenix.core.security.SessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;

/**
 * Default implementation of {@link net.pkhapps.fenix.communication.boundary.ContactGroupService}.
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
class ContactGroupServiceBean extends AbstractFireDepartmentSpecificCrudService<ContactGroup, ContactGroupRepository> implements ContactGroupService {

    @Autowired
    ContactGroupServiceBean(Validator validator, ContactGroupRepository repository, ApplicationContext applicationContext, SessionInfo sessionInfo) {
        super(validator, repository, applicationContext, sessionInfo);
    }
}
