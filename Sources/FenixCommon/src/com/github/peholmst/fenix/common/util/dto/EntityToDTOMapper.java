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

import java.lang.reflect.Field;

import com.github.peholmst.fenix.common.util.FieldUtil;

/**
 * TODO Document me!
 * 
 * @author peholmst
 * 
 * @param <DTO>
 */
class EntityToDTOMapper<DTO> {
    private final Class<DTO> dtoClass;
    private final Object[] entities;
    private DTO dto;

    private final FieldUtil.FieldVisitor dtoClassFieldVisitor = new FieldUtil.AccessibleFieldVisitor() {

        @Override
        public void visitAccessibleField(Field field) throws Exception {
            if (field.isAnnotationPresent(EntityField.class)) {
                copyEntityField(field);
            }
        }
    };

    private void copyEntityField(Field destinationField) throws Exception {
        EntityField entityFieldAnnotation = destinationField
                .getAnnotation(EntityField.class);
        String sourceFieldName = entityFieldAnnotation.fieldName();
        Class<?> sourceClass = entityFieldAnnotation.entityClass();
        Object sourceEntity = getEntityByClass(sourceClass);

        String destinationFieldName = destinationField.getName();
        Class<?> destinationFieldType = destinationField.getType();
        Object sourceFieldValue = FieldUtil.getFieldValue(sourceEntity,
                sourceFieldName);

        if (destinationFieldType.isAnnotationPresent(DataTransferObject.class)) {
            Object sourceFieldValueConvertedToDTO = DTOUtil
                    .createDTOForSingleEntity(destinationFieldType,
                            sourceFieldValue);
            FieldUtil.setFieldValue(dto, destinationFieldName,
                    sourceFieldValueConvertedToDTO);
        } else {
            FieldUtil
                    .setFieldValue(dto, destinationFieldName, sourceFieldValue);
        }
    }

    private Object getEntityByClass(Class<?> entityClass) {
        if (EntityField.DefaultEntityClass.class.equals(entityClass)) {
            return getDefaultEntity();
        }
        for (Object entity : entities) {
            if (entityClass.equals(entity.getClass())) {
                return entity;
            }
        }
        throw new DTOException("No entity of " + entityClass
                + " could be found");
    }

    private Object getDefaultEntity() {
        if (entities.length != 1) {
            throw new DTOException(
                    "A default entity class can only be specified when "
                            + "there is exactly one entity object to copy from");
        }
        return entities[0];
    }

    public EntityToDTOMapper(Class<DTO> dtoClass, Object[] entities) {
        this.dtoClass = dtoClass;
        this.entities = entities;
    }

    public DTO createAndPopulateDTO() {
        verifyDataTransferObjectAnnotation();

        try {
            dto = createNewDTOInstance();
            populateDTO();
            return dto;
        } catch (DTOException e) {
            /*
             * We have to catch this exception and re-throw it to prevent it
             * from being handled by the general exception handler below.
             */
            throw e;
        } catch (Exception e) {
            throw new DTOException(
                    "An exception occurred while creating the DTO", e);
        }
    }

    private void verifyDataTransferObjectAnnotation() {
        DataTransferObject dtoAnnotation = dtoClass
                .getAnnotation(DataTransferObject.class);
        if (dtoAnnotation == null) {
            throw new DTOException(
                    "The DataTransferObject annotation is missing from the DTO class "
                            + dtoClass.getSimpleName());
        }
        verifyThatProvidedEntitiesAreSupported(dtoAnnotation);
    }

    private void verifyThatProvidedEntitiesAreSupported(
            DataTransferObject metadata) {
        for (Object entity : entities) {
            Class<?> entityClass = entity.getClass();
            if (!DTOUtil.entityClassIsSupported(metadata, entityClass)) {
                throw new DTOException(
                        "The entity class is not supported by the DTO class "
                                + dtoClass.getSimpleName());
            }
        }
    }

    private DTO createNewDTOInstance() throws Exception {
        return dtoClass.newInstance();
    }

    private void populateDTO() throws Exception {
        FieldUtil.visitAllDeclaredFields(dtoClass, dtoClassFieldVisitor);
    }

}