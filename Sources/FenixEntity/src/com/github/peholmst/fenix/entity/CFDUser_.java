package com.github.peholmst.fenix.entity;

import com.github.peholmst.fenix.entity.types.CFDUserRole;
import com.github.peholmst.fenix.entity.util.User_;
import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-28T22:02:26.524+0300")
@StaticMetamodel(CFDUser.class)
public class CFDUser_ extends User_ {
	public static volatile MapAttribute<CFDUser, CFD, CFDUserRole> roles;
}
