package com.github.peholmst.fenix.entity;

import com.github.peholmst.fenix.entity.types.SystemWideUserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-26T20:20:17.302+0300")
@StaticMetamodel(SystemWideUser.class)
public class SystemWideUser_ extends User_ {
	public static volatile SingularAttribute<SystemWideUser, SystemWideUserRole> role;
}
