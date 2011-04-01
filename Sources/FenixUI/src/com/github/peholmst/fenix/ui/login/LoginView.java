/*
 * Copyright (c) 2011 Petter Holmström
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.peholmst.fenix.ui.login;

import com.github.peholmst.mvp4vaadin.View;

/**
 * This interface defines the view of the login window that is presented to a user when
 * Fenix starts up.
 * 
 * @author Petter Holmström
 */
public interface LoginView extends View {
    
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
