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
package net.pkhsolutions.fenix.ui.main.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.pkhsolutions.fenix.domain.ProducerFD;
import net.pkhsolutions.fenix.domain.Report;
import net.pkhsolutions.fenix.domain.ReportType;
import net.pkhsolutions.fenix.i18n.I18N;
import net.pkhsolutions.fenix.ui.main.reports.ReportFormView;
import net.pkhsolutions.fenix.ui.main.reports.ReportFormViewImpl;
import net.pkhsolutions.fenix.ui.mvp.AbstractView;
import net.pkhsolutions.fenix.ui.mvp.VaadinView;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class HomeViewImpl extends AbstractView<HomeView, HomePresenter>
		implements HomeView, VaadinView {

	public HomeViewImpl(I18N i18n) {
		super(i18n);
	}

	private static final long serialVersionUID = 2314588940522545090L;

	private VerticalLayout viewLayout;
		
	@Override
	public String getDisplayName() {
		return "Home"; // TODO localize
	}

	@Override
	public String getDescription() {
		return "Home description"; // TODO localize
	}

	@Override
	public ComponentContainer getViewComponent() {
		if (viewLayout == null) {
			// Lazily create components
			createComponents();
		}
		return viewLayout;
	}
	
	private void createComponents() {
		viewLayout = new VerticalLayout();
		viewLayout.setSizeFull();
		viewLayout.setMargin(true);
		viewLayout.addComponent(new Label("Home screen"));
		
		viewLayout.addComponent(new Button("Go to Report Browser"));
		viewLayout.addComponent(new Button("Go to Report Form", new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Locale sv = new Locale("sv");
				
				Report report = new Report();
				report.setProducerFD(new ProducerFD());
				report.getProducerFD().getName().setValue(sv, "Pargas Frivilliga Brandkår");
				report.getProducerFD().getName().setDefaultLanguage(sv);
				
				getViewController().goToView(new ReportFormViewImpl(getI18n()), ReportFormView.REPORT_USER_DATA_KEY, report);
			}
		}));
	}

	@Override
	protected HomePresenter createPresenter() {
		return new HomePresenter(this);
	}

	@Override
	protected void initView() {
		// Do nothing, components will be created lazily
	}
	
}
