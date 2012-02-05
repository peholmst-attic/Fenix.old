/*
 * Fenix
 * Copyright (C) 2012 Petter Holmström
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
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
	protected String uuid = UUID.randomUUID().toString();

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

	@Transient
	private boolean persistent = false;

	/**
	 * Default constructor 
	 */
	public BaseEntity() {
	}

	/**
	 * Copy constructor
	 */
	public BaseEntity(BaseEntity source) {
		uuid = source.uuid;
		version = source.version;
		created = (Date) (source.created == null ? null : source.created
				.clone());
		creator = source.creator;
		updated = (Date) (source.updated == null ? null : source.updated
				.clone());
		updater = source.updater;
	}

	/**
	 * Returns whether this entity is persistent or transient in the JPA sense.
	 */
	public boolean isPersistent() {
		return persistent;
	}

	public UUID getUUID() {
		return uuid == null ? null : UUID.fromString(uuid);
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	public void setUUID(UUID uuid) {
		this.uuid = uuid == null ? null : uuid.toString();
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

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	protected void setCreated(Date created) {
		this.created = created;
	}

	public User getCreator() {
		return creator;
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	protected void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getUpdated() {
		return updated;
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	protected void setUpdated(Date updated) {
		this.updated = updated;
	}

	public User getUpdater() {
		return updater;
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	protected void setUpdater(User updater) {
		this.updater = updater;
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	@PostLoad
	protected void postLoad() {
		persistent = true;
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	@PrePersist
	protected void prePersist() {
		if (created == null) {
			created = new Date();
		}
		if (creator == null) {
			creator = User.getCurrent();
			assert creator != null : "no user bound to the current thread";
		}
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	@PostPersist
	protected void postPersist() {
		persistent = true;
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	@PreUpdate
	protected void preUpdate() {
		updated = new Date();
		updater = User.getCurrent();
		assert updater != null : "no user bound to the current thread";
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
		return this.uuid != null && this.uuid.equals(other.uuid);
	}

	@Override
	public int hashCode() {
		if (this.uuid == null) {
			return super.hashCode();
		} else {
			return this.uuid.hashCode();
		}
	}

	/**
	 * Returns a hex string of the identity hash code of this particular object.
	 * 
	 * @see System#identityHashCode(Object)
	 */
	public String getObjectIdentityHashCode() {
		return Integer.toHexString(System.identityHashCode(this));
	}
}
