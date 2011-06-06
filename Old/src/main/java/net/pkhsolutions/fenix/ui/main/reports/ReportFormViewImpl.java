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
package net.pkhsolutions.fenix.ui.main.reports;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.DateField;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import net.pkhsolutions.fenix.domain.Report;
import net.pkhsolutions.fenix.domain.ReportType;
import net.pkhsolutions.fenix.i18n.I18N;
import net.pkhsolutions.fenix.ui.FenixTheme;
import net.pkhsolutions.fenix.ui.mvp.AbstractView;
import net.pkhsolutions.fenix.ui.mvp.VaadinView;

public class ReportFormViewImpl extends
		AbstractView<ReportFormView, ReportFormPresenter> implements
		ReportFormView, VaadinView {

	private static final long serialVersionUID = -1587523077863515139L;

	private VerticalLayout viewLayout;

	private Label header;

	private ReportForm reportForm;

	private Table participationTable;
	
	private Label producerFDLabel;

	private ComboBox reportTypeCombo;

	private List<ReportType> reportTypes;

	private Report report;

	public ReportFormViewImpl(I18N i18n) {
		super(i18n);
	}

	@Override
	public String getDisplayName() {
		return "Report Form"; // TODO Localize
	}

	@Override
	public String getDescription() {
		return "Report Form Description"; // TODO Localize
	}

	@Override
	protected ReportFormPresenter createPresenter() {
		return new ReportFormPresenter(this);
	}

	@Override
	protected void initView() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing ReportFormView");
		}
		viewLayout = new VerticalLayout();
		viewLayout.setSizeFull();
		viewLayout.setMargin(true);
		viewLayout.setSpacing(true);

		header = new Label();
		header.setCaption("Report Form"); // TODO Localize
		header.setStyleName(FenixTheme.LABEL_H1);
		header.setSizeUndefined();
		viewLayout.addComponent(header);
		
		HorizontalLayout topBar = new HorizontalLayout();
		topBar.setSpacing(true);
		viewLayout.addComponent(topBar);
		
		reportTypeCombo = new ComboBox();
		reportTypeCombo.setCaption("Report Type"); // TODO Localize!
		reportTypeCombo.setInputPrompt("Please select a report type"); // TODO Localize!
		if (reportTypes != null) {
			reportTypeCombo.setContainerDataSource(new IndexedContainer(
					reportTypes)); // TODO Create a container that supports
									// localized strings!
		}
		topBar.addComponent(reportTypeCombo);

		PopupDateField beginDate = new PopupDateField("Start date");
		beginDate.setResolution(DateField.RESOLUTION_DAY);
		topBar.addComponent(beginDate);

		TextField beginTime = new TextField("Start time");
		beginTime.setWidth("70px");
		topBar.addComponent(beginTime);
		
		PopupDateField endDate = new PopupDateField("End date");
		endDate.setResolution(DateField.RESOLUTION_DAY);
		topBar.addComponent(endDate);

		TextField endTime = new TextField("End time");
		endTime.setWidth("70px");
		topBar.addComponent(endTime);
		
		TextField duration = new TextField("Duration");
		duration.setWidth("70px");
		topBar.addComponent(duration);
		
		TabSheet tabSheet = new TabSheet();
		tabSheet.setSizeFull();
		//tabSheet.setStyleName(FenixTheme.TABSHEET_MINIMAL);
		viewLayout.addComponent(tabSheet);
		viewLayout.setExpandRatio(tabSheet, 1);
		VerticalLayout generalLayout = new VerticalLayout();
		generalLayout.setSizeFull();
		generalLayout.setSpacing(true);
		generalLayout.setMargin(true);
		tabSheet.addTab(generalLayout, "General", null);
		{
			TextField subject = new TextField("Subject");
			subject.setWidth("100%");
			generalLayout.addComponent(subject);
			
			TextField supervisors = new TextField("Supervisors");
			supervisors.setWidth("100%");
			generalLayout.addComponent(supervisors);
			
			TextField summary = new TextField("Summary");
			summary.setSizeFull();
			generalLayout.addComponent(summary);
			generalLayout.setExpandRatio(summary, 1);
			
			TextField location = new TextField("Location");
			location.setWidth("100%");
			generalLayout.addComponent(location);			
		}
		tabSheet.addTab(new Label("blah"), "Participants", null);
		tabSheet.addTab(new Label("blah"), "Attachements", null);
		tabSheet.addTab(new Label("blah"), "History", null);
		
		/*
		HorizontalLayout innerLayout = new HorizontalLayout();
		innerLayout.setSizeFull();
		innerLayout.setSpacing(true);
		viewLayout.addComponent(innerLayout);
		viewLayout.setExpandRatio(innerLayout, 1);
		
		reportForm = new ReportForm();
		reportForm.setWidth("400px");
		innerLayout.addComponent(reportForm);
		
		participationTable = new Table();
		participationTable.setSizeFull();
		participationTable.setCaption("Participants");
		innerLayout.addComponent(participationTable);
		innerLayout.setExpandRatio(participationTable, 1);
*/
		HorizontalLayout buttons = new HorizontalLayout();
		buttons.setSpacing(true);
		viewLayout.addComponent(buttons);
		buttons.addComponent(new Button("Save Draft"));
		buttons.addComponent(new Button("Save Complete Report"));
		buttons.addComponent(new Button("Approve"));
		buttons.addComponent(new Button("Deny"));
		buttons.addComponent(new Button("Request Complements"));
		buttons.addComponent(new Button("Go Back"));
		
		// TODO fire department
		// TODO stateChangeEntries
		// TODO participants
		// TODO audit log
	}

	@Override
	public ComponentContainer getViewComponent() {
		return viewLayout;
	}

	@Override
	public void setReport(Report report) {
		this.report = report;
		//reportForm.setReportItem(new BeanItem<Report>(report));
	}

	@Override
	public void setReportTypes(List<ReportType> reportTypes) {
		this.reportTypes = reportTypes;
		if (reportTypeCombo != null) {
			reportTypeCombo.setContainerDataSource(new IndexedContainer(
					reportTypes));
		}
	}

	public class ReportFieldFactory extends DefaultFieldFactory {

		private static final long serialVersionUID = -8918717544843379645L;

		public ReportFieldFactory() {
			reportTypeCombo = new ComboBox();
			reportTypeCombo.setCaption("Report Type"); // TODO Localize!
			reportTypeCombo.setWidth("100%");
			reportTypeCombo.setInputPrompt("Please select a report type"); // TODO Localize!
			if (reportTypes != null) {
				reportTypeCombo.setContainerDataSource(new IndexedContainer(
						reportTypes)); // TODO Create a container that supports
										// localized strings!
			}
		}

		// TODO Fix tab order!
		
		@Override
		public Field createField(Item item, Object propertyId,
				Component uiContext) {
			if (propertyId.equals(Report.PROP_REPORT_TYPE)) {
				return reportTypeCombo;
			} else {
				Field f = null;
				if (propertyId.equals(Report.PROP_EVENT_START_DATE)) {
					f = super.createField(item, propertyId, uiContext);
					f.setCaption("Start date");
					f.setWidth("100%");
				} else if (propertyId.equals(Report.PROP_EVENT_START_TIME)) {
					f = new TextField();
					f.setCaption("Start time");
					f.setWidth("100%");
				} else if (propertyId.equals(Report.PROP_EVENT_END_DATE)) {
					f = super.createField(item, propertyId, uiContext);
					f.setCaption("End date");
					f.setWidth("100%");
				} else if (propertyId.equals(Report.PROP_EVENT_END_TIME)) {
					f = new TextField();
					f.setCaption("End time");
					f.setWidth("100%");
				} else if (propertyId.equals(Report.PROP_SUBJECT)) {
					f = super.createField(item, propertyId, uiContext);
					f.setCaption("Subject");
					f.setWidth("100%");
					((TextField) f).setInputPrompt("Please enter the subject of the report"); // TODO figure out something better and localize it
				} else if (propertyId.equals(Report.PROP_SUMMARY)) {
					f = super.createField(item, propertyId, uiContext);
					f.setCaption("Summary");
					f.setWidth("100%");
					((TextField) f).setRows(10);
					((TextField) f).setInputPrompt("Please enter a summary of the event"); // TODO figure out something better and localize it
				} else if (propertyId.equals(Report.PROP_SUPERVISORS)) {
					f = super.createField(item, propertyId, uiContext);
					f.setCaption("Supervisors");
					f.setWidth("100%");
				} else if (propertyId.equals(Report.PROP_LOCATION)) {
					f = super.createField(item, propertyId, uiContext);
					f.setCaption("Location");
					f.setWidth("100%");					
				}
				if (f instanceof TextField) {
					((TextField) f).setNullRepresentation("");					
				}
				return f;
			}
		}
	}

	public class ReportForm extends Form {

		private static final long serialVersionUID = 1285613958706842416L;

		private GridLayout layout;

		public ReportForm() {
			layout = new GridLayout(4, 6);
			layout.setSpacing(true);
			layout.setWidth("100%");
			
			setLayout(layout);

			setWriteThrough(false);
			setInvalidCommitted(false);
			setFormFieldFactory(new ReportFieldFactory());
			setVisibleItemProperties(new String[] { Report.PROP_REPORT_TYPE,
					Report.PROP_EVENT_START_DATE, Report.PROP_EVENT_START_TIME,
					Report.PROP_EVENT_END_DATE, Report.PROP_EVENT_END_TIME,
					Report.PROP_SUBJECT, Report.PROP_SUPERVISORS, Report.PROP_SUMMARY, Report.PROP_LOCATION });
		}

		public void setReportItem(BeanItem<Report> reportItem) {
			setItemDataSource(reportItem);
		}

		@Override
		protected void attachField(Object propertyId, Field field) {
			if (propertyId.equals(Report.PROP_REPORT_TYPE)) {
				layout.addComponent(field, 0, 0, 3, 0);
			} else if (propertyId.equals(Report.PROP_EVENT_START_DATE)) {
				layout.addComponent(field, 0, 1);
			} else if (propertyId.equals(Report.PROP_EVENT_START_TIME)) {
				layout.addComponent(field, 1, 1);
			} else if (propertyId.equals(Report.PROP_EVENT_END_DATE)) {
				layout.addComponent(field, 2, 1);
			} else if (propertyId.equals(Report.PROP_EVENT_END_TIME)) {
				layout.addComponent(field, 3, 1);
			} else if (propertyId.equals(Report.PROP_SUBJECT)) {
				layout.addComponent(field, 0, 2, 3, 2);
			} else if (propertyId.equals(Report.PROP_SUPERVISORS)) {
				layout.addComponent(field, 0, 3, 3, 3);
			} else if (propertyId.equals(Report.PROP_SUMMARY)) {
				layout.addComponent(field, 0, 4, 3, 4);
			} else if (propertyId.equals(Report.PROP_LOCATION)) {
				layout.addComponent(field, 0, 5, 3, 5);
			}
		}
	}

}
