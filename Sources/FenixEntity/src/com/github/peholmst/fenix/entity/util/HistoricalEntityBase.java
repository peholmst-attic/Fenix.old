/*
 * Copyright (c) 2011 The original developers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.peholmst.fenix.entity.util;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This is a base class for entities that require all changes to be stored in a
 * history log. All versions of an entity are themselves entities and they are
 * grouped together by a master ID. The first entity created is always the
 * current version to which changes can be made and other entities refer.
 * <p>
 * When a new version has to be made, a copy of the current entity is created
 * and persisted (see {@link EntityBase#createFromExistingEntity(EntityBase)})
 * with the <tt>currentVersion</tt> flag set to false. Then, the
 * <tt>revision</tt> of the current version is incremented before the changes
 * are merged.
 * 
 * @author Petter Holmström
 */
@MappedSuperclass
public abstract class HistoricalEntityBase extends EntityBase {

    private static final long serialVersionUID = -6454306773489178762L;

    @Column(nullable = false)
    protected Long masterId;

    @Column(nullable = false)
    protected boolean currentVersion = true;

    @ManyToOne(optional = false)
    protected User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    protected Date createdOn;

    @Column(nullable = false)
    protected boolean deleted = false;

    @Column(nullable = false)
    protected Long revision;

    /**
     * Returns the master ID of this entity. All the versions of the same
     * logical entity have the same master ID. Of these entities, the one that
     * was created first is always the current version to which changes are made
     * and other entities refer.
     * 
     * @see #isCurrentVersion()
     * @see #getRevision()
     */
    public Long getMasterId() {
        return masterId;
    }

    /**
     * INTERNAL: Sets the master ID of this entity.
     */
    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    /**
     * Returns whether this entity is the current version. Changes can only be
     * made to an entity that has this flag set to true. A value of false means
     * that the entity is an archived version that must never be modified.
     * 
     * @see #getMasterId()
     * @see #getRevision()
     */
    public boolean isCurrentVersion() {
        return currentVersion;
    }

    /**
     * INTERNAL: Sets the <tt>currentVersion</tt> flag of this entity.
     */
    public void setCurrentVersion(boolean currentVersion) {
        this.currentVersion = currentVersion;
    }

    /**
     * Returns the user who created this entity.
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * INTERNAL: Sets the user who created this entity.
     */
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Returns the date when this entity was created.
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * INTERNAL: Sets the date when this entity was created.
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Returns whether this entity is marked as deleted ("soft delete") or not.
     * Only the current version can have this flag set.
     * 
     * @see #isCurrentVersion()
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * INTERNAL: Sets the <tt>deleted</tt> flag of this entity.
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Returns the revision number of this entity. The higher the revision
     * number, the newer the version.
     */
    public Long getRevision() {
        return revision;
    }

    /**
     * INTERNAL: Sets the revision of this entity.
     */
    public void setRevision(Long revision) {
        this.revision = revision;
    }

    /**
     * INTERNAL: Increments the revision number of this entity. If no revision
     * number has been set, it is set to 1.
     */
    public void incrementRevision() {
        if (revision == null) {
            revision = 1L;
        } else {
            ++revision;
        }
    }
}
