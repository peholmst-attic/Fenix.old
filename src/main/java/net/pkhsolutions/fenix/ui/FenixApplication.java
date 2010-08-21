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

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.pkhsolutions.fenix.i18n.I18N;
import net.pkhsolutions.fenix.i18n.I18NListener;
import net.pkhsolutions.fenix.ui.login.LoginView;
import net.pkhsolutions.fenix.ui.mvp.VaadinView;
import net.pkhsolutions.fenix.util.ListenerList;
import net.pkhsolutions.fenix.util.ListenerList.ListenerVisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * TODO Document me!
 * 
 * @author petter
 */
public class FenixApplication extends Application implements I18N,
		ApplicationContextAware, ApplicationListener<ContextClosedEvent> {

	private static final long serialVersionUID = -485013821375916930L;

	/**
	 * Protected SLF4J log for logging stuff.
	 */
	protected final static Logger logger = LoggerFactory
			.getLogger(FenixApplication.class);

	/**
	 * 
	 */
	@Autowired
	private MessageSource messages;

	/**
	 * 
	 */
	@Resource
	private Map<Locale, String> supportedLocales;

	private ListenerList<I18NListener> i18nListeners = new ListenerList<I18NListener>();

	private org.springframework.core.io.Resource guiContextLocation;

	private GenericApplicationContext guiContext;

	private ApplicationContext parentApplicationContext;

	private boolean applicationIsClosing = false;

	/**
	 * 
	 */
	public FenixApplication() {
		if (logger.isDebugEnabled()) {
			logger.debug("Creating new instance of application [" + this + "]");
		}
	}

	/**
	 * 
	 * @param guiContextLocation
	 */
	@Required
	public void setGuiContextLocation(
			org.springframework.core.io.Resource guiContextLocation) {
		this.guiContextLocation = guiContextLocation;
	}

	@Override
	public void init() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing application [" + this + "]");
		}
		// Update the application holder
		ApplicationHolder.setApplication(this);

		// Initialize the GUI context
		guiContext = new GenericApplicationContext(parentApplicationContext);
		{
			guiContext.setDisplayName("GUI Context for [" + this + "]");
			XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(
					guiContext);
			if (logger.isDebugEnabled()) {
				logger.debug("Loading GUI Context from resource ["
						+ guiContextLocation + "]");
			}
			try {
				beanDefinitionReader.loadBeanDefinitions(guiContextLocation);
			} catch (BeanDefinitionStoreException e) {
				logger.error("Error loading GUI context from resource ["
						+ guiContextLocation + "]", e);
				throw e;
			}
			guiContext.refresh();
		}

		// Show the login screen
		if (logger.isDebugEnabled()) {
			logger.debug("Showing login window");
		}
		// Look up the login view from the GUI Context
		final VaadinView loginView = guiContext.getBean(LoginView.BEAN_NAME,
				VaadinView.class);
		setMainWindow(new Window(loginView.getDisplayName(),
				loginView.getViewComponent()));
	}

	@Override
	public void close() {
		if (logger.isDebugEnabled()) {
			logger.debug("Closing application [" + this + "]");
		}
		/*
		 * Update the flag so that this method is not accidentally called
		 * another time when the GUI context closes (see the event handler at
		 * the bottom of this class).
		 */
		applicationIsClosing = true;
		// Close the GUI context
		guiContext.close();
		// Clear the security context to log the user out
		SecurityContextHolder.clearContext();
		// Clear the application holder
		ApplicationHolder.clearApplication();
		super.close();
	}

	/**
	 * Gets the currently logged in user from {@link SecurityContextHolder}. If
	 * this value is <code>null</code>, no user has been logged in yet.
	 * 
	 * @return an {@link Authentication} instance.
	 */
	@Override
	public Object getUser() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public String getMessage(String code, Object... args) {
		try {
			return messages.getMessage(code, args, getLocale());
		} catch (NoSuchMessageException e) {
			if (logger.isWarnEnabled()) {
				logger.warn("Message code '" + code + "' not found");
			}
			return "Invalid message code";
		}
	}

	@Override
	public Set<Locale> getSupportedLocales() {
		return Collections.unmodifiableSet(supportedLocales.keySet());
	}

	@Override
	public String getLocaleDisplayName(Locale locale) {
		String name = supportedLocales.get(locale);
		if (name == null) {
			name = "Unsupported Locale";
		}
		return name;
	}

	@Override
	public Locale getLocale() {
		/*
		 * Fetch the locale resolved by Spring in the application context.
		 */
		return LocaleContextHolder.getLocale();
	}

	@Override
	public void setLocale(final Locale locale) {
		final Locale oldLocale = getLocale();
		LocaleContextHolder.setLocale(locale);
		if (oldLocale != null && !oldLocale.equals(locale)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Application locale has been changed from ["
						+ oldLocale + "] to [" + locale
						+ "], notifying listeners");
			}
			i18nListeners.visitListeners(new ListenerVisitor<I18NListener>() {

				@Override
				public void visit(I18NListener listener) {
					listener.localeChanged(FenixApplication.this, oldLocale,
							locale);
				}
			});
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.parentApplicationContext = applicationContext;
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		/*
		 * This makes it possible for any bean in the GUI Context to close the
		 * application simply by closing the GUI Context. We have to check that
		 * the application has not been explicitly closed by Vaadin first,
		 * though.
		 */
		if (!applicationIsClosing && guiContext != null
				&& guiContext.isActive()) {
			guiContext.close();
		}
	}

	@Override
	public void addListener(I18NListener listener) {
		i18nListeners.addListener(listener);
	}

	@Override
	public void removeListener(I18NListener listener) {
		i18nListeners.removeListener(listener);
	}
}
