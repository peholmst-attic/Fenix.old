package com.github.peholmst.fenix.entity.util;

import javax.persistence.EntityTransaction;

import org.junit.Test;

public abstract class EntityBaseTestCase<E extends EntityBase> extends
        EntityManagerTestCase {

    protected abstract Class<E> getEntityClass();

    protected abstract void populateTransientEntityWithData(E entity);

    protected E createEntityToPersist() {
        return EntityBase.createEntity(getEntityClass());
    }

    @Test
    public void persist() {
        final E entityToPersist = createEntityToPersist();
        populateTransientEntityWithData(entityToPersist);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(entityToPersist);
        tx.commit();
    }

}
