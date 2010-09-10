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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TODO Document me!
 * 
 * @author petter
 * 
 */
@Entity
public class ReportParticipant extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8863852496467831327L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Report report;

	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	private ProducerFDMember fdMember;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date participationBeganDate;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date participationBeganTime;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date participationEndedDate;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date participationEndedTime;

	// TODO Add unit

	private boolean supervisor = false;

	private String comment;

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public ProducerFDMember getFdMember() {
		return fdMember;
	}

	public void setFdMember(ProducerFDMember fdMember) {
		this.fdMember = fdMember;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getParticipationBeganDate() {
		return participationBeganDate;
	}

	public void setParticipationBeganDate(Date participationBeganDate) {
		this.participationBeganDate = participationBeganDate;
	}

	public Date getParticipationBeganTime() {
		return participationBeganTime;
	}

	public void setParticipationBeganTime(Date participationBeganTime) {
		this.participationBeganTime = participationBeganTime;
	}

	public Date getParticipationEndedDate() {
		return participationEndedDate;
	}

	public void setParticipationEndedDate(Date participationEndedDate) {
		this.participationEndedDate = participationEndedDate;
	}

	public Date getParticipationEndedTime() {
		return participationEndedTime;
	}

	public void setParticipationEndedTime(Date participationEndedTime) {
		this.participationEndedTime = participationEndedTime;
	}

	public boolean isSupervisor() {
		return supervisor;
	}

	public void setSupervisor(boolean supervisor) {
		this.supervisor = supervisor;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
