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

import java.util.UUID;

import fenix.base.domain.annotation.NeverReturnsNull;

/**
 * Base interface for all repositories.
 * 
 * @author Petter Holmström
 * 
 * @param <E>
 *            the type of entities handled by this repository.
 */
public interface BaseRepository<E extends BaseEntity> {

	/**
	 * Saves the specified entity and returns it. The returned instance may, but
	 * need not be the same instance as the passed in instance.
	 */
	@NeverReturnsNull
	E save(E entity);

	/**
	 * Deletes the specified entity. If the entity cannot be found, nothing
	 * happens.
	 */
	void delete(E entity);

	/**
	 * Returns the entity with the specified UUID, or null if not found.
	 */
	E findByUUID(UUID uuid);

}
