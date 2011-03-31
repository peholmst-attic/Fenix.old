package com.github.peholmst.fenix.ejb.infra.impl;

import javax.ejb.Stateless;

import com.github.peholmst.fenix.ejb.infra.EventTypeRepository;
import com.github.peholmst.fenix.ejb.infra.util.RepositoryBaseBean;
import com.github.peholmst.fenix.entity.EventType;

/**
 * 
 * @author peholmst
 *
 */
@Stateless
public class EventTypeRepositoryBean extends RepositoryBaseBean<EventType> implements EventTypeRepository {

	@Override
	protected Class<EventType> getEntityClass() {
		return EventType.class;
	}

}
