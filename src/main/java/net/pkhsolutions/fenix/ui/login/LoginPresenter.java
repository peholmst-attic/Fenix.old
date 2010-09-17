/*
 * Copyright (c) 2010 The original author(s)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.pkhsolutions.fenix.ui.login;

import java.util.Locale;

import net.pkhsolutions.fenix.i18n.I18N;
import net.pkhsolutions.fenix.ui.mvp.Presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

/**
 * This class implements the Login Presenter. It receives a username/password
 * combination from the View and attempts to authenticate them with a Spring
 * {@link AuthenticationManager} (see {@link #attemptLogin(String, String)} for
 * more information}.
 * <p>
 * The presenter also knows how to change the language of the application (see
 * {@link #changeLocale(Locale)}).
 * 
 * @see LoginView
 * @author Petter Holmström
 */
public final class LoginPresenter extends Presenter<LoginView> {

	/**
	 * Creates a new <code>LoginPresenter</code>.
	 * 
	 * @param view
	 *            the view to use.
	 * @param i18n
	 *            the I18N instance to use for changing the locale.
	 */
	public LoginPresenter(LoginView view, I18N i18n) {
		super(view);
		this.i18n = i18n;
	}

	private static final long serialVersionUID = 200565293225998622L;

	/**
	 * The Spring authentication manager that will be used to perform the actual
	 * authentication.
	 */
	@Autowired
	private transient AuthenticationManager authenticationManager;

	/**
	 * The I18N instance that will be used to change the locale.
	 */
	private I18N i18n;

	/**
	 * Attempts to login using the specified username and password. If login
	 * succeeds, the presenter will publish a {@link UserLoggedInEvent} to the
	 * view. If login fails, the user is notified.
	 * 
	 * @param username
	 *            the username.
	 * @param password
	 *            the password.
	 */
	public void attemptLogin(String username, String password) {
		final Authentication auth = new UsernamePasswordAuthenticationToken(
				username, password);
		if (logger.isDebugEnabled()) {
			logger.debug("Attempting authentication of user '" + auth.getName()
					+ "'");
		}
		try {
			getView().clearForm();
			Authentication result = authenticationManager.authenticate(auth);
			if (logger.isDebugEnabled()) {
				logger.debug("Authentication of user '" + auth.getName()
						+ "' succeeded");
			}
			getView().fireViewEvent(new UserLoggedInEvent(getView(), result));
		} catch (BadCredentialsException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Bad credentials for user '" + auth.getName()
						+ "'", e);
			}
			getView().showBadCredentials();
		} catch (DisabledException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Account disabled for user '" + auth.getName()
						+ "'", e);
			}
			getView().showAccountDisabled();
		} catch (LockedException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(
						"Account locked for user '" + auth.getName() + "'", e);
			}
			getView().showAccountLocked();
		}
	}

	/**
	 * Changes the locale of the application to <code>locale</code>.
	 * 
	 * @see I18N#setLocale(Locale)
	 * 
	 * @param locale
	 *            the locale to change to.
	 */
	public void changeLocale(Locale locale) {
		if (logger.isInfoEnabled()) {
			logger.info("Changing locale to [" + locale + "]");
		}
		i18n.setLocale(locale);
	}
}
