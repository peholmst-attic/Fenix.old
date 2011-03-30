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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;

/**
 * Test case for {@link DTOUtil} that tests the creation of DTOs from Entities
 * (i.e. the functionality of {@link EntityToDTOMapper}.
 * 
 * @author Petter Holmström
 */
public class EntityToDTOMapperTest {

    @Test
    public void createDTOForSingleEntityWhenEntityIsNull() {
        assertNull(DTOUtil.createDTOForSingleEntity(
                DTOForFirstTestEntity.class, null));
    }

    @Test(expected = DTOException.class)
    public void createDTOForSingleEntityWhenDataTransferObjectAnnotationIsMissing() {
        DTOUtil.createDTOForSingleEntity(FirstTestEntity.class,
                new FirstTestEntity());
    }

    @Test(expected = DTOException.class)
    public void createDTOForSingleEntityWhenEntityIsUnsupported() {
        DTOUtil.createDTOForSingleEntity(DTOForSecondTestEntity.class,
                new FirstTestEntity());
    }

    @Test
    public void identifierIsCopiedForSingleEntity() {
        FirstTestEntity source = new FirstTestEntity();
        source.setId(123L);

        DTOForFirstTestEntity destination = DTOUtil.createDTOForSingleEntity(
                DTOForFirstTestEntity.class, source);

        assertEquals(source.getId(), destination.getId());
    }

    @Test
    public void identifiersAreCopiedForMultipleEntities() {
        FirstTestEntity firstSource = new FirstTestEntity();
        firstSource.setId(123L);
        SecondTestEntity secondSource = new SecondTestEntity();
        secondSource.setId(456L);

        DTOForMultipleEntities destination = DTOUtil
                .createDTOForMultipleEntities(DTOForMultipleEntities.class,
                        firstSource, secondSource);

        assertEquals(firstSource.getId(), destination.getFirstIdentifier());
        assertEquals(secondSource.getId(), destination.getSecondIdentifier());
    }

    @Test
    public void basicPropertyIsCopiedForSingleEntity() {
        FirstTestEntity source = new FirstTestEntity();
        source.setBasicProperty("Hello world");

        DTOForFirstTestEntity destination = DTOUtil.createDTOForSingleEntity(
                DTOForFirstTestEntity.class, source);

        assertEquals(source.getBasicProperty(), destination.getBasicField());
    }

    @Test
    public void basicPropertyIsCopiedForSingleEntityWhenValueIsNull() {
        FirstTestEntity source = new FirstTestEntity();

        DTOForFirstTestEntity destination = DTOUtil.createDTOForSingleEntity(
                DTOForFirstTestEntity.class, source);

        assertNull(destination.getBasicField());
    }

    @Test
    public void basicPropertiesAreCopiedForMultipleEntities() {
        FirstTestEntity firstSource = new FirstTestEntity();
        firstSource.setBasicProperty("foo");
        SecondTestEntity secondSource = new SecondTestEntity();
        secondSource.setAnotherBasicProperty(new Date());

        DTOForMultipleEntities destination = DTOUtil
                .createDTOForMultipleEntities(DTOForMultipleEntities.class,
                        firstSource, secondSource);

        assertEquals(firstSource.getBasicProperty(),
                destination.getFirstBasicField());
        assertEquals(secondSource.getAnotherBasicProperty(),
                destination.getSecondBasicField());
    }

    @Test
    public void entityPropertyIsCopiedForSingleEntity() {
        FirstTestEntity source = new FirstTestEntity();
        SecondTestEntity entityPropertyValue = new SecondTestEntity();
        entityPropertyValue.setId(123L);
        source.setEntityProperty(entityPropertyValue);

        DTOForFirstTestEntity destination = DTOUtil.createDTOForSingleEntity(
                DTOForFirstTestEntity.class, source);

        assertEquals(entityPropertyValue.getId(), destination.getEntityField()
                .getId());
    }
}
