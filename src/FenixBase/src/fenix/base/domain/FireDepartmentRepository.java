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
import fenix.base.domain.localization.Language;

/**
 * Repository interface for {@link FireDepartment}.
 * 
 * @author Petter Holmström
 */
public interface FireDepartmentRepository extends
		BaseHistoryRepository<FireDepartment> {

	/**
	 * Returns a list of all active fire departments, sorted by name in the
	 * specified language.
	 */
	@NeverReturnsNull
	List<FireDepartment> findAll(Municipality municipality,
			Language sortByLanguage);

	/**
	 * Returns a list of all active fire departments in the specified
	 * municipality, sorted by name in the specified language.
	 */
	@NeverReturnsNull
	List<FireDepartment> findByMunicipality(Municipality municipality,
			Language sortByLanguage);

	/**
	 * Returns a list of all active fire departments whose name contain the
	 * specified search string (case insensitive), sorted by name in the
	 * specified language.
	 */
	@NeverReturnsNull
	List<FireDepartment> findByName(String name, Language sortByLanguage);

}
