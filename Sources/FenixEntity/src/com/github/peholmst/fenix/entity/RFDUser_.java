package com.github.peholmst.fenix.entity;

import com.github.peholmst.fenix.entity.types.RFDUserRole;
import com.github.peholmst.fenix.entity.util.User_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-28T22:02:26.537+0300")
@StaticMetamodel(RFDUser.class)
public class RFDUser_ extends User_ {
	public static volatile SingularAttribute<RFDUser, RFD> regionalFireDepartment;
	public static volatile SingularAttribute<RFDUser, RFDUserRole> role;
}
