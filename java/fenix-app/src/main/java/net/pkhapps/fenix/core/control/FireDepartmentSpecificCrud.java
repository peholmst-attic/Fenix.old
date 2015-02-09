package net.pkhapps.fenix.core.control;

import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;
import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * Extension of the {@link net.pkhapps.fenix.core.control.Crud} interface for entities extending {@link net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity}.
 */
public interface FireDepartmentSpecificCrud<E extends AbstractFireDepartmentSpecificEntity> extends Crud<E> {

    /**
     * Retrieves the entity with the specified ID that also belongs to the specified fire department.
     *
     * @param fireDepartment the fire department that the entity must belong to.
     * @param id             the ID of the entity to retrieve.
     * @return an {@code Optional} containing the entity if it exists, otherwise an empty {@code Optional}.
     */
    Optional<E> findOne(FireDepartment fireDepartment, Long id);

    /**
     * Finds all entities belonging to the specified fire department.
     *
     * @param fireDepartment the fire department that the entities must belong to.
     * @param sort           specification of how the returned list should be sorted.
     * @return a list of entities.
     */
    List<E> findAll(FireDepartment fireDepartment, Sort sort);

    /**
     * Finds all entities belonging to the specified fire department, returning a subset of the result as a page.
     *
     * @param fireDepartment the fire department that the entities must belong to.
     * @param pageable       the pageable object specifying which part of the result to return.
     * @return a page of entities.
     */
    Page<E> findAll(FireDepartment fireDepartment, Pageable pageable);
}
