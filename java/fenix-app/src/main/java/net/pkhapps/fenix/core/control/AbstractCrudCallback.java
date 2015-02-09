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

    /**
     * Returns the class of the entities that this callback supports.
     */
    public Class<? extends AbstractEntity> getEntityClass() {
        return entityClass;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation supports all entities that are instances of the supported entity class.
     *
     * @see #getEntityClass()
     * @see #AbstractCrudCallback(Class)
     */
    @Override
    public boolean supports(AbstractEntity entity) {
        return entityClass.isInstance(entity);
    }
}
