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
package net.pkhsolutions.fenix.services;

import java.util.Date;
import java.util.List;
import javax.validation.ValidationException;
import net.pkhsolutions.fenix.domain.ConsumerFD;
import net.pkhsolutions.fenix.domain.ProducerFD;
import net.pkhsolutions.fenix.domain.Report;
import net.pkhsolutions.fenix.domain.ReportState;
import net.pkhsolutions.fenix.domain.ReportStateChangeEntry;
import net.pkhsolutions.fenix.domain.ReportType;

/**
 * This interface defines a service that is used to create, manage the life cycle of,
 * and retrieve {@link Report}s.
 *
 * @author Petter Holmström
 */
public interface ReportService {

	// TODO Add security annotations

	/**
	 * Fetches a list of all the report types that are currently active, i.e.
	 * <code>validFrom &lt;= now && (validTo == null || validTo &gt;= now)</code>.
	 *
	 * @return a list of report types, may be empty but never <code>null</code>.
	 */
	List<ReportType> getActiveReportTypes();

	/**
	 * Fetches a list of all the report types that were active on the given date,
	 * i.e. <code>validFrom &lt;= date && (validTo == null || validTo &gt;= date</code>.
	 *
	 * @see #getActiveReportTypes()
	 * @param date the date, or <code>null</code> to fetch the report types that are currently active.
	 * @return a list of report types, may be empty but never <code>null</code>.
	 */
	List<ReportType> getReportTypes(Date date);

	/**
	 * Attempts to validate and save the specified report. The state of the report will not be changed,
	 * even if the {@link Report#getReportState() reportState} property has been changed. If this is
	 * the first time the report is saved, its state will be set to {@link ReportState#INCOMPLETE}.
	 *
	 * @see #saveReportAndChangeState(net.pkhsolutions.fenix.domain.Report, net.pkhsolutions.fenix.domain.ReportState, java.lang.String) 
	 *
	 * @param report the report to save, must not be <code>null</code>.
	 * @return the saved report, never <code>null</code>.
	 * @throws IllegalStateException if the state of the report is anything other than {@link ReportState#INCOMPLETE} or {@link ReportState#COMPLEMENTS_REQUIRED}.
	 * @throws ValidationException if the report contains invalid data.
	 */
	Report saveReport(Report report) throws IllegalStateException, ValidationException;

	/**
	 * Attempts to validate and save the specified report and also changes the state at the same time.
	 * If <code>newState</code> is equal to the state stored in the database, this method does the
	 * same as {@link #saveReport(net.pkhsolutions.fenix.domain.Report) } and no report state change entry
	 * is recorded.
	 *
	 * @see #getReportStateChanges(net.pkhsolutions.fenix.domain.Report) 
	 *
	 * @param report the report to save, must not be <code>null</code>.
	 * @param newState the new state of the report, must not be <code>null</code>.
	 * @param comment an additional comment explaining the state change, may be <code>null</code>.
	 * @return the saved report, never <code>null</code>.
	 * @throws IllegalReportStateTransitionException if the state of the report cannot be changed to <code>newState</code> according to the rules defined in the JavaDocs of {@link ReportState}.
	 * @throws IllegalStateException if the state of the report does not change as a result of this method call and the current state is anything other than {@link ReportState#INCOMPLETE} or {@link ReportState#COMPLEMENTS_REQUIRED}.
	 * @throws ValidationException if the report contains invalid data.
	 */
	Report saveReportAndChangeState(Report report, ReportState newState, String comment) throws IllegalReportStateTransitionException, IllegalStateException, ValidationException;

	/**
	 * Fetches a list of all the state changes of the specified report.
	 *
	 * @param report the report, must not be <code>null</code>.
	 * @return a list of report state changes, sorted by timestamp in ascending order. It may be empty but is never <code>null</code>.
	 */
	List<ReportStateChangeEntry> getReportStateChanges(Report report);

	/**
	 * Fetches all the reports of the specified producer fire department that are of the specified report type.
	 * Additionally, only reports whose {@link Report#getEventStartDate() eventStartDate}s are within the time interval
	 * [<code>from</code>, <code>to</code>] can be included.
	 * 
	 * @param producerFD the producer fire department, must not be <code>null</code>.
	 * @param reportType the report type, must not be <code>null</code>.
	 * @param from the inclusive starting point of the time interval, or <code>null</code> to disable lower bound filtering.
	 * @param to the inclusive ending point of the time interval, or <code>null</code> to disable upper bound filtering.
	 * @param fetchMax the maximum number of reports to retrieve, or 0 to retrieve all.
	 * @return a list of reports, sorted by event start date and time in ascending order. It may be empty but is never <code>null</code>.
	 */
	List<Report> getReportsByType(ProducerFD producerFD, ReportType reportType, Date from, Date to, int fetchMax);

	/**
	 * Fetches all reports of the specified producer fire department that contain the specified keyword.
	 * At least {@link Report#getSubject() subject}, {@link Report#getSummary() summary} and {@link Report#getLocation() location} are included in the search.
	 *
	 * @param producerFD the producer fire department, must not be <code>null</code>.
	 * @param keyword the keyword to look for, must neither be <code>null</code> nor empty.
	 * @param fetchMax the maximum number of reports to retrieve, or 0 to retrieve all.
	 * @return a list of reports, sorted by event start date and time in ascending order. It may be empty but is never <code>null</code>.
	 */
	List<Report> findReportsByKeyword(ProducerFD producerFD, String keyword, int fetchMax);

	/**
	 * Gets all the reports that are currently awaiting approval by the specified consumer fire department. The returned
	 * list may be additionally filtered depending to the user that invoked the method (e.g. if a certain user only manages
	 * a certain number of producer fire departments).
	 *
	 * @param consumerFD the consumer fire department, must not be <code>null</code>.
	 * @return a list of reports awaiting approval, sorted by event start date and time in ascending order. It may be empty but is never <code>null</code>.
	 */
	List<Report> getReportsAwaitingApproval(ConsumerFD consumerFD);

	/**
	 * Gets all the reports by the specified producer fire department that are currently incomplete.
	 * 
	 * @param producerFD the producer fire department, must not be <code>null</code>.
	 * @return a list of incomplete reports, may be empty but never <code>null</code>.
	 */
	List<Report> getIncompleteReports(ProducerFD producerFD);

	/**
	 * Gets all the reports by the specified producer fire department that require complementing before they can be approved (or denied).
	 *
	 * @param producerFD the producer fire department, must not be <code>null</code>.
	 * @return a list of reports that require complementing, sorted by event start date and time in ascending order. It may be empty but is never <code>null</code>.
	 */
	List<Report> getReportsRequiringComplementing(ProducerFD producerFD);

	/**
	 * Gets all the reports by the specified producer fire department that have been denied.
	 *
	 * @param producerFD the producer fire department, must not be <code>null</code>.
	 * @return a list of denied reports, sorted by event start date and time in ascending order. It may be empty but is never <code>null</code>.
	 */
	List<Report> getDeniedReports(ProducerFD producerFD);

	/**
	 * Completely deletes the specified report. This operation cannot be undone.
	 *
	 * @param report the report to delete, must not be <code>null</code>.
	 */
	void deleteReport(Report report);

	// TODO Attachements and attachment content
}
