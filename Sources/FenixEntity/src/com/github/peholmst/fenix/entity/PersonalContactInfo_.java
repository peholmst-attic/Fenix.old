package com.github.peholmst.fenix.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-07-26T18:54:55.892+0300")
@StaticMetamodel(PersonalContactInfo.class)
public class PersonalContactInfo_ {
	public static volatile SetAttribute<PersonalContactInfo, PhoneNumber> phoneNumbers;
	public static volatile SetAttribute<PersonalContactInfo, EmailAddress> emailAddresses;
}
