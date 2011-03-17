/*
 * Copyright (c) 2011 Petter Holmström
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

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * This is a base class for entities that need to be versioned. The idea behind
 * entity versioning is the following: the first instance of an entity is
 * <strong>always</strong> the latest version and can be modified freely. When
 * the user wants to create a new version, the following happens:
 * <ul>
 * <li>A copy of the latest version is created with a different identifier and
 * the <code>newestVersion</code> flag set to false.</li>
 * <li>The copy is stored in the database as a normal entity. <strong>Keep this
 * in mind if you want to use unique constraints on the database level in your
 * application!</strong></li>
 * <li>The version number of the latest version is increased (not necessarily by
 * one).</li>
 * </ul>
 * The versions are grouped together by referencing the same
 * {@link VersionGroup}. Thus, all the versions of an entity can be retrieved
 * from the entity manager by querying for entities with the same version group.
 * <p>
 * Versioned entities should preferably be aggregate roots.
 * 
 * 
 * @author Petter Holmström
 */
@MappedSuperclass
public abstract class VersionedEntity extends EntityBase {

    private static final long serialVersionUID = 3072632765829536204L;

    @Column(nullable = false)
    private Long version;

    public static final String PROP_VERSION = "version";

    @Column(nullable = false)
    private boolean deleted = false;

    public static final String PROP_DELETED = "deleted";

    @Column(nullable = false)
    private boolean newestVersion = true;

    public static final String PROP_NEWEST_VERSION = "newestVersion";

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private VersionGroup versionGroup;

    public static final String PROP_VERSION_GROUP = "versionGroup";

    /**
     * Protected constructor, clients should use the factory methods instead.
     */
    protected VersionedEntity() {
    }

    /**
     * Checks whether this entity is marked as deleted, meaning it should not
     * show up in normal listings, etc.
     * 
     * @return true if the entity is deleted, false if not.
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Checks whether this entity is the newest version. There can only be one
     * entity in the same version group with this property set to true.
     * 
     * @return true if the entity is the newest version, false if not.
     */
    public boolean isNewestVersion() {
        return newestVersion;
    }

    /**
     * Gets the version number of this entity.
     * 
     * @return the version number, or <code>null</code> if no version has been
     *         assigned yet.
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Gets the version group for this entity.
     * 
     * @return the version group, or <code>null</code> if none has been assigned
     *         yet.
     */
    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    /**
     * Sets the version group for this entity.
     * 
     * @param versionGroup
     *            the version group to set.
     */
    public void setVersionGroup(VersionGroup versionGroup) {
        this.versionGroup = versionGroup;
    }

}
