package com.github.peholmst.fenix.ejb.infra;

import javax.ejb.Local;

import com.github.peholmst.fenix.ejb.infra.util.Repository;
import com.github.peholmst.fenix.entity.EventType;

@Local
public interface EventTypeRepository extends Repository<EventType> {

}
