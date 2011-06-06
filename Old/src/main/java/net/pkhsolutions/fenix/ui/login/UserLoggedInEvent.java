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

import net.pkhsolutions.fenix.ui.mvp.ViewEvent;

import org.springframework.security.core.Authentication;

/**
 * View event that is fired by a {@link LoginView} when the user has logged in
 * successfully.
 * 
 * @author Petter Holmström
 */
public class UserLoggedInEvent extends ViewEvent {

	private static final long serialVersionUID = -2819159232773687159L;

	private final Authentication authentication;

	/**
	 * Creates a new <code>UserLoggedInEvent</code>.
	 * 
	 * @param source
	 *            the view that fired the event.
	 * @param authentication
	 *            the authentication credentials of the user.
	 */
	public UserLoggedInEvent(LoginView source, Authentication authentication) {
		super(source);
		this.authentication = authentication;
	}

	/**
	 * Gets the authentication credentials of the user.
	 * 
	 * @return the credentials.
	 */
	public Authentication getAuthentication() {
		return authentication;
	}
}
