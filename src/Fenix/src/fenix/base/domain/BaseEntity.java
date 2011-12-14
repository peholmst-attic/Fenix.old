/*
 * Fenix
 * Copyright (C) 2011 Petter Holmström
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fenix.base.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Base class for all entity classes.
 * 
 * @author Petter Holmström
 */
@MappedSuperclass
public abstract class BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -2109540329117361945L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Version
	protected Long version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	protected Date created;

	@ManyToOne(optional = false)
	protected User creator;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date updated;

	@ManyToOne
	protected User updater;

	public Long getId() {
		return id;
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	protected void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	protected void setVersion(Long version) {
		this.version = version;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public User getUpdater() {
		return updater;
	}

	public void setUpdater(User updater) {
		this.updater = updater;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != obj.getClass()) {
			return false;
		}
		final BaseEntity other = (BaseEntity) obj;
		return this.id != null && this.id.equals(other.id);
	}

	@Override
	public int hashCode() {
		if (this.id == null) {
			return super.hashCode();
		} else {
			return this.id.hashCode();
		}
	}
}
