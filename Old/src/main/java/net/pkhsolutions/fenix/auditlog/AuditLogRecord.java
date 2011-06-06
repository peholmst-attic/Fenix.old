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
package net.pkhsolutions.fenix.auditlog;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TODO Document me!
 * 
 * @author Petter Holmström
 */
@MappedSuperclass
public abstract class AuditLogRecord<T extends Enum<?>> implements Serializable {

	private static final long serialVersionUID = 7627014598165088907L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identifier;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date recordTimestamp;

	private String username;

	private String comment;

	private String host;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private T eventType;

	public Long getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Long identifier) {
		this.identifier = identifier;
	}

	public Date getRecordTimestamp() {
		return recordTimestamp;
	}

	public void setRecordTimestamp(Date recordTimestamp) {
		this.recordTimestamp = recordTimestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public T getEventType() {
		return eventType;
	}

	public void setEventType(T eventType) {
		this.eventType = eventType;
	}
}
