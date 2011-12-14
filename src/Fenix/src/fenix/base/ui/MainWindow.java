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

import com.github.peholmst.i18n4vaadin.I18N;
import com.github.peholmst.i18n4vaadin.support.I18NWindow;

/**
 * Main window of the Fenix application.
 * 
 * @author Petter Holmström
 */
public class MainWindow extends I18NWindow {

	private static final long serialVersionUID = 8264172423096635587L;
	
	public static final String TABS_STYLE = "main";

	public MainWindow(I18N i18n) {
		super("Fenix", i18n);
		
		LoginViewImpl loginView = new LoginViewImpl();
		setContent(loginView);
		
		/*-
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		setContent(layout);		
		layout.addComponent(new Header());
		
		TabSheet tabs = new TabSheet();
		tabs.setSizeFull();
		layout.addComponent(tabs);
	 	layout.setExpandRatio(tabs, 1.0f);
	 	
	 	tabs.setStyleName(TABS_STYLE);	 	
	 	tabs.addTab(new Label("todo"), "Översikt", null);
	 	tabs.addTab(new Label("todo"), "Medlemsregister", null);
	 	tabs.addTab(new Label("todo"), "Verksamhetsbokföring", null);
	 	tabs.addTab(new Label("todo"), "Planering", null);
	 	tabs.addTab(new Label("todo"), "Ärenden", null);
	 	tabs.addTab(new Label("todo"), "Egna brandkåren", null);*/
	}
}
