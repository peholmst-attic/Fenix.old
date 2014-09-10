package net.pkhapps.fenix.core.boundary;

import net.pkhapps.fenix.core.entity.AbstractEntity;

/**
 * Base class for implementations of {@link net.pkhapps.fenix.core.boundary.CrudServiceCallback} and its subinterfaces.
 */
public abstract class AbstractCrudServiceCallback implements CrudServiceCallback {

    private final Class<? extends AbstractEntity> entityClass;

    protected AbstractCrudServiceCallback(Class<? extends AbstractEntity> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public boolean supports(AbstractEntity entity) {
        return entityClass.isInstance(entity);
    }
}
