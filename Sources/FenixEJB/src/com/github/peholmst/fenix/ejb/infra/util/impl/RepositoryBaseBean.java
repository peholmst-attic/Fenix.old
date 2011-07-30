package com.github.peholmst.fenix.ejb.infra.util.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.github.peholmst.fenix.entity.util.EntityBase;
import com.github.peholmst.fenix.entity.util.OrderInstruction;
import com.github.peholmst.fenix.entity.util.User;

public abstract class RepositoryBaseBean<E extends EntityBase> {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private EJBContext context;

    /**
     * 
     * @return
     */
    protected abstract Class<E> getEntityClass();

    /**
     * 
     * @return
     */
    protected User getCurrentUser() {
        return null;
    }

    /**
     * 
     * @return
     */
    protected EJBContext getContext() {
        return context;
    }

    /**
     * 
     * @return
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * 
     * @param entity
     * @param orderInstructions
     * @return
     */
    protected List<Order> convertOrderInstructions(Root<E> entity,
            OrderInstruction<E>... orderInstructions) {
        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final List<Order> orderByList = new ArrayList<Order>(
                orderInstructions.length);
        for (OrderInstruction<E> orderInstruction : orderInstructions) {
            if (orderInstruction.isAscending()) {
                orderByList.add(builder.asc(entity.get(orderInstruction
                        .getAttribute())));
            } else {
                orderByList.add(builder.desc(entity.get(orderInstruction
                        .getAttribute())));
            }
        }
        return orderByList;
    }

    public static class PredicateBuilder {
        private LinkedList<Predicate> predicates = new LinkedList<Predicate>();

        public PredicateBuilder add(Predicate predicate) {
            predicates.add(predicate);
            return this;
        }

        public Predicate[] get() {
            return predicates.toArray(new Predicate[predicates.size()]);
        }
    }

    /**
     * 
     * @param query
     * @param returnMax
     * @return
     */
    protected List<E> executeQuery(CriteriaQuery<E> query, int returnMax) {
        final TypedQuery<E> executableQuery = getEntityManager().createQuery(
                query);
        executableQuery.setMaxResults(returnMax);
        return executableQuery.getResultList();

    }
}
