/*
 * Fenix
 * Copyright (C) 2011 Petter Holmström
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
package fenix.base.ui;

import com.github.peholmst.mvp4vaadin.ViewEvent;

/**
 * Event fired by {@link LoginView} when the user has successfully logged in.
 * 
 * @author Petter Holmström
 */
public class UserLoggedInEvent extends ViewEvent {

	private static final long serialVersionUID = 6947186611885871856L;

	private final String username;

	public UserLoggedInEvent(LoginView source, String username) {
		super(source);
		this.username = username;
	}

	/**
	 * Returns the username of the user that has logged in.
	 */
	public String getUsername() {
		return username;
	}

}
