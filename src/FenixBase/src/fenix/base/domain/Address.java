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
import fenix.base.domain.localization.LocalizedString;
import fenix.base.domain.validation.PostalCode;

/**
 * Value object containing a Finnish street address.
 * 
 * @see #copy(Address)
 * @see #create()
 * @see #derive()
 * @see #empty()
 * @see AddressRequired
 * 
 * @author Petter Holmström
 */
@ValueObject
@Embeddable
public class Address implements java.io.Serializable {

	private static final long serialVersionUID = 5897544331818829256L;

	protected LocalizedString streetAddress = LocalizedString.empty();

	@PostalCode
	protected String postalCode;

	protected LocalizedString postOffice = LocalizedString.empty();

	/**
	 * Default constructor
	 * 
	 * @see #empty()
	 */
	protected Address() {
	}

	/**
	 * Copy constructor
	 * 
	 * @see #copy(Address)
	 */
	protected Address(final Address original) {
		assert original != null : "original must not be null";
		this.streetAddress = LocalizedString.copy(original.streetAddress);
		this.postalCode = original.postalCode;
		this.postOffice = LocalizedString.copy(original.postOffice);
	}

	/**
	 * Creates a new <code>AddressBuilder</code> with the initial property
	 * values set to the values of this <code>Address</code> object.
	 */
	@NeverReturnsNull
	public AddressBuilder derive() {
		return new AddressBuilder(this);
	}

	@NeverReturnsNull
	public LocalizedString getStreetAddress() {
		return LocalizedString.copy(streetAddress);
	}

	public String getPostalCode() {
		return postalCode;
	}

	@NeverReturnsNull
	public LocalizedString getPostOffice() {
		return LocalizedString.copy(postOffice);
	}

	/**
	 * Builder for creating new <code>Address</code> instances.
	 * 
	 * @author Petter Holmström
	 */
	public static final class AddressBuilder {

		private LocalizedString streetAddress;
		private String postalCode;
		private LocalizedString postOffice;

		private AddressBuilder() {
		}

		private AddressBuilder(final Address original) {
			this.streetAddress = LocalizedString.copy(original.streetAddress);
			this.postalCode = original.postalCode;
			this.postOffice = LocalizedString.copy(original.postOffice);
		}

		@NeverReturnsNull
		public AddressBuilder setPostOffice(final LocalizedString postOffice) {
			if (postOffice == null) {
				this.postOffice = null;
			} else {
				this.postOffice = LocalizedString.copy(postOffice);
			}
			return this;
		}

		@NeverReturnsNull
		public AddressBuilder setPostalCode(final String postalCode) {
			this.postalCode = postalCode;
			return this;
		}

		@NeverReturnsNull
		public AddressBuilder setStreetAddress(
				final LocalizedString streetAddress) {
			if (streetAddress == null) {
				this.streetAddress = null;
			} else {
				this.streetAddress = LocalizedString.copy(streetAddress);
			}
			return this;
		}

		/**
		 * Creates and returns a new <code>Address</code> instance with the
		 * values specified in the builder.
		 */
		@NeverReturnsNull
		public Address build() {
			final Address address = new Address();
			address.streetAddress = LocalizedString.copy(streetAddress);
			address.postalCode = postalCode;
			address.postOffice = LocalizedString.copy(postOffice);
			return address;
		}
	}

	/**
	 * Creates a new <code>AddressBuilder</code> with the initial property
	 * values set to null.
	 */
	@NeverReturnsNull
	public static AddressBuilder create() {
		return new AddressBuilder();
	}

	/**
	 * Creates and returns an empty <code>Address</code> object.
	 */
	@NeverReturnsNull
	public static Address empty() {
		return new Address();
	}

	/**
	 * Creates and returns a copy of the specified <code>Address</code> object.
	 * If null is specified, an empty <code>Address</code> object is returned.
	 */
	@NeverReturnsNull
	public static Address copy(Address original) {
		if (original == null) {
			return empty();
		} else {
			return new Address(original);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((postOffice == null) ? 0 : postOffice.hashCode());
		result = prime * result
				+ ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result
				+ ((streetAddress == null) ? 0 : streetAddress.hashCode());
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
		Address other = (Address) obj;
		if (postOffice == null) {
			if (other.postOffice != null)
				return false;
		} else if (!postOffice.equals(other.postOffice))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		} else if (!streetAddress.equals(other.streetAddress))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String
				.format("%s[streetAddress=%s, postalCode=%s, postOffice=%s, website=%s, objectIdentity=%s]",
						getClass().getSimpleName(), streetAddress, postalCode,
						postOffice,
						Integer.toHexString(System.identityHashCode(this)));
	}
}
