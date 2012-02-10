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

import fenix.base.domain.localization.Localized;

/**
 * Enumeration of different fire department section types.
 * 
 * @author Petter Holmström
 */
public enum FireDepartmentSectionType implements Localized {

	RESCUE, EMS, RESCUE_AND_EMS, WOMEN, YOUTH, CHILDREN, VETERAN_OR_SUPPORT, MUSIC, OTHER;

	@Override
	public String getMessageKey() {
		return String.format("%s.%s",
				FireDepartmentSectionType.class.getSimpleName(), name());
	}

}
