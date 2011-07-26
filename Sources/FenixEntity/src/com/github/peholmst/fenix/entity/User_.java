package com.github.peholmst.fenix.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-26T22:56:25.953+0300")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> fullName;
	public static volatile SingularAttribute<User, String> emailAddress;
	public static volatile SingularAttribute<User, String> encryptedPassword;
	public static volatile SingularAttribute<User, Boolean> active;
	public static volatile SingularAttribute<User, Boolean> locked;
	public static volatile SingularAttribute<User, Date> lastFailedLoginAttempt;
	public static volatile SingularAttribute<User, Integer> numberOfFailedLoginAttempts;
	public static volatile SingularAttribute<User, Boolean> forcePasswordChangeOnNextLogin;
	public static volatile SingularAttribute<User, Date> expirationDate;
}
