package com.github.peholmst.fenix.entity;

import com.github.peholmst.fenix.entity.types.CFDUserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-26T20:26:08.870+0300")
@StaticMetamodel(CFDUser.class)
public class CFDUser_ extends User_ {
	public static volatile MapAttribute<CFDUser, CFD, CFDUserRole> roles;
}
