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
import net.pkhsolutions.fenix.ui.mvp.AbstractView;
import net.pkhsolutions.fenix.ui.mvp.VaadinView;
import net.pkhsolutions.fenix.util.VisitableList;
import net.pkhsolutions.fenix.util.VisitableList.Visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * This is the main Vaadin application class for Fenix. It is designed to be
 * configured inside a Spring application context using <a href=
 * "http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-classpath-scanning"
 * >classpath scanning</a> (as can be deduced from the {@link Component
 * @Component} annotation).
 * <p>
 * The class requires the following beans to be available for injection:
 * <ul>
 * <li>A {@link MessageSource}.</li>
 * <li>A &lt;{@link Locale}, String&gt; {@link Map} named
 * <code>supportedLocales</code> (see {@link #getSupportedLocales()}).</li>
 * <li>A {@link VaadinView} instance named {@link LoginView#BEAN_NAME}
 * implementing the login view.</li>
 * <li>TODO A Main view</li>
 * </ul>
 * <p>
 * This class also implements the {@link I18N} interface. Thus, any views
 * extending {@link AbstractView} will automatically have access to
 * internationalized messages served by this application instance.
 * 
 * @see Presenter
 * @see VaadinView
 * @see View
 * @see AbstractView
 * @see net.pkhsolutions.fenix.servlet.SpringApplicationServlet
 *      SpringApplicationServlet
 * @author Petter Holmström
 */
@Component
public class FenixApplication extends Application implements I18N,
		ApplicationListener<ContextClosedEvent> {

	private static final long serialVersionUID = -485013821375916930L;

	/**
	 * Logger for logging stuff.
	 */
	protected final static Logger logger = LoggerFactory
			.getLogger(FenixApplication.class);

	/**
	 * Message source for internationalization, automatically injected by the
	 * Spring application context.
	 * <p>
	 * See the <a href=
	 * "http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-resource-annotation"
	 * >Spring documentation</a> for more information about how the
	 * {@link Resource @Resource} annotation is handled in Spring.
	 */
	@Resource
	private MessageSource messages;

	/**
	 * Map of supported locales and their respective display names,
	 * automatically injected by the Spring application context.
	 */
	@Resource
	private Map<Locale, String> supportedLocales;

	/**
	 * The login view, automatically injected by the Spring application context.
	 */
	@Resource(name = LoginView.BEAN_NAME)
	private VaadinView loginView;

	/**
	 * The application context instance itself, also automatically injected.
	 */
	@Resource
	private ConfigurableApplicationContext applicationContext;

	private VisitableList<I18NListener> i18nListeners = new VisitableList<I18NListener>();

	/**
	 * This flag is set when {@link #close()} is called. The reason for this is
	 * that the application can be closed in two ways: by calling
	 * <code>close()</code> or by closing the application context. Furthermore,
	 * when the application context is closed, <code>close()</code is called,
	 * and likevise, when <code>close()</code> is called, the application
	 * context is closed. The use of this flag prevents any of the close
	 * operations from being called more than once.
	 */
	private boolean applicationIsClosing = false;

	/**
	 * Creates a new <code>FenixApplication</code>. The application has to be
	 * initialized by calling {@link #init()}. As this application has to be
	 * configured inside a Spring application, clients should never have to
	 * invoke this constructor manually.
	 */
	public FenixApplication() {
		if (logger.isDebugEnabled()) {
			/*
			 * This log entry is useful for debugging situations where too many
			 * instances of the applications are created. This may happen if the
			 * Spring application context has been incorrectly configured, e.g.
			 * if the scope of this application is prototype instead of
			 * singleton.
			 */
			logger.debug("Creating new instance of application [" + this + "]");
		}
	}

	@Override
	public void init() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing application [" + this + "]");
		}
		// Show the login screen
		if (logger.isDebugEnabled()) {
			logger.debug("Showing login window");
		}
		// Look up the login view from the GUI Context
		setMainWindow(new Window(loginView.getDisplayName(),
				loginView.getViewComponent()));
	}

	// FIXME Detect when the user has logged in and move to the main view

	@Override
	public void close() {
		if (logger.isDebugEnabled()) {
			logger.debug("Closing application [" + this + "]");
		}
		/*
		 * Update the flag so that this method is not accidentally called
		 * another time when the application context closes (see the event
		 * handler at the bottom of this class).
		 */
		applicationIsClosing = true;
		// Close the application context
		applicationContext.close();
		// Clear the security context to log the user out
		SecurityContextHolder.clearContext();
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
			i18nListeners.visitList(new Visitor<I18NListener>() {

				@Override
				public void visit(I18NListener listener) {
					listener.localeChanged(FenixApplication.this, oldLocale,
							locale);
				}
			});
		}
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		/*
		 * This makes it possible for any bean in the application context to
		 * close the application simply by closing the application context. We
		 * have to check that the application has not been explicitly closed by
		 * Vaadin first, though.
		 */
		if (!applicationIsClosing && applicationContext != null
				&& applicationContext.isActive()) {
			applicationContext.close();
		}
	}

	@Override
	public void addListener(I18NListener listener) {
		i18nListeners.add(listener);
	}

	@Override
	public void removeListener(I18NListener listener) {
		i18nListeners.remove(listener);
	}
}
