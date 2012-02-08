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

/**
 * Interface to be implemented by entities that use soft deletes, i.e. instead
 * of removing them from the data store, they are just marked as deleted and
 * filtered out from queries.
 * 
 * @author Petter Holmström
 */
public interface SoftDeletable {

	/**
	 * Returns whether the entity has been marked as deleted or not.
	 */
	boolean isDeleted();
}
