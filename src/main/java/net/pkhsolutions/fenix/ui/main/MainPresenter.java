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
package net.pkhsolutions.fenix.ui.main;

import net.pkhsolutions.fenix.ui.mvp.Presenter;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 * @author petter
 *
 */
@Component
public class MainPresenter extends Presenter<MainView> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6885390700438953159L;

	/**
	 * 
	 */
	public void logout() {
		if (logger.isDebugEnabled()) {
			logger.debug("Logging out the user by closing the application context");
		}
		((ConfigurableApplicationContext) getApplicationContext()).close();
	}
}
