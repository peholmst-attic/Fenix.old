package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactRepository;
import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.security.SessionInfo;
import net.pkhapps.fenix.core.security.WrongFireDepartmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Default implementation of {@link net.pkhapps.fenix.communication.boundary.ContactService}.
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
class ContactServiceBean implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    SessionInfo sessionInfo;

    @Override
    public Contact save(Contact contact) {
        final FireDepartment currentFireDepartment = sessionInfo.getCurrentFireDepartment();
        if (contact.getFireDepartment() == null) {
            contact.setFireDepartment(currentFireDepartment);
        } else if (!currentFireDepartment.equals(contact.getFireDepartment())) {
            throw new WrongFireDepartmentException();
        }
        return contactRepository.saveAndFlush(contact);
    }

    @Override
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findByFireDepartment(sessionInfo.getCurrentFireDepartment());
    }
}
