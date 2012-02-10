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
 * Repository interface for {@link Municipality}.
 * 
 * @author Petter Holmström
 */
public interface MunicipalityRepository extends BaseRepository<Municipality> {

	/**
	 * Returns a list of all active municipalities, sorted by the municipality
	 * name in the specified language.
	 */
	@NeverReturnsNull
	List<Municipality> findAll(Language sortByLanguage);

	/**
	 * Returns a list of all municipalities, sorted by the municipality name in
	 * the specified language. If <code>includeDeleted</code> is true, the list
	 * will also include municipalities that have been flagged as deleted.
	 */
	@NeverReturnsNull
	List<Municipality> findAll(Language sortByLanguage, boolean includeDeleted);
}
