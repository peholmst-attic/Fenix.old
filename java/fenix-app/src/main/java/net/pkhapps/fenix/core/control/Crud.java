package net.pkhapps.fenix.core.control;

import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.validation.ConflictException;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Control interface for CRUD operations.
 */
public interface Crud<E extends AbstractEntity> {

    /**
     * Saves the specified entity.
     *
     * @param entity the entity to save.
     * @return the saved entity (may or may not be the same instance as the passed in entity).
     * @throws net.pkhapps.fenix.core.validation.ValidationFailedException if the entity failed validation prior to saving.
     * @throws net.pkhapps.fenix.core.validation.ConflictException         if the entity has been modified by another user.
     */
    E save(E entity) throws ValidationFailedException, ConflictException;

    /**
     * Deletes the specified entity without checking for optimistic locking. If the entity does not exist,
     * nothing happens.
     *
     * @param entity the entity to delete.
     */
    void delete(E entity);

    /**
     * Finds all entities.
     *
     * @param sort specification of how the returned list should be sorted.
     * @return a list of entities.
     */
    List<E> findAll(Sort sort);

    /**
     * Finds all entities, returning a subset of the result as a page.
     *
     * @param pageable the pageable object specifying which part of the result to return.
     * @return a page of entities.
     */
    Page<E> findAll(Pageable pageable);
}
