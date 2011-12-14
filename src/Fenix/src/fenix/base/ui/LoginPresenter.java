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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.github.peholmst.mvp4vaadin.Presenter;

/**
 * Presenter that attempts to authenticate by invoking
 * {@link HttpServletRequest#login(String, String)} with a username and password
 * provided by the user.
 * 
 * @see LoginView
 * 
 * @author Petter Holmström
 */
public class LoginPresenter extends Presenter<LoginView> {

	private static final long serialVersionUID = -4122495133920178838L;

	/**
	 * Attempts to log in using the specified username and password. If the
	 * authentication succeeds, a {@link UserLoggedInEvent} is fired. Otherwise,
	 * an error message is shown to the user.
	 * 
	 * @see HttpServletRequest#login(String, String)
	 */
	public void attemptLogin(final String username, final String password) {
		final HttpServletRequest request = getCurrentRequest();
		if (request == null) {
			throw new IllegalStateException(
					"No active HttpServletRequest - cannot log in");
		}
		try {
			request.login(username, password);
			fireViewEvent(new UserLoggedInEvent(getView(), username));
		} catch (ServletException e) {
			getView().showLoginFailed();
			getView().clearForm();
		}
	}

	/**
	 * Returns the current HttpServletRequest (required to perform the actual
	 * authentication). This has been extracted into a separate method to make
	 * unit testing easier.
	 */
	protected HttpServletRequest getCurrentRequest() {
		return FenixApplication.getInstance().getCurrentRequest();
	}
	
	@Override
	public void init() {
		getView().clearForm();
	}
}
