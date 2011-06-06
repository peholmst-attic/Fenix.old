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

/**
 * This enumeration defines the different states that a {@link Report} may be in.
 * Depending on the current state of a report, only certain state transitions
 * are allowed. The business logic should make sure that these rules are not
 * broken.
 * 
 * @author Petter Holmström
 */
public enum ReportState {

	/**
	 * This state indicates that the report has been approved. No more
	 * modifications may be made to the report.
	 */
	APPROVED,

	/**
	 * This state indicates that the report has been denied. No more
	 * modifications may be made to the report.
	 */
	DENIED,

	/**
	 * This state indicates that the report must be complemented with additional
	 * information before it can be approved. The next state must be {@link #AWAITING_APPROVAL}.
	 */
	COMPLEMENTS_REQUIRED,

	/**
	 * This state indicates that the report is complete and does not require any
	 * approval. No more modifications may be made to the report.
	 */
	COMPLETE_NO_APPROVAL_REQUIRED,

	/**
	 * This state indicates that the report is complete and awaits approval. No modifications may be
	 * made to the report in this state. The next state must be one of the following: {@link #APPROVED}, {@link #COMPLEMENTS_REQUIRED} or {@link #DENIED}.
	 */
	AWAITING_APPROVAL,

	/**
	 * This state indicates that the report is incomplete. The next state must be either {@link #AWAITING_APPROVAL} or {@link #COMPLETE_NO_APPROVAL_REQUIRED}.
	 */
	INCOMPLETE
	
}
