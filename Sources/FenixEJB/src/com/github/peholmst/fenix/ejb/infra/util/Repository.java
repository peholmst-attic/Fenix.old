package com.github.peholmst.fenix.ejb.infra.util;

import java.util.List;

import com.github.peholmst.fenix.common.util.OrderInstruction;
import com.github.peholmst.fenix.entity.util.EntityBase;

public interface Repository<ENTITY extends EntityBase> {

	ENTITY save(ENTITY entity);	
	
	ENTITY findByIdentifier(long identifier);
	
	List<ENTITY> findAll(OrderInstruction ... orderBy);
	
}
