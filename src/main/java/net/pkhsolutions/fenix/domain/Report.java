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
package net.pkhsolutions.fenix.domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TODO Document me!
 * 
 * @author petter
 * 
 */
@Entity
public class Report extends AbstractEntity {

	private static final long serialVersionUID = 3451616348587116520L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private ProducerFD producerFD;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private ReportType reportType;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date eventStartDate;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date eventStartTime;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date eventEndDate;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date eventEndTime;

	@Column(nullable = false)
	private String subject;

	@Column(nullable = false)
	private String summary;

	// TODO Add person (or persons) in charge??

	// TODO Add attachments (e.g. additional reports)

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	private ReportState state = ReportState.INCOMPLETE;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "report", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<ReportStateChangeEntry> stateChangeEntries = new HashSet<ReportStateChangeEntry>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "report", orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<ReportParticipant> participants = new HashSet<ReportParticipant>();

	public ProducerFD getProducerFD() {
		return producerFD;
	}

	public void setProducerFD(ProducerFD producerFD) {
		this.producerFD = producerFD;
	}

	public ReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReportType reportType) {
		this.reportType = reportType;
	}

	public Date getEventStartDate() {
		return eventStartDate;
	}

	public void setEventStartDate(Date eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public Date getEventStartTime() {
		return eventStartTime;
	}

	public void setEventStartTime(Date eventStartTime) {
		this.eventStartTime = eventStartTime;
	}

	public Date getEventEndDate() {
		return eventEndDate;
	}

	public void setEventEndDate(Date eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public Date getEventEndTime() {
		return eventEndTime;
	}

	public void setEventEndTime(Date eventEndTime) {
		this.eventEndTime = eventEndTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public ReportState getState() {
		return state;
	}

	public void setState(ReportState state) {
		this.state = state;
	}

	public Collection<ReportStateChangeEntry> getStateChangeEntries() {
		return stateChangeEntries;
	}

	public Collection<ReportParticipant> getParticipants() {
		return participants;
	}

}
