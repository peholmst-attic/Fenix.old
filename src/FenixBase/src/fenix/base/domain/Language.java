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

import java.util.Locale;

/**
 * Enumeration of the languages supported by this system. As Fenix is intended
 * for use in Finland, both the official languages (Finnish and Swedish) are
 * supported.
 * 
 * @author Petter Holmström
 */
public enum Language {

	FINNISH(new Locale("fi")), SWEDISH(new Locale("sv"));

	private final Locale locale;

	private Language(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Returns the language locale.
	 */
	public Locale getLocale() {
		return locale;
	}
}
