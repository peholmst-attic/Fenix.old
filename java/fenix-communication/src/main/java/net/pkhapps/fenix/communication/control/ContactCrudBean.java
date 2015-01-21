package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactRepository;
import net.pkhapps.fenix.core.annotation.Control;
import net.pkhapps.fenix.core.control.AbstractFireDepartmentSpecificCrud;
import net.pkhapps.fenix.core.security.SessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.Validator;

/**
 * Default implementation of {@link net.pkhapps.fenix.communication.control.ContactCrud}.
 */
@Control
class ContactCrudBean extends AbstractFireDepartmentSpecificCrud<Contact, ContactRepository> implements ContactCrud {

    @Autowired
    ContactCrudBean(Validator validator, ContactRepository repository, ApplicationContext applicationContext, SessionInfo sessionInfo) {
        super(validator, repository, applicationContext, sessionInfo);
    }
}
