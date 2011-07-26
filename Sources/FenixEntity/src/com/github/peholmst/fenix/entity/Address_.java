package com.github.peholmst.fenix.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-26T18:37:30.673+0300")
@StaticMetamodel(Address.class)
public class Address_ {
	public static volatile SingularAttribute<Address, String> postalCode;
	public static volatile SingularAttribute<Address, String> postOffice;
	public static volatile SingularAttribute<Address, String> streetOrBox;
	public static volatile SingularAttribute<Address, String> number;
	public static volatile SingularAttribute<Address, String> appartment;
}
