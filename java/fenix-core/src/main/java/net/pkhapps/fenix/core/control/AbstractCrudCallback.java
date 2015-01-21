package net.pkhapps.fenix.core.control;

import net.pkhapps.fenix.core.entity.AbstractEntity;

/**
 * Base class for implementations of {@link net.pkhapps.fenix.core.control.CrudCallback} and its subinterfaces.
 */
public abstract class AbstractCrudCallback implements CrudCallback {

    private final Class<? extends AbstractEntity> entityClass;

    protected AbstractCrudCallback(Class<? extends AbstractEntity> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public boolean supports(AbstractEntity entity) {
        return entityClass.isInstance(entity);
    }
}
