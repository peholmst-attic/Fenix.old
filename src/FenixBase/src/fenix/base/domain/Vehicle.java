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

import fenix.base.domain.annotation.AggregateRoot;

/**
 * Entity representing a Vehicle. A vehicle always belong to exactly one fire
 * department. A vehicle can be owned by another organization than the fire
 * department using it.
 * 
 * @author Petter Holmström
 */
@AggregateRoot
@Entity
public class Vehicle extends BaseEntity implements SoftDeletable,
		FireDepartmentSpecificEntity {

	private static final long serialVersionUID = -1375764385959086395L;

	@Basic(optional = false)
	protected String callsign;

	@Basic(optional = false)
	@Enumerated(EnumType.STRING)
	protected VehicleType type;

	protected Integer yearModel;

	@ManyToOne(optional = false)
	protected FireDepartment fireDepartment;

	protected boolean ownedByFireDepartment;

	protected boolean deleted;

	protected String licensePlate;

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
