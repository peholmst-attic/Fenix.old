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
import org.springframework.stereotype.Component;

/**
 * TODO Document me!
 * 
 * @author Petter Holmström
 */
@Component
public final class LoginPresenter extends Presenter<LoginView> {

	private static final long serialVersionUID = 200565293225998622L;

	/**
	 * The Spring authentication manager that will be used to perform the actual
	 * authentication.
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * The I18N instance that will be used to change the locale.
	 */
	@Autowired
	private I18N i18n;

	/**
	 * Attempts to login using the specified username and password. If login
	 * succeeds, the authentication manager will publish a
	 * {@link org.springframework.security.authentication.event.AuthenticationSuccessEvent
	 * AuthenticationSuccessEvent} to the Spring application context. If login
	 * fails, the user is notified and a corresponding
	 * <code>AuthenticationFailure*</code> (e.g.
	 * {@link org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent
	 * AuthenticationFailureBadCredentialsEvent}) event is published.
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
			authenticationManager.authenticate(auth);
			if (logger.isDebugEnabled()) {
				logger.debug("Authentication of user '" + auth.getName()
						+ "' succeeded");
			}
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
