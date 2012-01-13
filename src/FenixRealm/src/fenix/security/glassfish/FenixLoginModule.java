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
package fenix.security.glassfish;

import javax.security.auth.login.LoginException;

import com.sun.appserv.security.AppservPasswordLoginModule;

/**
 * GlassFish login module for the Fenix realm.
 * 
 * @see FenixRealm
 * @see <a href="http://developers.sun.com/appserver/reference/techart/as8_authentication/">Authentication Using Custom Realms</a>
 * 
 * @author Petter Holmström
 */
public class FenixLoginModule extends AppservPasswordLoginModule {

	@Override
	protected void authenticateUser() throws LoginException {
		if (!(_currentRealm instanceof FenixRealm)) {
			throw new LoginException("The current realm is not of type FenixRealm");
		}
		final FenixRealm realm = (FenixRealm) _currentRealm;
		final String[] userGroupNames = realm.authenticateUser(_username, getPasswordChar());
		if (userGroupNames == null) {
			throw new LoginException("Login failed for user " + _username);
		}
		commitUserAuthentication(userGroupNames);
	}
}
