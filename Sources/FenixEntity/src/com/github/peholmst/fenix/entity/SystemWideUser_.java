package com.github.peholmst.fenix.entity;

import com.github.peholmst.fenix.entity.types.SystemWideUserRole;
import com.github.peholmst.fenix.entity.util.User_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-28T22:02:26.546+0300")
@StaticMetamodel(SystemWideUser.class)
public class SystemWideUser_ extends User_ {
	public static volatile SingularAttribute<SystemWideUser, SystemWideUserRole> role;
}
