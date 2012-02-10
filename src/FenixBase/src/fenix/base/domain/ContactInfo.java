/*
 * Fenix
 * Copyright (C) 2012 Petter Holmström
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fenix.base.domain;

import javax.persistence.Embeddable;

import fenix.base.domain.annotation.NeverReturnsNull;
import fenix.base.domain.annotation.ValueObject;
import fenix.base.domain.validation.Email;
import fenix.base.domain.validation.PhoneNumber;
import fenix.base.domain.validation.Website;

/**
 * Value object containing the most common contact information (phone, fax,
 * e-mail and website).
 * 
 * @author Petter Holmström
 */
@Embeddable
@ValueObject
public class ContactInfo implements java.io.Serializable {

	private static final long serialVersionUID = -1634117784467199361L;

	@PhoneNumber
	protected String phone;

	@PhoneNumber
	protected String fax;

	@Email
	protected String email;

	@Website
	protected String website;

	/**
	 * Default constructor
	 */
	public ContactInfo() {
	}

	/**
	 * Copy constructor
	 */
	public ContactInfo(final ContactInfo original) {
		assert original != null : "original must not be null";
		this.phone = original.phone;
		this.fax = original.fax;
		this.email = original.email;
		this.website = original.website;
	}

	/**
	 * Creates a new <code>ContactInfoBuilder</code> with the initial property
	 * values set to the values of this <code>ContactInfo</code> object.
	 */
	@NeverReturnsNull
	public ContactInfoBuilder derive() {
		return new ContactInfoBuilder(this);
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	public String getPhone() {
		return phone;
	}

	public String getWebsite() {
		return website;
	}

	/**
	 * Builder for creating new <code>ContactInfo</code> instances.
	 * 
	 * @author Petter Holmström
	 */
	public static final class ContactInfoBuilder {

		private String phone;
		private String fax;
		private String email;
		private String website;

		private ContactInfoBuilder() {
		}

		private ContactInfoBuilder(final ContactInfo original) {
			this.phone = original.phone;
			this.fax = original.fax;
			this.email = original.email;
			this.website = original.website;
		}

		@NeverReturnsNull
		public ContactInfoBuilder setPhone(final String phone) {
			this.phone = phone;
			return this;
		}

		@NeverReturnsNull
		public ContactInfoBuilder setFax(final String fax) {
			this.fax = fax;
			return this;
		}

		@NeverReturnsNull
		public ContactInfoBuilder setEmail(final String email) {
			this.email = email;
			return this;
		}

		@NeverReturnsNull
		public ContactInfoBuilder setWebsite(final String website) {
			this.website = website;
			return this;
		}

		/**
		 * Creates and returns a new <code>ContactInfo</code> instance with the
		 * values specified in the builder.
		 */
		@NeverReturnsNull
		public ContactInfo build() {
			final ContactInfo ci = new ContactInfo();
			ci.email = email;
			ci.fax = fax;
			ci.phone = phone;
			ci.website = website;
			return ci;
		}
	}

	/**
	 * Creates a new <code>ContactInfoBuilder</code> with the initial property
	 * values set to null.
	 */
	@NeverReturnsNull
	public static ContactInfoBuilder create() {
		return new ContactInfoBuilder();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactInfo other = (ContactInfo) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String
				.format("%s[phone=%s, fax=%s, email=%s, website=%s, objectIdentity=%s]",
						getClass().getSimpleName(), phone, fax, email, website,
						Integer.toHexString(System.identityHashCode(this)));
	}
}
