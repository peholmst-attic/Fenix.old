package net.pkhapps.fenix.communication.boundary.rest;

import net.pkhapps.fenix.communication.boundary.rest.dto.ContactDTO;
import net.pkhapps.fenix.communication.boundary.rest.dto.ContactDTOMapper;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactRepository;
import net.pkhapps.fenix.core.control.FireDepartmentRetriever;
import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * TODO Generalize
 */
@RestController
@RequestMapping("/rest/{fireDepartment}")
public class ContactsController {

    @Autowired
    FireDepartmentRetriever fireDepartmentRetriever;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactDTOMapper contactDTOMapper;

    @RequestMapping(value = "/contacts", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<ContactDTO> create(@PathVariable("fireDepartment") Long fireDepartmentId, @RequestBody ContactDTO dto) {
        Optional<FireDepartment> fireDepartment = fireDepartmentRetriever.getFireDepartmentIfPermitted(fireDepartmentId);
        if (fireDepartment.isPresent()) {
            Contact contact = contactDTOMapper.toEntity(dto);
            contact.setFireDepartment(fireDepartment.get());
            contact = contactRepository.saveAndFlush(contact);
            return new ResponseEntity<>(contactDTOMapper.toDTO(contact), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ResponseEntity<Page<ContactDTO>> retrieveAll(@PathVariable("fireDepartment") Long fireDepartmentId, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        Optional<FireDepartment> fireDepartment = fireDepartmentRetriever.getFireDepartmentIfPermitted(fireDepartmentId);
        if (fireDepartment.isPresent()) {
            return new ResponseEntity<>(contactDTOMapper.toDTOs(contactRepository.findByFireDepartment(fireDepartment.get(), new PageRequest(page.orElse(0), size.orElse(20)))), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/contacts/{contactId}", method = RequestMethod.GET)
    public ResponseEntity<ContactDTO> retrieveOne(@PathVariable("fireDepartment") Long fireDepartmentId, @PathVariable("contactId") Long contactId) {
        Optional<FireDepartment> fireDepartment = fireDepartmentRetriever.getFireDepartmentIfPermitted(fireDepartmentId);
        if (fireDepartment.isPresent()) {
            Contact contact = contactRepository.findByIdAndFireDepartment(contactId, fireDepartment.get());
            if (contact != null) {
                return new ResponseEntity<>(contactDTOMapper.toDTO(contact), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/contacts/{contactId}", method = RequestMethod.PUT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<ContactDTO> update(@PathVariable("fireDepartment") Long fireDepartmentId, @PathVariable("contactId") Long contactId, @RequestBody ContactDTO dto) {
        Optional<FireDepartment> fireDepartment = fireDepartmentRetriever.getFireDepartmentIfPermitted(fireDepartmentId);
        if (fireDepartment.isPresent()) {
            Contact contact = contactRepository.findByIdAndFireDepartment(contactId, fireDepartment.get());
            if (contact != null) {
                try {
                    contact = contactRepository.saveAndFlush(contactDTOMapper.toExistingEntity(dto, contact));
                    return new ResponseEntity<>(contactDTOMapper.toDTO(contact), HttpStatus.OK);
                } catch (OptimisticLockingFailureException ex) {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/contacts/{contactId}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<ContactDTO> delete(@PathVariable("fireDepartment") Long fireDepartmentId, @PathVariable("contactId") Long contactId) {
        Optional<FireDepartment> fireDepartment = fireDepartmentRetriever.getFireDepartmentIfPermitted(fireDepartmentId);
        if (fireDepartment.isPresent()) {
            Contact contact = contactRepository.findByIdAndFireDepartment(contactId, fireDepartment.get());
            if (contact != null) {
                contactRepository.delete(contact);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
