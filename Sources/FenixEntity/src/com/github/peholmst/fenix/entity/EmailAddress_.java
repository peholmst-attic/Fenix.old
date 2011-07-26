package com.github.peholmst.fenix.entity;

import com.github.peholmst.fenix.entity.types.EmailAddressType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-26T18:46:37.283+0300")
@StaticMetamodel(EmailAddress.class)
public class EmailAddress_ {
	public static volatile SingularAttribute<EmailAddress, EmailAddressType> type;
	public static volatile SingularAttribute<EmailAddress, String> address;
	public static volatile SingularAttribute<EmailAddress, Boolean> publicAddress;
}
