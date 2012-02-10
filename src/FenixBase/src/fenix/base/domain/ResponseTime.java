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
 * Enumeration of response time ranges.
 * 
 * @author Petter Holmström
 */
public enum ResponseTime implements Localized {

	WITHIN_1_2_MINS, WITHIN_3_5_MINS, WITHIN_6_10_MINS, WITHIN_11_20_MINS, WITHIN_21_30_MINS, OVER_30_MINS, NONE;

	@Override
	public String getMessageKey() {
		return String.format("%s.%s", ResponseTime.class.getSimpleName(),
				name());
	}
}
