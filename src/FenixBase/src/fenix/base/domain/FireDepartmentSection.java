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

import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import fenix.base.domain.annotation.AggregateRoot;
import fenix.base.domain.annotation.NeverReturnsNull;
import fenix.base.domain.localization.LocalizedString;
import fenix.base.domain.localization.LocalizedStringRequired;

/**
 * Entity representing a Section of a fire department. A section always belongs
 * to exactly one fire department and it always has a type (for statistical
 * purposes).
 * 
 * @author Petter Holmström
 */
@AggregateRoot
@Entity
public class FireDepartmentSection extends BaseEntity implements SoftDeletable,
		FireDepartmentSpecificEntity {

	private static final long serialVersionUID = -1642614550657835639L;

	@Embedded
	@LocalizedStringRequired(message = "{FireDepartmentSection.constraints.name}")
	protected LocalizedString name = LocalizedString.empty();

	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "{FireDepartmentSection.constraints.type}")
	protected FireDepartmentSectionType type;

	@Embedded
	@Valid
	protected Address address = Address.empty();

	@Embedded
	@Valid
	protected ContactInfo contactInfo = ContactInfo.empty();

	protected boolean deleted;

	@ManyToOne(optional = false)
	@NotNull(message = "Section has not been assigned to a fire department. This is a bug!")
	protected FireDepartment fireDepartment;

	/**
	 * Default constructor
	 */
	public FireDepartmentSection() {
	}

	/**
	 * Copy constructor
	 */
	public FireDepartmentSection(FireDepartmentSection original) {
		super(original);
		this.name = LocalizedString.copy(original.name);
		this.type = original.type;
		this.address = Address.copy(original.address);
		this.contactInfo = ContactInfo.copy(original.contactInfo);
		this.deleted = original.deleted;
		this.fireDepartment = original.fireDepartment;
	}

	@Override
	public FireDepartment getFireDepartment() {
		return fireDepartment;
	}

	public void setFireDepartment(final FireDepartment fireDepartment) {
		this.fireDepartment = fireDepartment;
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	void setDeleted(final boolean deleted) {
		this.deleted = deleted;
	}

	@NeverReturnsNull
	public LocalizedString getName() {
		return LocalizedString.copy(name);
	}

	public void setName(LocalizedString name) {
		this.name = LocalizedString.copy(name);
	}

	public FireDepartmentSectionType getType() {
		return type;
	}

	public void setType(FireDepartmentSectionType type) {
		this.type = type;
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
}
