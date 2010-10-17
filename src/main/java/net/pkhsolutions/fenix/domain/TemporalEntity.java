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
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TODO Document me!
 * @author petter
 *
 */
@MappedSuperclass
public abstract class TemporalEntity extends AbstractEntity {

	private static final long serialVersionUID = -3641387323891467287L;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date validFrom;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date validTo;

	public Date getValidFrom() {
		// Date is mutable, so we return a clone of it instead of the actual reference
		return validFrom != null ? (Date) validFrom.clone() : null;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom != null ? (Date) validFrom.clone() : null;
	}

	public Date getValidTo() {
		// Date is mutable, so we return a clone of it instead of the actual reference
		return validTo != null ? (Date) validTo.clone() : null;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo != null ? (Date) validTo.clone() : null;
	}

	public boolean isValidNow() {
		Date now = new Date();
		if (validFrom == null) {
			return false;
		} else {
			return validFrom.compareTo(now) <= 0 && (validTo == null || validTo.compareTo(now) >= 0);
		}
	}
}
