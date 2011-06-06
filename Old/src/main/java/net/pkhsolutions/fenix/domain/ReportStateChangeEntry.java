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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author petter
 * 
 */
@Entity
public class ReportStateChangeEntry extends AbstractEntity {

	private static final long serialVersionUID = -392450837059105037L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Report report;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date stateChangeTimestamp;

	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private ReportState newState;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private User user;

	private String comment;

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Date getStateChangeTimestamp() {
		return stateChangeTimestamp;
	}

	public void setStateChangeTimestamp(Date stateChangeTimestamp) {
		this.stateChangeTimestamp = stateChangeTimestamp;
	}

	public ReportState getNewState() {
		return newState;
	}

	public void setNewState(ReportState newState) {
		this.newState = newState;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
