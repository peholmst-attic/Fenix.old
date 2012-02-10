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

import java.util.List;

import fenix.base.domain.annotation.NeverReturnsNull;

/**
 * Repository interface for {@link Vehicle}s.
 * 
 * @author Petter Holmström
 */
public interface VehicleRepository extends BaseRepository<Vehicle> {

	/**
	 * Returns a list of vehicles for the specified fire department, sorted by
	 * call sign. If <code>includeDeleted</code> is true, the list will also
	 * include vehicles that have been flagged as deleted. If the fire
	 * department has no vehicles, or does not exist, an empty list is returned.
	 */
	@NeverReturnsNull
	List<Vehicle> findByFireDepartment(FireDepartment fireDepartment,
			boolean includeDeleted);

	/**
	 * Returns a list of all active vehicles for the specified fire department,
	 * sorted by call sign. If the fire department has no vehicles, or does not
	 * exist, an empty list is returned.
	 */
	@NeverReturnsNull
	List<Vehicle> findByFireDepartment(FireDepartment fireDepartment);
}
