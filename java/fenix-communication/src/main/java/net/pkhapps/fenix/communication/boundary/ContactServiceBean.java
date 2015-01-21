package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactRepository;
import net.pkhapps.fenix.core.boundary.AbstractFireDepartmentSpecificCrudService;
import net.pkhapps.fenix.core.security.SessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

/**
 * Default implementation of {@link net.pkhapps.fenix.communication.boundary.ContactService}.
 */
@Service
@Deprecated
class ContactServiceBean extends AbstractFireDepartmentSpecificCrudService<Contact, ContactRepository> implements ContactService {

    @Autowired
    ContactServiceBean(Validator validator, ContactRepository repository, ApplicationContext applicationContext, SessionInfo sessionInfo) {
        super(validator, repository, applicationContext, sessionInfo);
    }
}
