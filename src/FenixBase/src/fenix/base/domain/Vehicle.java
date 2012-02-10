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
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import fenix.base.domain.annotation.AggregateRoot;
import fenix.base.domain.validation.LicensePlate;
import fenix.base.domain.validation.NotEmpty;

/**
 * Entity representing a Vehicle. A vehicle always belong to exactly one fire
 * department and it always has a type (for statistical purposes). A vehicle can
 * be owned by another organization than the fire department using it.
 * 
 * @author Petter Holmström
 */
@AggregateRoot
@Entity
public class Vehicle extends BaseEntity implements SoftDeletable,
		FireDepartmentSpecificEntity {

	private static final long serialVersionUID = -1375764385959086395L;

	@Basic(optional = false)
	@NotEmpty(message = "{Vehicle.constraints.callsign}")
	protected String callsign;

	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "{Vehicle.constraints.type}")
	protected VehicleType type;

	protected Integer yearModel;

	@ManyToOne(optional = false)
	@NotNull(message = "Vehicle has not been assigned to a fire department. This is a bug!")
	protected FireDepartment fireDepartment;

	protected boolean ownedByFireDepartment;

	protected boolean deleted;

	@LicensePlate
	protected String licensePlate;

	/**
	 * Default constructor
	 */
	public Vehicle() {
	}

	/**
	 * Copy constructor
	 */
	public Vehicle(Vehicle original) {
		super(original);
		this.callsign = original.callsign;
		this.type = original.type;
		this.yearModel = original.yearModel;
		this.fireDepartment = original.fireDepartment;
		this.ownedByFireDepartment = original.ownedByFireDepartment;
		this.deleted = original.deleted;
		this.licensePlate = original.licensePlate;
	}

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public FireDepartment getFireDepartment() {
		return fireDepartment;
	}

	public void setFireDepartment(FireDepartment fireDepartment) {
		this.fireDepartment = fireDepartment;
	}

	public String getCallsign() {
		return callsign;
	}

	public void setCallsign(String callsign) {
		this.callsign = callsign;
	}

	public VehicleType getType() {
		return type;
	}

	public void setType(VehicleType type) {
		this.type = type;
	}

	public Integer getYearModel() {
		return yearModel;
	}

	public void setYearModel(Integer yearModel) {
		this.yearModel = yearModel;
	}

	public boolean isOwnedByFireDepartment() {
		return ownedByFireDepartment;
	}

	public void setOwnedByFireDepartment(boolean ownedByFireDepartment) {
		this.ownedByFireDepartment = ownedByFireDepartment;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

}
