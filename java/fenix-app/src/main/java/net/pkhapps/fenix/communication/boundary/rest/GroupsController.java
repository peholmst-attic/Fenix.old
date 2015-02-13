package net.pkhapps.fenix.communication.boundary.rest;

import net.pkhapps.fenix.communication.boundary.rest.dto.GroupDTO;
import net.pkhapps.fenix.communication.boundary.rest.dto.GroupDTOMapper;
import net.pkhapps.fenix.communication.control.GroupCrud;
import net.pkhapps.fenix.communication.entity.Group;
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
 * Rest controller for managing {@link net.pkhapps.fenix.communication.entity.Group contact groups}.
 */
@RestController
@RequestMapping(value = Constants.REST_URL_PREFIX + "*/contact-groups")
class GroupsController extends AbstractFireDepartmentSpecificCrudController<Group, GroupDTO, GroupCrud> {

    @Autowired
    GroupsController(GroupCrud crud, GroupDTOMapper entityDTOMapper, CurrentFireDepartment currentFireDepartment) {
        super(crud, entityDTOMapper, currentFireDepartment);
    }

    @Override
    @Secured({UserRoles.ROLE_FD_ADMINISTRATOR, UserRoles.ROLE_FD_POWER_USER})
    public ResponseEntity<?> create(@RequestBody GroupDTO dto) throws ConflictException, ValidationFailedException {
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
    public ResponseEntity<?> update(@PathVariable("id") Long entityId, @RequestBody GroupDTO dto) throws ConflictException, ValidationFailedException {
        return super.update(entityId, dto);
    }

    @Override
    @Secured({UserRoles.ROLE_FD_ADMINISTRATOR, UserRoles.ROLE_FD_POWER_USER})
    public ResponseEntity<?> delete(@PathVariable("id") Long entityId) {
        return super.delete(entityId);
    }
}
