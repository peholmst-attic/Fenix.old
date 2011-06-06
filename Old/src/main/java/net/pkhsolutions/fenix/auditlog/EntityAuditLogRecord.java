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

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * TODO Document me!
 * 
 * @author petter
 */
@Entity
public class EntityAuditLogRecord extends AuditLogRecord<EntityAuditEventType> {

	private static final long serialVersionUID = -6918253398477634625L;

	@Column(nullable = false)
	private String entityName;

	@Column(nullable = false)
	private Long entityIdentifier;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Long getEntityIdentifier() {
		return entityIdentifier;
	}

	public void setEntityIdentifier(Long entityIdentifier) {
		this.entityIdentifier = entityIdentifier;
	}

}
