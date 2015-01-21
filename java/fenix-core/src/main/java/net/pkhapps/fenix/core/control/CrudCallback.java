package net.pkhapps.fenix.core.control;

import net.pkhapps.fenix.core.entity.AbstractEntity;

/**
 * Base interface for {@link Crud} callbacks that are invoked by the
 * service on different occasions.
 */
public interface CrudCallback {

    /**
     * Returns whether this callback is interested in this entity.
     */
    boolean supports(AbstractEntity entity);

    /**
     * Callback for delete operations.
     */
    public interface DeleteCallback<E extends AbstractEntity> extends CrudCallback {

        default void beforeDelete(E entity) {
        }

        default void afterDelete(E entity) {
        }
    }

    /**
     * Callback for save operations.
     */
    public interface SaveCallback<E extends AbstractEntity> extends CrudCallback {
        default void beforeSave(E entity) {
        }

        default void afterSave(E entity) {
        }
    }

}
