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

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

import fenix.base.ui.theme.Fenix;

/**
 * Header component of the MainWindow.
 * 
 * @author Petter Holmström
 */
public class Header extends CustomComponent {

	private static final long serialVersionUID = 330699728686508209L;

	public static final String HEADER_STYLE = "header";

	public static final String APP_TITLE_STYLE = "app-title";

	public static final String CURRENT_USER_STYLE = "current-user";

	public static final String BUTTONS_LAYOUT_STYLE = "button-layout";

	private HorizontalLayout layout;

	private HorizontalLayout buttonsLayout;

	private Label appTitle;

	private ComboBox fireDepartment;

	private Label currentUser;

	private Button myAccount;

	private Button logout;

	public Header() {
		setStyleName(HEADER_STYLE);
		layout = new HorizontalLayout();
		layout.setWidth("100%");
		layout.setHeight("45px");
		layout.setMargin(false);
		layout.setSpacing(true);
		setCompositionRoot(layout);
		setWidth("100%");

		createAppTitle();
		createFireDepartment();
		createCurrentUser();
		createButtons();
	}

	private void createAppTitle() {
		appTitle = new Label("Fenix");
		appTitle.setStyleName(APP_TITLE_STYLE);
		layout.addComponent(appTitle);
		layout.setExpandRatio(appTitle, 1.0f);
		layout.setComponentAlignment(appTitle, Alignment.MIDDLE_LEFT);
	}

	private void createFireDepartment() {
		fireDepartment = new ComboBox();
		fireDepartment.addItem("Kalles Brandkår");
		fireDepartment.addItem("Pelles Brandkår");
		fireDepartment.setNullSelectionAllowed(false);
		fireDepartment.setValue("Kalles Brandkår");
		layout.addComponent(fireDepartment);
		layout.setComponentAlignment(fireDepartment, Alignment.MIDDLE_RIGHT);
	}

	private void createCurrentUser() {
		currentUser = new Label("Kalle Kårchef");
		currentUser.setSizeUndefined();
		currentUser.setStyleName(CURRENT_USER_STYLE);
		layout.addComponent(currentUser);
		layout.setComponentAlignment(currentUser, Alignment.MIDDLE_RIGHT);
	}

	private void createButtons() {
		buttonsLayout = new HorizontalLayout();
		buttonsLayout.setStyleName(BUTTONS_LAYOUT_STYLE);
		buttonsLayout.setHeight("100%");
		layout.addComponent(buttonsLayout);
		layout.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_RIGHT);
		createMyAccount();
		createLogout();
	}

	private void createMyAccount() {
		myAccount = new Button("Mitt konto");
		myAccount.addStyleName(Fenix.BUTTON_LINK);
		myAccount.setHeight("100%");
		buttonsLayout.addComponent(myAccount);
	}

	private void createLogout() {
		logout = new Button("Logga ut");
		logout.addStyleName(Fenix.BUTTON_LINK);
		logout.setHeight("100%");
		buttonsLayout.addComponent(logout);
	}
}
