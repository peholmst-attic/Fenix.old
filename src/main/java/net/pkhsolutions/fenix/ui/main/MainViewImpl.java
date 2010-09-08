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
package net.pkhsolutions.fenix.ui.main;

import java.util.Locale;

import net.pkhsolutions.fenix.i18n.I18N;
import net.pkhsolutions.fenix.ui.FenixTheme;
import net.pkhsolutions.fenix.ui.mvp.AbstractView;
import net.pkhsolutions.fenix.ui.mvp.VaadinView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.vaadin.notifique.Notifique;
import org.vaadin.notifique.Notifique.Message;

import com.vaadin.Application;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * TODO Document me!
 * 
 * @author petter
 * 
 */
@Component(MainView.BEAN_NAME)
public final class MainViewImpl extends AbstractView<MainView, MainPresenter>
		implements MainView, VaadinView {

	private static final long serialVersionUID = 2659284453464649349L;

	@Autowired
	private Application application;

	private VerticalLayout viewLayout;

	private ComponentContainer header;
	
	private Button changePasswordButton;

	private Button logoutButton;

	private Button changeLanguageButton;

	private Label userLabel;
	
	private Notifique notifications;

	@Autowired
	public MainViewImpl(MainPresenter presenter) {
		super(presenter);
	}

	@Override
	public String getDisplayName() {
		return getI18n().getMessage("mainView.displayName",
				((Authentication) application.getUser()).getName());
	}

	@Override
	public String getDescription() {
		return getI18n().getMessage("mainView.description");
	}

	@Override
	protected void initView() {
		// Do nothing - the components will be created lazily when needed.
	}

	@Override
	public ComponentContainer getViewComponent() {
		if (viewLayout == null) {
			// Lazily create the components
			createComponents();
		}
		return viewLayout;
	}

	private void createComponents() {
		header = createHeader();

		viewLayout = new VerticalLayout();
		viewLayout.setSizeFull();
		viewLayout.addComponent(header);
		
		notifications = new Notifique(false);
		notifications.setWidth("100%");
		viewLayout.addComponent(notifications);

		updateLabels();
	}

	@SuppressWarnings("serial")
	private ComponentContainer createHeader() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(false);
		layout.setWidth("100%");

		final Label title = new Label();
		title.setIcon(new ThemeResource("icons/logo.png"));
		layout.addComponent(title);
		layout.setExpandRatio(title, 1.0f);
		layout.setComponentAlignment(title, Alignment.MIDDLE_LEFT);

		final HorizontalLayout menu = new HorizontalLayout();
		menu.setSpacing(true);

		userLabel = new Label();
		userLabel.setContentMode(Label.CONTENT_XHTML);
		menu.addComponent(userLabel);
		menu.setComponentAlignment(userLabel, Alignment.MIDDLE_LEFT);
		userLabel.setSizeUndefined();

		changePasswordButton = new Button();
		changePasswordButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Implement password change
			}
		});
		changePasswordButton.setStyleName(FenixTheme.BUTTON_SMALL);
		menu.addComponent(changePasswordButton);
		menu.setComponentAlignment(changePasswordButton, Alignment.MIDDLE_RIGHT);

		changeLanguageButton = new Button();
		changeLanguageButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Implement language change
			}
		});
		changeLanguageButton.setStyleName(FenixTheme.BUTTON_SMALL);
		menu.addComponent(changeLanguageButton);
		menu.setComponentAlignment(changeLanguageButton, Alignment.MIDDLE_RIGHT);

		logoutButton = new Button();
		logoutButton.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				confirmLogout();
			}
		});
		logoutButton.setStyleName(FenixTheme.BUTTON_SMALL);
		menu.addComponent(logoutButton);
		menu.setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);

		layout.addComponent(menu);
		layout.setComponentAlignment(menu, Alignment.MIDDLE_RIGHT);
		return layout;
	}

	private void updateLabels() {
		userLabel.setValue(getI18n().getMessage("mainView.menu.loggedIn",
				((Authentication) application.getUser()).getName()));
		changePasswordButton.setCaption(getI18n().getMessage(
				"mainView.menu.changePassword.caption"));
		changePasswordButton.setDescription(getI18n().getMessage(
				"mainView.menu.changePassword.descr"));
		changeLanguageButton.setCaption(getI18n().getMessage(
				"mainView.menu.changeLanguage.caption"));
		changeLanguageButton.setDescription(getI18n().getMessage(
				"mainView.menu.changeLanguage.descr"));
		logoutButton.setCaption(getI18n().getMessage(
				"mainView.menu.logout.caption"));
		logoutButton.setDescription(getI18n().getMessage(
				"mainView.menu.logout.descr"));
	}

	@Override
	public void localeChanged(I18N source, Locale oldLocale, Locale newLocale) {
		// It's possible the view has not been created yet when this method is
		// called (as it is lazily initialized)
		if (viewLayout != null) {
			updateLabels();
		}
	}

	private void confirmLogout() {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		
		final Label messageLabel = new Label(getI18n().getMessage("mainView.menu.logout.confirm")); 
		layout.addComponent(messageLabel);		
		layout.setComponentAlignment(messageLabel, Alignment.MIDDLE_RIGHT);
		
		final Button proceedButton = new Button(getI18n().getMessage("mainView.menu.logout.proceed"), new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				getPresenter().logout();
			}
		});
		layout.addComponent(proceedButton);
		layout.setComponentAlignment(proceedButton, Alignment.MIDDLE_RIGHT);
		proceedButton.setStyleName(FenixTheme.BUTTON_SMALL);
		
		final Message[] messages = new Message[1];
		final Button cancelButton = new Button(getI18n().getMessage("mainView.menu.logout.cancel"), new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				messages[0].hide();
			}
		});	
		layout.addComponent(cancelButton);
		layout.setComponentAlignment(cancelButton, Alignment.MIDDLE_RIGHT);
		cancelButton.setStyleName(FenixTheme.BUTTON_SMALL);
		
		messages[0] = notifications.add(null, layout, Notifique.Styles.MAGIC_GRAY, false);
	}
	
	
}
