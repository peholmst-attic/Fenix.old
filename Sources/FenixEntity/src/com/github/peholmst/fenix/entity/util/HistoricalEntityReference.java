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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Date;

/**
 * This class represents a reference to a {@link HistoricalEntityBase}.
 * 
 * @author Petter Holmström
 */
public class HistoricalEntityReference<E extends HistoricalEntityBase>
        implements java.io.Serializable {

    private static final long serialVersionUID = -3498113345532268880L;

    private final Long masterId;

    private final Long id;

    private final Long revision;

    private final Date creationTime;

    private final Long creatorUserId;

    private final String creatorFullName;

    private final String entityClassName;

    private transient Class<E> entityClass;

    /**
     * Creates a new reference to the specified historical entity. None of the
     * parameters may be <code>null</code>.
     */
    public HistoricalEntityReference(E entity, Class<E> entityClass) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        if (entityClass == null) {
            throw new IllegalArgumentException("entityClass must not be null");
        }
        this.entityClass = entityClass;
        entityClassName = this.entityClass.getName();
        masterId = entity.getMasterId();
        id = entity.getIdentifier();
        revision = entity.getRevision();
        creationTime = entity.getCreatedOn();
        if (entity.getCreatedBy() == null) {
            throw new IllegalStateException("createdBy is missing from entity");
        }
        creatorUserId = entity.getCreatedBy().getUserId();
        creatorFullName = entity.getCreatedBy().getFullName();
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        entityClass = (Class<E>) Class.forName(entityClassName);
    }

    /**
     * Returns the entity class.
     */
    public Class<E> getEntityClass() {
        return entityClass;
    }

    /**
     * Returns the master ID of the entity.
     * 
     * @see HistoricalEntityBase#getMasterId()
     */
    public Long getMasterId() {
        return masterId;
    }

    /**
     * Returns the identifier of the entity.
     * 
     * @see EntityBase#getIdentifier()
     */
    public Long getIdentifier() {
        return id;
    }

    /**
     * Returns the revision of the entity.
     * 
     * @see HistoricalEntityBase#getRevision()
     */
    public Long getRevision() {
        return revision;
    }

    /**
     * Returns the creation time of the entity.
     * 
     * @see HistoricalEntityBase#getCreatedOn()
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Returns the ID of the user who created the entity.
     * 
     * @see HistoricalEntityBase#getCreatedBy()
     * @see User#getUserId()
     */
    public Long getCreatorUserId() {
        return creatorUserId;
    }

    /**
     * Returns the full name of the user who created the entity.
     * 
     * @see HistoricalEntityBase#getCreatedBy()
     * @see User#getFullName()
     */
    public String getCreatorFullName() {
        return creatorFullName;
    }
}
