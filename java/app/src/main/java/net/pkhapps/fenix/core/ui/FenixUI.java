/*
 * Fenix
 * Copyright (C) 2014 Petter Holmström
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
package net.pkhapps.fenix.core.ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.VaadinUI;
import org.vaadin.spring.navigator.SpringViewProvider;
import org.vaadin.spring.security.Security;
import org.vaadin.spring.stuff.sidebar.SideBar;

/**
 * @author Petter Holmström
 */
@VaadinUI
public class FenixUI extends UI {

    @Autowired
    Security security;

    @Autowired
    SpringViewProvider viewProvider;

    @Autowired
    SideBar sideBar;

    @Autowired
    TopBar topBar;

    @Autowired
    ErrorView errorView;

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        setContent(layout);

        layout.addComponent(topBar);

        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        splitPanel.setSizeFull();
        layout.addComponent(splitPanel);
        layout.setExpandRatio(splitPanel, 1f);

        splitPanel.setFirstComponent(sideBar);
        splitPanel.setSplitPosition(120, Unit.PIXELS);

        Navigator navigator = new Navigator(this, view -> {
            splitPanel.setSecondComponent((Component) view);
        });
        navigator.setErrorView(errorView);
        navigator.addProvider(viewProvider);
    }

}
