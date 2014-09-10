package net.pkhapps.fenix.core.boundary;

import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.List;

/**
 * Service interface for CRUD operations.
 */
public interface CrudService<E extends AbstractEntity> {

    /**
     * Saves the specified entity.
     *
     * @param entity the entity to save.
     * @return the saved entity (may or may not be the same instance as the passed in entity).
     * @throws ValidationFailedException         if the entity failed validation prior to saving.
     * @throws OptimisticLockingFailureException if the entity has been modified by another user.
     */
    E save(E entity) throws ValidationFailedException, OptimisticLockingFailureException;

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
     * @return a list of entities.
     */
    List<E> findAll();
}
