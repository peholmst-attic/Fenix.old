package com.github.peholmst.fenix.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-26T20:30:26.208+0300")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> fullName;
	public static volatile SingularAttribute<User, String> emailAddress;
	public static volatile SingularAttribute<User, String> encryptedPassword;
	public static volatile SingularAttribute<User, Boolean> active;
	public static volatile SingularAttribute<User, Boolean> blocked;
}
