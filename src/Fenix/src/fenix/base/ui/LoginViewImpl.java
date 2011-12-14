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

import java.util.Locale;

import com.github.peholmst.mvp4vaadin.i18n.AbstractI18NViewComponent;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

import fenix.base.ui.theme.Fenix;

/**
 * Implementation of {@link LoginView}.
 * 
 * @author Petter Holmström
 */
public class LoginViewImpl extends
		AbstractI18NViewComponent<LoginView, LoginPresenter> implements
		LoginView {

	private static final long serialVersionUID = 7903068250623134816L;

	private HorizontalLayout viewLayout;

	private Label header;

	private TextField username;

	private PasswordField password;

	private Button login;

	public LoginViewImpl() {
		init();
	}
	
	@Override
	public void showLoginFailed() {
		getWindow().showNotification(
				getI18N().getMessage("loginView.loginFailed.caption"),
				getI18N().getMessage("loginView.loginFailed.description"));
	}

	@Override
	public void clearForm() {
		username.setValue("");
		password.setValue("");
		username.focus();
	}

	@SuppressWarnings("serial")
	@Override
	protected Component createCompositionRoot() {
		VerticalLayout loginPanel = new VerticalLayout();
		loginPanel.setSpacing(true);
		loginPanel.setWidth("300px");

		header = new Label();
		header.addStyleName(Fenix.LABEL_H1);
		loginPanel.addComponent(header);

		username = new TextField();
		username.setWidth("100%");
		loginPanel.addComponent(username);

		password = new PasswordField();
		password.setWidth("100%");
		loginPanel.addComponent(password);

		login = new Button();
		login.setClickShortcut(KeyCode.ENTER);
		login.addStyleName(Fenix.BUTTON_DEFAULT);
		login.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getPresenter().attemptLogin((String) username.getValue(),
						(String) password.getValue());
			}
		});
		loginPanel.addComponent(login);
		loginPanel.setComponentAlignment(login, Alignment.BOTTOM_RIGHT);

		viewLayout = new HorizontalLayout();
		viewLayout.addComponent(loginPanel);
		viewLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		viewLayout.setSizeFull();
		viewLayout.addStyleName(Fenix.LAYOUT_BLACK);
		setSizeFull();

		return viewLayout;
	}

	@Override
	protected void updateInternationalizedData(Locale locale) {
		username.setCaption(getI18N().getMessage("loginView.username.caption"));
		password.setCaption(getI18N().getMessage("loginView.password.caption"));
		login.setCaption(getI18N().getMessage("loginView.login.caption"));
		header.setValue(getI18N().getMessage("loginView.header"));
	}

}
