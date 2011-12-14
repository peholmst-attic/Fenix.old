/*
 * Fenix
 * Copyright (C) 2011 Petter Holmström
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
package fenix.base.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.peholmst.i18n4vaadin.I18N;
import com.github.peholmst.i18n4vaadin.ResourceBundleI18N;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;

import fenix.base.ui.theme.Fenix;
import fenix.i18n.FenixLocales;

/**
 * Vaadin application class for Fenix.
 * 
 * @author Petter Holmström
 */
public class FenixApplication extends Application implements
		HttpServletRequestListener {

	private static final long serialVersionUID = -7995226256400579276L;

	private static InheritableThreadLocal<FenixApplication> INSTANCE = new InheritableThreadLocal<FenixApplication>();

	private HttpServletRequest currentRequest;

	private I18N i18n;

	@Override
	public void init() {
		INSTANCE.set(this);
		setTheme(Fenix.themeName());
		i18n = new ResourceBundleI18N("fenix.i18n.messages", FenixLocales.SUPPORTED_LOCALES);
		i18n.setCurrentLocale(FenixLocales.DEFAULT_LOCALE);
		MainWindow mainWindow = new MainWindow(i18n);
		setMainWindow(mainWindow);
	}

	/**
	 * Returns the currently active HTTP servlet request, or null if there is no
	 * active request right now.
	 */
	public HttpServletRequest getCurrentRequest() {
		return currentRequest;
	}

	@Override
	public void onRequestStart(HttpServletRequest request,
			HttpServletResponse response) {
		this.currentRequest = request;
		INSTANCE.set(this);
	}

	@Override
	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {
		this.currentRequest = null;
		INSTANCE.remove();
	}

	/**
	 * Returns the FenixApplication instance for the current thread. If no
	 * instance has been bound, an IllegalStateException is thrown. Thus, this
	 * method never returns null.
	 */
	public static FenixApplication getInstance() {
		final FenixApplication app = INSTANCE.get();
		if (app == null) {
			throw new IllegalStateException(
					"No FenixApplication instance has ben bound to the current thread");
		}
		return app;
	}

}
