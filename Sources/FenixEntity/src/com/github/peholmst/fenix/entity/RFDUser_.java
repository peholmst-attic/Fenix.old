package com.github.peholmst.fenix.entity;

import com.github.peholmst.fenix.entity.types.RFDUserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-26T20:34:35.828+0300")
@StaticMetamodel(RFDUser.class)
public class RFDUser_ extends User_ {
	public static volatile SingularAttribute<RFDUser, RFD> regionalFireDepartment;
	public static volatile SingularAttribute<RFDUser, RFDUserRole> role;
}
