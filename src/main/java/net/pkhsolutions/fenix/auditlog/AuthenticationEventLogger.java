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

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationFailureDisabledEvent;
import org.springframework.security.authentication.event.AuthenticationFailureLockedEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO Document me!
 * 
 * @author petter
 * 
 */
@Component
public class AuthenticationEventLogger implements
		ApplicationListener<AbstractAuthenticationEvent> {

	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationEventLogger.class);

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public void onApplicationEvent(AbstractAuthenticationEvent event) {
		if (logger.isDebugEnabled()) {
			logger.debug("Logging event [" + event + "] in audit log");
		}
		final UserAuditLogRecord record = new UserAuditLogRecord();
		record.setRecordTimestamp(new Date());
		record.setUsername(event.getAuthentication().getName());
		// TODO Figure out a clever way of getting the client's host
		if (event instanceof AuthenticationSuccessEvent) {
			record.setEventType(UserAuditEventType.AUTHENTICATED);
		} else if (event instanceof AuthenticationFailureBadCredentialsEvent) {
			record.setEventType(UserAuditEventType.BAD_CREDENTIALS);
		} else if (event instanceof AuthenticationFailureDisabledEvent) {
			record.setEventType(UserAuditEventType.ACCOUNT_DISABLED);
		} else if (event instanceof AuthenticationFailureLockedEvent) {
			record.setEventType(UserAuditEventType.ACCOUNT_LOCKED);
		} else {
			if (logger.isWarnEnabled()) {
				logger.warn("Uunknown event: [" + event + "]");
			}
			record.setEventType(UserAuditEventType.UNKNOWN);
			record.setComment("Unknown event: "
					+ event.getClass().getSimpleName());
		}
		entityManager.persist(record);
	}

}
