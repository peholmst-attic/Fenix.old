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

import com.github.peholmst.mvp4vaadin.View;

/**
 * View interface for the LoginView. The LoginView allows users to log into the
 * system.
 * 
 * @author Petter Holmström
 */
public interface LoginView extends View {

	/**
	 * Shows a notification to the user that login has failed.
	 */
	void showLoginFailed();

	/**
	 * Clears the login form and puts focus into the username text field.
	 */
	void clearForm();

}
