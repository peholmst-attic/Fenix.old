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
package com.github.peholmst.fenix.ui;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.peholmst.fenix.common.SystemConstants;
import com.github.peholmst.fenix.ui.login.LoginViewImpl;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window;

/**
 * 
 * @author peholmst
 * 
 */
@SessionScoped
public class FenixApplication extends Application implements
        HttpServletRequestListener {

    private static final long serialVersionUID = 9218145495412462275L;

    private static final Logger log = LoggerFactory
            .getLogger(FenixApplication.class);

    @Inject
    private LoginViewImpl loginView;

    private HttpServletRequest currentRequest;

    @Override
    public void init() {
        log.debug("Initializing application {}", this);
        setTheme(FenixTheme.themeName());
        showLoginView();
    }

    private void showLoginView() {
        Window loginWindow = new Window(SystemConstants.APPLICATION_TITLE,
                loginView.getViewComponent());
        setMainWindow(loginWindow);
    }

    @Override
    public void onRequestStart(HttpServletRequest request,
            HttpServletResponse response) {
        this.currentRequest = request;
    }

    @Override
    public void onRequestEnd(HttpServletRequest request,
            HttpServletResponse response) {
        this.currentRequest = null;
    }

}
