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

import com.github.peholmst.fenix.common.exceptions.DataNotFoundException;
import com.github.peholmst.fenix.common.exceptions.ReadOnlyException;
import com.github.peholmst.fenix.common.util.OrderInstruction;
import com.github.peholmst.fenix.entity.util.VersionGroup;
import com.github.peholmst.fenix.entity.util.VersionedEntity;

/**
 * This interface defines a repository for versioned entities. Please check the
 * JavaDocs of the methods for more information.
 * 
 * @author Petter Holmström
 * 
 * @param <ENTITY>
 *            the type of entities handled by this repository.
 */
public interface VersionRepository<ENTITY extends VersionedEntity> {

    /**
     * Saves a copy of the specified entity (the new version), increases the
     * version number of the entity and saves it. If the entity is transient, a
     * new version group will be assigned. If there are multiple versions in the
     * version group, the specified entity must be the newest one, otherwise
     * this method will fail.
     * 
     * @param entity
     *            the entity to save.
     * @return the saved entity.
     * @throws ReadOnlyException
     *             if <code>entity</code> is not the newest version.
     */
    ENTITY saveAndCreateNewVersion(ENTITY entity) throws ReadOnlyException;

    /**
     * Saves the specified entity. If the entity is transient, a new version
     * group will be assigned. If there are multiple versions in the version
     * group, the specified entity must be the newest one, otherwise this method
     * will fail.
     * 
     * @param entity
     *            the entity to save.
     * @return the saved entity.
     * @throws ReadOnlyException
     *             if <code>entity</code> is not the newest version.
     */
    ENTITY save(ENTITY entity) throws ReadOnlyException;

    /**
     * Marks the specified entity as deleted and creates a new version. The
     * entity must be the newest version, otherwise this method will fail. If
     * the entity is not persistent, nothing happens. Note, that this method
     * does not actually remove any data from the database.
     * 
     * @param entity
     *            the entity to delete.
     * @throws ReadOnlyException
     *             if <code>entity</code> is not the newest version.
     */
    void delete(ENTITY entity) throws ReadOnlyException;

    /**
     * Marks the specified entity as undeleted and creates a new version. The
     * entity must be the newest version, otherwise this method will fail.
     * 
     * @param entity
     *            the entity to undelete.
     * @throws ReadOnlyException
     *             if <code>entity</code> is not the newest version.
     * @throws DataNotFoundException
     *             if the entity is not persistent.
     */
    void undelete(ENTITY entity) throws ReadOnlyException,
            DataNotFoundException;

    /**
     * Returns all the entities that belong to the specified version group. The
     * result is ordered by version number with the newest version at index 0.
     * If the version group does not exist or does not contain any entities, an
     * empty list is returned.
     * 
     * @param versionGroup
     *            the version group.
     * @return a list of entities.
     */
    List<ENTITY> findEntitiesOfGroup(VersionGroup versionGroup);

    /**
     * Returns the newest version from the specified version group.
     * 
     * @param versionGroup
     *            the version group.
     * @return the newest version.
     * @throws DataNotFoundException
     *             if the group does not exist or contains no versions.
     */
    ENTITY findNewestVersionInGroup(VersionGroup versionGroup)
            throws DataNotFoundException;

    /**
     * Returns the newest versions of all entities that have not been marked as
     * deleted.
     * 
     * @param orderInstructions
     *            instructions for ordering the result.
     * @return a list of entities.
     */
    List<ENTITY> findAll(OrderInstruction... orderInstructions);

    /**
     * Returns the entity with the specified identifier, regardless of whether
     * is is the newest version or has been marked as deleted.
     * 
     * @param identifier
     *            the entity identifier.
     * @return the entity.
     * @throws DataNotFoundException
     *             if the entity could not be found.
     */
    ENTITY findByIdentifier(long identifier) throws DataNotFoundException;

    /**
     * Checks if there exists an entity with the specified identifier.
     * 
     * @param identifier
     *            the identifier to check.
     * @return true if the entity exists, false if not.
     */
    boolean entityExists(long identifier);
}
