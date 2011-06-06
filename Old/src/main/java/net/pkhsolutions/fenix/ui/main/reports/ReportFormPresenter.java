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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.pkhsolutions.fenix.domain.Report;
import net.pkhsolutions.fenix.domain.ReportType;
import net.pkhsolutions.fenix.ui.mvp.Presenter;
import net.pkhsolutions.fenix.ui.mvp.ViewController;

public class ReportFormPresenter extends Presenter<ReportFormView> {

	private static final long serialVersionUID = -7964338162494004790L;

	public ReportFormPresenter(ReportFormView view) {
		super(view);
	}
	
	@Override
	protected void viewShown(ViewController viewController,
			Map<String, Object> userData) {
		Locale sv = new Locale("sv");
		
		final Report report = (Report) userData.get(ReportFormView.REPORT_USER_DATA_KEY);
		
		List<ReportType> reportTypes = new ArrayList<ReportType>();
		ReportType reportType = new ReportType();
		reportType.getName().setDefaultLanguage(sv);
		reportType.getName().setValue(sv, "Övning (larmavdelning)");
		reportType.setRequiresApproval(true);
		reportTypes.add(reportType);

		reportType = new ReportType();
		reportType.getName().setDefaultLanguage(sv);
		reportType.getName().setValue(sv, "Övning (övriga avdelningar)");
		reportType.setRequiresApproval(true);
		reportTypes.add(reportType);

		reportType = new ReportType();
		reportType.getName().setDefaultLanguage(sv);
		reportType.getName().setValue(sv, "Alarm (räddning)");
		reportType.setRequiresApproval(true);
		reportTypes.add(reportType);

		reportType = new ReportType();
		reportType.getName().setDefaultLanguage(sv);
		reportType.getName().setValue(sv, "Alarm (första respons)");
		reportType.setRequiresApproval(true);
		reportTypes.add(reportType);
		
		reportType = new ReportType();
		reportType.getName().setDefaultLanguage(sv);
		reportType.getName().setValue(sv, "Fysisk fostran");
		reportType.setRequiresApproval(false);
		reportTypes.add(reportType);
		
		reportType = new ReportType();
		reportType.getName().setDefaultLanguage(sv);
		reportType.getName().setValue(sv, "Lektion (larmavdelning)");
		reportType.setRequiresApproval(true);
		reportTypes.add(reportType);				

		reportType = new ReportType();
		reportType.getName().setDefaultLanguage(sv);
		reportType.getName().setValue(sv, "Lektion (övriga avdelningar)");
		reportType.setRequiresApproval(false);
		reportTypes.add(reportType);
		
		getView().setReportTypes(reportTypes);
		getView().setReport(report);
	}
}
