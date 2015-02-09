package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactRepository;
import net.pkhapps.fenix.core.annotation.Control;
import net.pkhapps.fenix.core.control.AbstractCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.Validator;

/**
 * Default implementation of {@link net.pkhapps.fenix.communication.control.ContactCrud}.
 */
@Control
class ContactCrudImpl extends AbstractCrud<Contact, ContactRepository> implements ContactCrud {

    @Autowired
    ContactCrudImpl(Validator validator, ContactRepository repository, ApplicationContext applicationContext) {
        super(validator, repository, applicationContext);
    }
}
