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
import net.pkhsolutions.fenix.domain.ConsumerFD;
import net.pkhsolutions.fenix.domain.ProducerFD;
import net.pkhsolutions.fenix.domain.Report;
import net.pkhsolutions.fenix.domain.ReportType;

/**
 *
 * @author petter
 */
public interface ReportService {

	/**
	 *
	 *
	 * @return
	 */
	List<ReportType> getActiveReportTypes();

	/**
	 * 
	 * @param date
	 * @return
	 */
	List<ReportType> getReportTypes(Date date);

	/**
	 * 
	 * @param report
	 * @return
	 * @throws IllegalStateException 
	 */
	Report saveReport(Report report) throws IllegalStateException;

	/**
	 * 
	 * @param producerFD 
	 * @param reportType
	 * @param from
	 * @param to
	 * @param fetchMax 
	 * @return
	 */
	List<Report> getReportsByType(ProducerFD producerFD, ReportType reportType, Date from, Date to, int fetchMax);

	/**
	 * 
	 * @param consumerFD
	 * @return
	 */
	List<Report> getReportsAwaitingApproval(ConsumerFD consumerFD);

	/**
	 * 
	 * @param producerFD
	 * @return
	 */
	List<Report> getIncompleteReports(ProducerFD producerFD);

	/**
	 * 
	 * @param producerFD
	 * @return
	 */
	List<Report> getReportsRequiringComplementing(ProducerFD producerFD);

	/**
	 * 
	 * @param producerFD
	 * @return
	 */
	List<Report> getDeniedReports(ProducerFD producerFD);

	/**
	 * 
	 * @param report
	 */
	void deleteReport(Report report);

	// TODO Attachements and attachment content
}
