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
package com.github.peholmst.fenix.ejb.infra.util;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.peholmst.fenix.common.exceptions.DataNotFoundException;
import com.github.peholmst.fenix.common.exceptions.ReadOnlyException;
import com.github.peholmst.fenix.common.util.FieldUtil;
import com.github.peholmst.fenix.common.util.OrderInstruction;
import com.github.peholmst.fenix.entity.util.EntityBase;
import com.github.peholmst.fenix.entity.util.IdGenerator;
import com.github.peholmst.fenix.entity.util.VersionGroup;
import com.github.peholmst.fenix.entity.util.VersionedEntity;

/**
 * 
 * @author peholmst
 * 
 * @param <ENTITY>
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public abstract class VersionRepositoryBaseBean<ENTITY extends VersionedEntity>
        implements VersionRepository<ENTITY> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 
     * @return
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * 
     * @return
     */
    protected abstract Class<ENTITY> getEntityClass();

    @Override
    public ENTITY saveAndCreateNewVersion(ENTITY entity)
            throws ReadOnlyException {
        assert entity != null : "entity must not be null";
        if (entityExists(entity.getIdentifier())) {
            requireNewestVersion(entity);
        } else {
            entity = saveTransientEntity(entity);
        }
        log.debug("Creating new version of entity {}", entity);
        ENTITY copy = EntityBase.createFromExistingEntity(entity);
        getEntityManager().persist(copy);
        setVersionOfEntity(entity, getNextVersion(entity.getVersionGroup()));
        return getEntityManager().merge(entity);
    }

    @Override
    public ENTITY save(ENTITY entity) throws ReadOnlyException {
        assert entity != null : "entity must not be null";
        if (entityExists(entity.getIdentifier())) {
            return savePersistentEntity(entity);
        } else {
            return saveTransientEntity(entity);
        }
    }

    private ENTITY savePersistentEntity(ENTITY entity) throws ReadOnlyException {
        log.debug("Saving persistent entity {}", entity);
        if (entity.getVersionGroup() == null) {
            log.error("Persistent entity {} has no version group", entity);
            throw new IllegalArgumentException(
                    "The entity has no version group");
        }
        requireNewestVersion(entity);
        entity = getEntityManager().merge(entity);
        return entity;
    }

    private ENTITY saveTransientEntity(ENTITY entity) {
        log.debug("Saving transient entity {}", entity);
        VersionGroup versionGroup = VersionGroup
                .createEntity(VersionGroup.class);
        getEntityManager().persist(versionGroup);
        entity.setVersionGroup(versionGroup);
        setVersionOfEntity(entity, getNextVersion(versionGroup));
        getEntityManager().persist(entity);
        return entity;
    }

    private void requireNewestVersion(ENTITY entity) throws ReadOnlyException {
        try {
            ENTITY newestVersion = findNewestVersionInGroup(entity
                    .getVersionGroup());
            if (!newestVersion.equals(entity)) {
                throw new ReadOnlyException("Entity is not the newest version");
            }
        } catch (DataNotFoundException e) {
            log.error(
                    "The group {} was not found or did not contain any entities",
                    entity.getVersionGroup());
            throw new IllegalStateException("The entity group is invalid");
        }
    }

    /**
     * 
     * @param versionGroup
     * @return
     */
    protected long getNextVersion(VersionGroup versionGroup) {
        return IdGenerator.getNextValue();
    }

    private void setVersionOfEntity(ENTITY entity, Long versionNumber) {
        try {
            FieldUtil.setFieldValue(entity, VersionedEntity.PROP_VERSION,
                    versionNumber);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Could not set version", e);
        }
    }

    @Override
    public void delete(ENTITY entity) throws ReadOnlyException {
        // TODO Auto-generated method stub

    }

    @Override
    public void undelete(ENTITY entity) throws ReadOnlyException,
            DataNotFoundException {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ENTITY> findEntitiesOfGroup(VersionGroup versionGroup) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ENTITY findNewestVersionInGroup(VersionGroup versionGroup)
            throws DataNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ENTITY> findAll(OrderInstruction... orderInstructions) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ENTITY findByIdentifier(long identifier)
            throws DataNotFoundException {
        ENTITY entity = getEntityManager().find(getEntityClass(), identifier);
        if (entity == null) {
            throw new DataNotFoundException("Entity not found");
        }
        return entity;
    }

    @Override
    public boolean entityExists(long identifier) {
        return getEntityManager().find(getEntityClass(), identifier) != null;
    }

}
