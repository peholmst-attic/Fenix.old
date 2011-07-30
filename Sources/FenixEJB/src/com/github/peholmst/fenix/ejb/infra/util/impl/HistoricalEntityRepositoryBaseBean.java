package com.github.peholmst.fenix.ejb.infra.util.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.github.peholmst.fenix.ejb.infra.util.HistoricalEntityRepository;
import com.github.peholmst.fenix.ejb.infra.util.HistoricalEntityRepositoryException;
import com.github.peholmst.fenix.ejb.infra.util.SequenceRepository;
import com.github.peholmst.fenix.entity.util.EntityBase;
import com.github.peholmst.fenix.entity.util.HistoricalEntityBase;
import com.github.peholmst.fenix.entity.util.HistoricalEntityBase_;
import com.github.peholmst.fenix.entity.util.HistoricalEntityReference;
import com.github.peholmst.fenix.entity.util.OrderInstruction;
import com.github.peholmst.fenix.entity.util.SequenceType;

public abstract class HistoricalEntityRepositoryBaseBean<E extends HistoricalEntityBase>
        extends RepositoryBaseBean<E> implements HistoricalEntityRepository<E> {

    // TODO Clean up this code to make it more readable!

    @EJB
    protected SequenceRepository sequenceRepository;

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public E save(final E entity) {
        entity.setCreatedBy(getCurrentUser());
        entity.setCreatedOn(new Date());
        entity.incrementRevision();
        if (entity.isPersistent()) {
            final E storedEntity = getEntityManager().find(getEntityClass(),
                    entity.getIdentifier());
            if (storedEntity != null) {
                if (!storedEntity.isCurrentVersion()) {
                    throw HistoricalEntityRepositoryException
                            .entityIsNotCurrentVersion(entity);
                }
                final E entityToArchive = EntityBase
                        .createFromExistingEntity(storedEntity);
                getEntityManager().persist(entityToArchive);
                return getEntityManager().merge(entity);
            } else {
                throw HistoricalEntityRepositoryException
                        .entityNotFound(entity);
            }
        } else {
            entity.setMasterId(sequenceRepository
                    .getNextValue(SequenceType.MASTER_ID));
            getEntityManager().persist(entity);
            return entity;
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void delete(final E entity) {
        if (isCurrentVersion(entity)) {
            entity.setDeleted(true);
            getEntityManager().merge(entity);
        } else {
            throw HistoricalEntityRepositoryException
                    .entityIsNotCurrentVersion(entity);
        }
    }

    protected boolean isCurrentVersion(final E entity) {
        final E storedEntity = getEntityManager().find(getEntityClass(),
                entity.getIdentifier());
        if (storedEntity != null) {
            return storedEntity.isCurrentVersion();
        } else {
            throw HistoricalEntityRepositoryException.entityNotFound(entity);
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public E findByIdentifier(final Long identifier) {
        return getEntityManager().find(getEntityClass(), identifier);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public List<E> findAll(final int returnMax,
            final OrderInstruction<E>... orderBy) {
        return findAll(returnMax, false, orderBy);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public List<HistoricalEntityReference<E>> getVersionsOfEntity(E entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public E findEntityFromReference(HistoricalEntityReference<E> reference) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public List<E> findAll(int returnMax, boolean includeDeleted,
            OrderInstruction<E>... orderBy) {
        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<E> queryDef = builder.createQuery(getEntityClass());
        final Root<E> entity = queryDef.from(getEntityClass());

        final PredicateBuilder restrictions = new PredicateBuilder();
        restrictions.add(builder.isTrue(entity
                .get(HistoricalEntityBase_.currentVersion)));

        if (!includeDeleted) {
            restrictions.add(builder.isFalse(entity
                    .get(HistoricalEntityBase_.deleted)));
        }

        queryDef.select(entity).where(restrictions.get())
                .orderBy(convertOrderInstructions(entity, orderBy));
        return executeQuery(queryDef, returnMax);
    }
}
