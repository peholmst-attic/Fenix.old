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

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

/**
 * A servlet that creates new Fenix Application instances using CDI.
 * 
 * @author Petter Holmström
 */
@WebServlet(urlPatterns = "/*")
public class FenixApplicationServlet extends AbstractApplicationServlet {

    private static final long serialVersionUID = 4191127066216597908L;

    @Inject
    private Instance<FenixApplication> fenixApplication;

    @Override
    protected Application getNewApplication(HttpServletRequest request)
            throws ServletException {
        return fenixApplication.get();
    }

    @Override
    protected Class<? extends Application> getApplicationClass()
            throws ClassNotFoundException {
        return FenixApplication.class;
    }

}
