package net.pkhapps.fenix.core.boundary.rest.support;

import net.pkhapps.fenix.core.boundary.rest.dto.AbstractEntityDTO;
import net.pkhapps.fenix.core.boundary.rest.dto.AbstractEntityDTOMapper;
import net.pkhapps.fenix.core.control.FireDepartmentSpecificCrud;
import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;
import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.security.context.CurrentFireDepartment;
import net.pkhapps.fenix.core.validation.ConflictException;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Base class for a REST controller that provides CRUD operations for {@link net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity fire department specific} entities.
 * The controller uses a {@link CRUD} instance to perform the actual operations and an {@link net.pkhapps.fenix.core.boundary.rest.dto.AbstractEntityDTOMapper entity-DTO mapper} for
 * converting between DTOs and entities.
 * <p>
 * Please note that this controller requires the {@link net.pkhapps.fenix.core.security.context.CurrentFireDepartment} to be properly
 * populated before the methods are invoked.
 */
public abstract class AbstractFireDepartmentSpecificCrudController<E extends AbstractFireDepartmentSpecificEntity, DTO extends AbstractEntityDTO, CRUD extends FireDepartmentSpecificCrud<E>> {

    // TODO Document the methods

    public static final int DEFAULT_PAGE_SIZE = 20;

    private final CRUD crud;

    private final AbstractEntityDTOMapper<DTO, E> entityDTOMapper;

    private final CurrentFireDepartment currentFireDepartment;

    protected AbstractFireDepartmentSpecificCrudController(CRUD crud, AbstractEntityDTOMapper<DTO, E> entityDTOMapper, CurrentFireDepartment currentFireDepartment) {
        this.crud = crud;
        this.entityDTOMapper = entityDTOMapper;
        this.currentFireDepartment = currentFireDepartment;
    }

    /**
     * Return the default page size to use when no page size has been specified as a request parameter.
     *
     * @see #DEFAULT_PAGE_SIZE
     */
    protected int getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    }

    /**
     * Return the default sort to use when no sort has been specified as a request parameter.
     */
    protected Sort getDefaultSort() {
        return new Sort(AbstractEntity.PROP_ID);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<?> create(@RequestBody DTO dto) throws ConflictException, ValidationFailedException {
        final FireDepartment fireDepartment = currentFireDepartment.requireFireDepartment();
        E entity = entityDTOMapper.toEntity(dto);
        entity.setFireDepartment(fireDepartment);
        entity = crud.save(entity);
        return ResponseEntity.ok(entityDTOMapper.toDTO(entity));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> retrieveAll(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final FireDepartment fireDepartment = currentFireDepartment.requireFireDepartment();
        final Pageable pageRequest = new PageRequest(page.orElse(0), size.orElse(getDefaultPageSize()), getDefaultSort());
        final Page<E> entityPage = crud.findAll(fireDepartment, pageRequest);
        final Page<DTO> dtoPage = entityDTOMapper.toDTOs(entityPage);
        return ResponseEntity.ok(dtoPage);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<?> retrieveOne(@PathVariable("id") Long entityId) {
        final FireDepartment fireDepartment = currentFireDepartment.requireFireDepartment();
        final E entity = crud.findOne(fireDepartment, entityId).orElseThrow(() -> new NoSuchResourceException());
        final DTO dto = entityDTOMapper.toDTO(entity);
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<?> update(@PathVariable("id") Long entityId, @RequestBody DTO dto) throws ConflictException, ValidationFailedException {
        final FireDepartment fireDepartment = currentFireDepartment.requireFireDepartment();
        E entity = crud.findOne(fireDepartment, entityId).orElseThrow(() -> new NoSuchResourceException());
        entity = crud.save(entityDTOMapper.toExistingEntity(dto, entity));
        return ResponseEntity.ok(entityDTOMapper.toDTO(entity));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<?> delete(@PathVariable("id") Long entityId) {
        final FireDepartment fireDepartment = currentFireDepartment.requireFireDepartment();
        E entity = crud.findOne(fireDepartment, entityId).orElseThrow(() -> new NoSuchResourceException());
        crud.delete(entity);
        return ResponseEntity.ok().build();
    }
}
