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
package fenix.base.ui.servlet;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

import fenix.base.ui.FenixApplication;

/**
 * Vaadin application servlet that uses CDI to inject new instnaces of
 * {@link FenixApplication}.
 * 
 * @author Petter Holmström
 */
@WebServlet(urlPatterns = "/*")
public class FenixApplicationServlet extends AbstractApplicationServlet {

	private static final long serialVersionUID = 8481126159672419788L;

	@Inject
	private Instance<FenixApplication> app;

	@Override
	protected Application getNewApplication(HttpServletRequest request)
			throws ServletException {
		return app.get();
	}

	@Override
	protected Class<? extends Application> getApplicationClass()
			throws ClassNotFoundException {
		return FenixApplication.class;
	}
}
