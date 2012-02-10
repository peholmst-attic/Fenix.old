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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import fenix.base.domain.annotation.AggregateRoot;
import fenix.base.domain.annotation.NeverReturnsNull;
import fenix.base.domain.localization.Language;
import fenix.base.domain.localization.LocalizedString;
import fenix.base.domain.localization.LocalizedStringRequired;

/**
 * Entity representing a Fire Department. A fire department must have a name, a
 * postal address, an e-mail address and at least one language. In addition, it
 * must have a response time, a type and a municipality (for statistical
 * purposes).
 * 
 * @author Petter Holmström
 */
@AggregateRoot
@Entity
public class FireDepartment extends BaseHistoryEntity {

	private static final long serialVersionUID = 2966479165778797034L;

	@Embedded
	@LocalizedStringRequired(message = "{FireDepartment.constraints.name}")
	protected LocalizedString name = LocalizedString.empty();

	@Embedded
	@AddressRequired
	protected Address address = Address.empty();

	@Embedded
	@Valid
	@ContactInfoRequired(value = ContactInfoRequired.Property.EMAIL, message = "{FireDepartment.constraints.contactInfo}")
	protected ContactInfo contactInfo = ContactInfo.empty();

	@ManyToOne(optional = false)
	@NotNull(message = "{FireDepartment.constraints.municipality}")
	protected Municipality municipality;

	@Enumerated(EnumType.STRING)
	@ElementCollection
	@Size(min = 1, message = "{FireDepartment.constraints.languages}")
	protected Set<Language> languages = new HashSet<Language>();

	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	@NotNull(message = "{FireDepartment.constraints.responseTime}")
	protected ResponseTime responseTime;

	@Enumerated(EnumType.STRING)
	@Basic(optional = false)
	@NotNull(message = "{FireDepartment.constraints.type}")
	protected FireDepartmentType type;

	/**
	 * Default constructor
	 */
	public FireDepartment() {
	}

	/**
	 * Copy constructor
	 */
	public FireDepartment(FireDepartment original) {
		super(original);
		this.name = LocalizedString.copy(original.name);
		this.address = Address.copy(original.address);
		this.contactInfo = ContactInfo.copy(original.contactInfo);
		this.municipality = original.municipality;
		this.languages = new HashSet<Language>(original.languages);
		this.responseTime = original.responseTime;
		this.type = original.type;
	}

	@NeverReturnsNull
	public LocalizedString getName() {
		return LocalizedString.copy(name);
	}

	public void setName(LocalizedString name) {
		this.name = LocalizedString.copy(name);
	}

	@NeverReturnsNull
	public Address getAddress() {
		return Address.copy(address);
	}

	public void setAddress(Address address) {
		this.address = Address.copy(address);
	}

	@NeverReturnsNull
	public ContactInfo getContactInfo() {
		return ContactInfo.copy(contactInfo);
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = ContactInfo.copy(contactInfo);
	}

	public Municipality getMunicipality() {
		return municipality;
	}

	public void setMunicipality(Municipality municipality) {
		this.municipality = municipality;
	}

	@NeverReturnsNull
	public Set<Language> getLanguages() {
		return new HashSet<Language>(languages);
	}

	public void setLanguages(Set<Language> languages) {
		if (languages == null) {
			this.languages = new HashSet<Language>();
		} else {
			this.languages = new HashSet<Language>(languages);
		}
	}

	public ResponseTime getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(ResponseTime responseTime) {
		this.responseTime = responseTime;
	}

	public FireDepartmentType getType() {
		return type;
	}

	public void setType(FireDepartmentType type) {
		this.type = type;
	}
}
