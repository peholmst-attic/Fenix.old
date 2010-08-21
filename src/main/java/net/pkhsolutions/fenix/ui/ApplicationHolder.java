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
package net.pkhsolutions.fenix.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * TODO Document me!
 * @author petter
 *
 */
@Component
public final class ApplicationHolder {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationHolder.class);
	
	private static final ThreadLocal<FenixApplication> application = new ThreadLocal<FenixApplication>();
	
	/**
	 * 
	 * @param app
	 */
	public static void setApplication(FenixApplication app) {
		if (logger.isDebugEnabled()) {
			logger.debug("Setting application of thread [" + Thread.currentThread() + "] to [" + app + "]");
		}
		application.set(app);
	}
	
	/**
	 * 
	 */
	public static void clearApplication() {
		if (logger.isDebugEnabled()) {
			logger.debug("Removing application of thread [" + Thread.currentThread() + "]");
		}
		application.remove();
	}
	
	/**
	 * 
	 * @return
	 */
	@Bean(autowire=Autowire.NO)
	public FenixApplication applicationInstance() {
		if (logger.isDebugEnabled()) {
			logger.debug("Returning application instance of thread [" + Thread.currentThread() + "]");
		}
		return application.get();
	}
}
