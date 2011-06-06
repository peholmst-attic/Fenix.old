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
import net.pkhsolutions.fenix.ui.login.LoginViewImpl;
import net.pkhsolutions.fenix.ui.login.UserLoggedInEvent;
import net.pkhsolutions.fenix.ui.main.MainViewImpl;
import net.pkhsolutions.fenix.ui.main.UserLoggedOutEvent;
import net.pkhsolutions.fenix.ui.mvp.ViewEvent;
import net.pkhsolutions.fenix.ui.mvp.ViewListener;
import net.pkhsolutions.fenix.util.VisitableList;
import net.pkhsolutions.fenix.util.VisitableList.Visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.Application;
import com.vaadin.ui.Window;

/**
 * This is the main Vaadin application class for Fenix. It is designed to be
 * configured by a Spring application context using AspectJ (as can be deduced from the {@link Configurable
 * @Configurable} annotation).
 * <p>
 * The class requires the following beans to be available for injection:
 * <ul>
 * <li>A {@link MessageSource}.</li>
 * <li>A &lt;{@link Locale}, String&gt; {@link Map} named
 * <code>supportedLocales</code> (see {@link #getSupportedLocales()}).</li>
 * </ul>
 * <p>
 * This class also implements the {@link I18N} interface.
 * 
 * @author Petter Holmström
 */
@Configurable(preConstruction = true, dependencyCheck = true)
public class FenixApplication extends Application implements I18N {

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
	private transient MessageSource messages;

	/**
	 * Map of supported locales and their respective display names,
	 * automatically injected by the Spring application context.
	 */
	@Resource
	private transient Map<Locale, String> supportedLocales;

	private LoginViewImpl loginView;

	private MainViewImpl mainView;

	private VisitableList<I18NListener> i18nListeners = new VisitableList<I18NListener>();

	/**
	 * Creates a new <code>FenixApplication</code>. The application has to be
	 * initialized by calling {@link #init()}.
	 */
	public FenixApplication() {
		if (logger.isDebugEnabled()) {
			/*
			 * This log entry is useful for debugging situations where too many
			 * instances of the applications are created.
			 */
			logger.debug("Creating new instance of application [" + this + "]");
		}
	}

	@Override
	public void init() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing application [" + this + "]");
		}
		setTheme(FenixTheme.themeName());
		// Show the login screen
		createAndShowLoginView();
	}

	@SuppressWarnings("serial")
	private void createAndShowLoginView() {
		if (logger.isDebugEnabled()) {
			logger.debug("Showing login window");
		}
		loginView = new LoginViewImpl(this);
		loginView.addListener(new ViewListener() {
			@Override
			public void onViewEvent(ViewEvent event) {
				if (event instanceof UserLoggedInEvent) {
					SecurityContextHolder.getContext().setAuthentication(
							((UserLoggedInEvent) event).getAuthentication());
					createAndShowMainView();
				}
			}
		});
		setMainWindow(new Window(loginView.getDisplayName(),
				loginView.getViewComponent()));

	}

	@SuppressWarnings("serial")
	private void createAndShowMainView() {
		if (logger.isDebugEnabled()) {
			logger.debug("Showing main window");
		}
		// Remove the old login window...
		removeWindow(getMainWindow());
		loginView.destroy();
		loginView = null;
		// ... and show the new main window
		mainView = new MainViewImpl(this, this);
		mainView.addListener(new ViewListener() {

			@Override
			public void onViewEvent(ViewEvent event) {
				if (event instanceof UserLoggedOutEvent) {
					close();
				}
			}

		});
		setMainWindow(new Window(mainView.getDisplayName(),
				mainView.getViewComponent()));
	}

	@Override
	public void close() {
		if (logger.isDebugEnabled()) {
			logger.debug("Closing application [" + this + "]");
		}
		// Clear the security context to log the user out
		SecurityContextHolder.clearContext();
		// Dispose of the views
		if (loginView != null) {
			loginView.destroy();
			loginView = null;
		}
		if (mainView != null) {
			mainView.destroy();
			mainView = null;
		}
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
		 * Fetch the locale resolved by Spring.
		 */
		return LocaleContextHolder.getLocale();
	}

	@Override
	public void setLocale(final Locale locale) {
		// TODO Translate Vaadin system messages
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
	public void addListener(I18NListener listener) {
		i18nListeners.add(listener);
	}

	@Override
	public void removeListener(I18NListener listener) {
		i18nListeners.remove(listener);
	}
}
