package net.pkhapps.fenix.communication.boundary.rest;

import net.pkhapps.fenix.communication.boundary.rest.dto.ContactDTO;
import net.pkhapps.fenix.communication.boundary.rest.dto.ContactDTOMapper;
import net.pkhapps.fenix.communication.control.ContactCrud;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.core.boundary.rest.support.AbstractFireDepartmentSpecificCrudController;
import net.pkhapps.fenix.core.boundary.rest.support.Constants;
import net.pkhapps.fenix.core.security.UserRoles;
import net.pkhapps.fenix.core.security.context.CurrentFireDepartment;
import net.pkhapps.fenix.core.validation.ConflictException;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Rest controller for managing {@link net.pkhapps.fenix.communication.entity.Contact contacts}.
 */
@RestController
@RequestMapping(value = Constants.REST_URL_PREFIX + "*/contacts")
class ContactsController extends AbstractFireDepartmentSpecificCrudController<Contact, ContactDTO, ContactCrud> {

    @Autowired
    ContactsController(ContactCrud crud, ContactDTOMapper entityDTOMapper, CurrentFireDepartment currentFireDepartment) {
        super(crud, entityDTOMapper, currentFireDepartment);
    }

    @Override
    @Secured({UserRoles.ROLE_FD_ADMINISTRATOR, UserRoles.ROLE_FD_POWER_USER})
    public ResponseEntity<?> create(@RequestBody ContactDTO dto) throws ConflictException, ValidationFailedException {
        return super.create(dto);
    }

    @Override
    @Secured({UserRoles.ROLE_FD_ADMINISTRATOR, UserRoles.ROLE_FD_POWER_USER, UserRoles.ROLE_FD_USER})
    public ResponseEntity<?> retrieveAll(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        return super.retrieveAll(page, size);
    }

    @Override
    @Secured({UserRoles.ROLE_FD_ADMINISTRATOR, UserRoles.ROLE_FD_POWER_USER, UserRoles.ROLE_FD_USER})
    public ResponseEntity<?> retrieveOne(@PathVariable("id") Long entityId) {
        return super.retrieveOne(entityId);
    }

    @Override
    @Secured({UserRoles.ROLE_FD_ADMINISTRATOR, UserRoles.ROLE_FD_POWER_USER})
    public ResponseEntity<?> update(@PathVariable("id") Long entityId, @RequestBody ContactDTO dto) throws ConflictException, ValidationFailedException {
        return super.update(entityId, dto);
    }

    @Override
    @Secured({UserRoles.ROLE_FD_ADMINISTRATOR, UserRoles.ROLE_FD_POWER_USER})
    public ResponseEntity<?> delete(@PathVariable("id") Long entityId) {
        return super.delete(entityId);
    }
}