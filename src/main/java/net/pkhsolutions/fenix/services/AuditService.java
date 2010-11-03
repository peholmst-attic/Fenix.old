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
 *
 * @author petter
 */
public interface AuditService {

	/**
	 * 
	 * @param entity
	 * @return
	 */
	List<EntityAuditLogRecord> getAuditLogForEntity(AbstractEntity entity);

	/**
	 *
	 * @param username
	 * @param from
	 * @param to
	 * @param fetchMax
	 * @return
	 */
	List<UserAuditLogRecord> getAuditLogForUser(String username, Date from, Date to, int fetchMax);
}
