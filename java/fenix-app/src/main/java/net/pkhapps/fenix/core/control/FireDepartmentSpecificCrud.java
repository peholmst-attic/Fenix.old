package net.pkhapps.fenix.core.control;

import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;
import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Extension of the {@link net.pkhapps.fenix.core.control.Crud} interface for entities extending {@link net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity}.
 */
public interface FireDepartmentSpecificCrud<E extends AbstractFireDepartmentSpecificEntity> extends Crud<E> {

    /**
     * Finds all entities belonging to the specified fire department.
     *
     * @param sort specification of how the returned list should be sorted.
     * @return a list of entities.
     */
    List<E> findAll(FireDepartment fireDepartment, Sort sort);

    /**
     * Finds all entities belonging to the specified fire department, returning a subset of the result as a page.
     *
     * @param pageable the pageable object specifying which part of the result to return.
     * @return a page of entities.
     */
    Page<E> findAll(FireDepartment fireDepartment, Pageable pageable);
}
