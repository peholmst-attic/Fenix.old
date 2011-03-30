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
package com.github.peholmst.fenix.common.util.dto;

import java.util.Set;

/**
 * TODO Document me!
 * 
 * @author Petter Holmström
 */
public final class DTOUtil {

    static boolean entityClassIsSupported(DataTransferObject annotation,
            Class<?> entityClass) {
        for (Class<?> supportedClass : annotation.entityClasses()) {
            if (supportedClass.equals(entityClass)) {
                return true;
            }
        }
        return false;
    }

    private DTOUtil() {

    }

    /**
     * 
     * @param <DTO>
     * @param dtoClass
     * @param sourceEntity
     * @return
     */
    public static <DTO> DTO createDTOForSingleEntity(Class<DTO> dtoClass,
            Object sourceEntity) {
        if (sourceEntity == null) {
            return null;
        }
        return new EntityToDTOMapper<DTO>(dtoClass,
                new Object[] { sourceEntity }).createAndPopulateDTO();
    }

    /**
     * 
     * @param <DTO>
     * @param dtoClass
     * @param sourceEntities
     * @return
     */
    public static <DTO> DTO createDTOForMultipleEntities(Class<DTO> dtoClass,
            Object... sourceEntities) {
        return new EntityToDTOMapper<DTO>(dtoClass, sourceEntities)
                .createAndPopulateDTO();
    }

    public static class EntityReference {
        // TODO Complete me!
    }

    public static EntityReference getSingleEntityReferenceToResolveFromDTO(
            Object dto) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static Set<EntityReference> getMultipleEntityReferencesToResolveFromDTO(
            Object dto) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static void copyDataFromDTOToSingleEntity(Object sourceDTO,
            Object destinationEntity) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public static void copyDataFromDTOToMultipleEntities(Object sourceDTO,
            Object... destinationEntities) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
