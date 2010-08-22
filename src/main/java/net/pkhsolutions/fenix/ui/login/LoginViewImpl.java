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
package net.pkhsolutions.fenix.ui.login;

import java.util.Locale;

import net.pkhsolutions.fenix.i18n.I18N;
import net.pkhsolutions.fenix.ui.mvp.AbstractView;
import net.pkhsolutions.fenix.ui.mvp.VaadinView;

import org.springframework.stereotype.Component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.themes.BaseTheme;

/**
 * This is a Vaadin implementation of the {@link LoginView} interface. Please
 * note the use of the {@link Component @Component} annotation and the
 * {@link LoginView#BEAN_NAME} constant. Also note that this view, in addition
 * to the {@link LoginView} interface, also implements the {@link VaadinView}
 * interface.
 * 
 * @author Petter Holmström
 */
@Component(LoginView.BEAN_NAME)
public final class LoginViewImpl extends AbstractView<LoginView, LoginPresenter>
		implements LoginView, VaadinView {

	private static final long serialVersionUID = -8071814016264582837L;

	private HorizontalLayout viewLayout;
	private TextField username;
	private TextField password;
	private Button loginButton;
	private Panel loginPanel;

	@Override
	public ComponentContainer getViewComponent() {
		return viewLayout;
	}

	@Override
	public String getDisplayName() {
		return getI18n().getMessage("loginView.displayName");
	}

	@Override
	public String getDescription() {
		return getI18n().getMessage("loginView.description");
	}

	@Override
	public void showBadCredentials() {
		getViewComponent().getWindow().showNotification(
				getI18n().getMessage("loginView.err.badCredentials.title"),
				getI18n().getMessage("loginView.err.badCredentials.descr"),
				Notification.TYPE_WARNING_MESSAGE);
	}

	@Override
	public void showAccountDisabled() {
		getViewComponent().getWindow().showNotification(
				getI18n().getMessage("loginView.err.accountDisabled.title"),
				getI18n().getMessage("loginView.err.accountDisabled.descr"),
				Notification.TYPE_WARNING_MESSAGE);
	}

	@Override
	public void showAccountLocked() {
		getViewComponent().getWindow().showNotification(
				getI18n().getMessage("loginView.err.accountLocked.title"),
				getI18n().getMessage("loginView.err.accountLocked.descr"),
				Notification.TYPE_WARNING_MESSAGE);
	}

	@Override
	public void clearForm() {
		if (logger.isDebugEnabled()) {
			logger.debug("Clearing form");
		}
		password.setValue("");
		username.setValue("");
	}

	@Override
	@SuppressWarnings("serial")
	protected void initView() {
		loginPanel = new Panel();
		((VerticalLayout) loginPanel.getContent()).setSpacing(true);

		username = new TextField();
		username.setWidth("100%");
		loginPanel.addComponent(username);

		password = new TextField();
		password.setSecret(true);
		password.setWidth("100%");
		loginPanel.addComponent(password);

		loginButton = new Button();
		loginButton.setStyleName("primary");
		// TODO Make it possible to submit the form by pressing <Enter> in any
		// of the text fields
		loginPanel.addComponent(loginButton);
		((VerticalLayout) loginPanel.getContent()).setComponentAlignment(
				loginButton, Alignment.MIDDLE_RIGHT);
		loginButton.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				getPresenter().attemptLogin((String) username.getValue(),
						(String) password.getValue());
			}
		});

		HorizontalLayout languages = new HorizontalLayout();
		languages.setSpacing(true);
		final Button.ClickListener languageListener = new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				final Locale locale = (Locale) event.getButton().getData();
				getPresenter().changeLocale(locale);
			}
		};
		for (Locale locale : getI18n().getSupportedLocales()) {
			final Button languageButton = new Button(getI18n()
					.getLocaleDisplayName(locale));
			languageButton.setStyleName(BaseTheme.BUTTON_LINK);
			languageButton.setData(locale);
			languageButton.addListener(languageListener);
			languages.addComponent(languageButton);
		}
		loginPanel.addComponent(languages);

		loginPanel.setWidth("300px");

		viewLayout = new HorizontalLayout();
		viewLayout.addComponent(loginPanel);
		viewLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
		viewLayout.setSizeFull();
		viewLayout.setMargin(true);

		updateCaptions();
	}

	/**
	 * Updates all the captions in the view with fresh messages from the
	 * {@link I18N} instance.
	 */
	private void updateCaptions() {
		loginPanel.setCaption(getI18n().getMessage("loginView.form.title"));
		username.setCaption(getI18n().getMessage("loginView.form.username"));
		password.setCaption(getI18n().getMessage("loginView.form.password"));
		loginButton.setCaption(getI18n().getMessage("loginView.form.button"));
	}

	@Override
	public void localeChanged(I18N source, Locale oldLocale, Locale newLocale) {
		updateCaptions();
	}
}
