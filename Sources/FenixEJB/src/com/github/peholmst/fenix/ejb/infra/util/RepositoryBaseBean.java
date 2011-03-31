package com.github.peholmst.fenix.ejb.infra.util;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.peholmst.fenix.entity.util.EntityBase;

/**
 * 
 * @author peholmst
 *
 * @param <ENTITY>
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public abstract class RepositoryBaseBean<ENTITY extends EntityBase> implements Repository<ENTITY> {
	
	private final Logger log = LoggerFactory.getLogger(getClass());		

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 
	 * @return
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}
	
	/**
	 * 
	 * @return
	 */
	protected abstract Class<ENTITY> getEntityClass();
	
	@Override
	public ENTITY findByIdentifier(long identifier) {
		return getEntityManager().find(getEntityClass(), identifier);
	}
	
	@Override
	public ENTITY save(ENTITY entity) {
		assert entity != null : "entity must not be null";
		if (entity.isPersistent()) {
			log.debug("Merging persistent entity {}", entity);
			return getEntityManager().merge(entity);
		} else {
			log.debug("Persisting transient entity {}", entity);
			getEntityManager().persist(entity);
			return entity;
		}
	}
}
