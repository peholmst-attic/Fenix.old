package net.pkhapps.fenix.core.boundary;

import net.pkhapps.fenix.core.entity.AbstractEntity;

/**
 * Base interface for {@link net.pkhapps.fenix.core.boundary.CrudService} callbacks that are invoked by the
 * service on different occasions.
 */
public interface CrudServiceCallback {

    /**
     * Returns whether this callback is interested in this entity.
     */
    boolean supports(AbstractEntity entity);

    /**
     * Callback for delete operations.
     */
    public interface DeleteCallback<E extends AbstractEntity> extends CrudServiceCallback {
        void beforeDelete(E entity);

        void afterDelete(E entity);
    }

    /**
     * Callback for save operations.
     */
    public interface SaveCallback<E extends AbstractEntity> extends CrudServiceCallback {
        void beforeSave(E entity);

        void afterSave(E entity);
    }

}
