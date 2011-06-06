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
import net.pkhsolutions.fenix.auditlog.EntityAuditLogRecord;
import net.pkhsolutions.fenix.auditlog.UserAuditLogRecord;
import net.pkhsolutions.fenix.domain.AbstractEntity;

/**
 * This interface defines a service that is used to retrieve audit logs for
 * entities (e.g. when they were created or updated and by whom) and for users (e.g. successful
 * and failed login attempts).
 *
 * @author Petter Holmström
 */
public interface AuditService {

	/**
	 * Fetches all the audit log records for the specified entity.
	 * 
	 * @param entity the entity, must not be <code>null</code>.
	 * @return a list of audit log records, sorted by timestamp in ascending order. It may be empty, but is never <code>null</code>.
	 */
	List<EntityAuditLogRecord> getAuditLogForEntity(AbstractEntity entity);

	/**
	 * Fetches all the audit log records for the specified user. Additionally,
	 * only records that are within the time interval <code>[from, to]</code> can be included.
	 *
	 * @param username the username of the user, must not be <code>null</code>.
	 * @param from the inclusive starting point of the time interval, or <code>null</code> to disable lower bound filtering.
	 * @param to the inclusive ending point of the time interval, or <code>null</code> to disable upper bound filtering.
	 * @param fetchMax the maximum number of records to retrieve, or 0 to retrieve all.
	 * @return a list of audit log records, sorted by timestamp in ascending order. It may be empty, but is never <code>null</code>.
	 */
	List<UserAuditLogRecord> getAuditLogForUser(String username, Date from, Date to, int fetchMax);
}
