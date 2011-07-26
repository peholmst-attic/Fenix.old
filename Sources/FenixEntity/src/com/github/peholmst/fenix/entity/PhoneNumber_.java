package com.github.peholmst.fenix.entity;

import com.github.peholmst.fenix.entity.types.PhoneNumberType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-26T18:51:00.134+0300")
@StaticMetamodel(PhoneNumber.class)
public class PhoneNumber_ {
	public static volatile SingularAttribute<PhoneNumber, PhoneNumberType> type;
	public static volatile SingularAttribute<PhoneNumber, String> areaCode;
	public static volatile SingularAttribute<PhoneNumber, String> number;
	public static volatile SingularAttribute<PhoneNumber, Boolean> publicNumber;
}
