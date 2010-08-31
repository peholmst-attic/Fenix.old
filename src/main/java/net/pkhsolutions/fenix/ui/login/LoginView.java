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

import net.pkhsolutions.fenix.ui.mvp.View;

/**
 * This interface defines the Login View. It asks the user for a username and a
 * password and passes them to the presenter for authentication. It also shows
 * notifications to the user if the authentication fails. Finally, the view
 * allows the user to change the language of the application.
 * 
 * @see LoginPresenter
 * @author Petter Holmström
 */
public interface LoginView extends View {

	/**
	 * The name of the Login View bean when configured inside a Spring
	 * application context.
	 */
	String BEAN_NAME = "loginViewBean";

	/**
	 * Informs the user that the credentials were bad.
	 */
	void showBadCredentials();

	/**
	 * Informs the user that the account has been disabled.
	 */
	void showAccountDisabled();

	/**
	 * Informs the user that the account has been locked.
	 */
	void showAccountLocked();

	/**
	 * Clears the username/password login form.
	 */
	void clearForm();

}
