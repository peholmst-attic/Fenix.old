/*
 * Copyright (c) 2011 Petter Holmström
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.peholmst.fenix.ui.login;

import javax.enterprise.context.SessionScoped;

import com.github.peholmst.fenix.ui.FenixTheme;
import com.github.peholmst.fenix.ui.components.ChangeLanguageComponent;
import com.github.peholmst.fenix.ui.util.FenixView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SessionScoped
public class LoginViewImpl extends FenixView<LoginView, LoginPresenter>
        implements LoginView {

    private static final long serialVersionUID = -1465483721468298278L;

    private HorizontalLayout viewLayout;
    private Label headerLabel;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private VerticalLayout loginPanel;

    @Override
    public void initView() {
        createViewComponents();
    }

    private void createViewComponents() {
        loginPanel = new VerticalLayout();
        loginPanel.setSpacing(true);

        headerLabel = new Label();
        headerLabel.addStyleName(FenixTheme.LABEL_H1);
        loginPanel.addComponent(headerLabel);

        usernameField = new TextField();
        usernameField.setWidth("100%");
        loginPanel.addComponent(usernameField);

        passwordField = new PasswordField();
        passwordField.setWidth("100%");
        loginPanel.addComponent(passwordField);

        loginButton = new Button();
        loginButton.setStyleName(FenixTheme.BUTTON_DEFAULT);
        // TODO Make it possible to submit the form by pressing <Enter> in any
        // of the text fields
        loginPanel.addComponent(loginButton);
        loginPanel.setComponentAlignment(loginButton, Alignment.MIDDLE_RIGHT);

        ChangeLanguageComponent changeLanguageComponent = new ChangeLanguageComponent(
                getI18N());
        loginPanel.addComponent(changeLanguageComponent);

        loginPanel.setWidth("300px");

        viewLayout = new HorizontalLayout();
        viewLayout.addComponent(loginPanel);
        viewLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        viewLayout.setSizeFull();
        viewLayout.setMargin(true);
        viewLayout.setStyleName(FenixTheme.LAYOUT_BLACK);

        updateCaptions();
    }

    @Override
    protected void updateCaptions() {
        headerLabel.setCaption(getI18N().getMessage("loginView.displayName"));
        usernameField.setCaption(getI18N()
                .getMessage("loginView.form.username"));
        passwordField.setCaption(getI18N()
                .getMessage("loginView.form.password"));
        loginButton.setCaption(getI18N().getMessage("loginView.form.button"));
    }

    @Override
    public String getDescription() {
        return getI18N().getMessage("loginView.description");
    }

    @Override
    public String getDisplayName() {
        return getI18N().getMessage("loginView.displayName");
    }

    @Override
    public ComponentContainer getViewComponent() {
        return viewLayout;
    }

    @Override
    public void showBadCredentials() {
        // TODO Auto-generated method stub

    }

    @Override
    public void showAccountDisabled() {
        // TODO Auto-generated method stub

    }

    @Override
    public void showAccountLocked() {
        // TODO Auto-generated method stub

    }

    @Override
    public void clearForm() {
        // TODO Auto-generated method stub

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }
}
