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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.peholmst.fenix.common.util.CloneThis;
import com.github.peholmst.fenix.common.util.FieldUtil;

/**
 * Test case for {@link EntityBase}.
 * 
 * @author Petter Holmström
 */
public class EntityBaseTest {

    @SuppressWarnings("serial")
    static class EntityUnderTest extends EntityBase {

        ReferencedEntity aReference;
        @CloneThis
        AggregateEntity anAggregate;

        @CloneThis(deepClone = true)
        List<AggregateEntity> anAggregateList = new LinkedList<AggregateEntity>();

        @CloneThis(deepClone = true)
        AggregateEntity[] anAggregateArray;

        @CloneThis(deepClone = true)
        Map<String, AggregateEntity> anAggregateMap = new HashMap<String, AggregateEntity>();
    }

    @SuppressWarnings("serial")
    static class ReferencedEntity extends EntityBase {

    }

    @SuppressWarnings("serial")
    static class AggregateEntity extends EntityBase {

        ReferencedEntity aReference;

        @CloneThis
        AggregateEntity anAggregate;
    }

    @BeforeClass
    public static void setUpIdGenerator() {
        IdGenerator.setSequence(new InMemorySequence());
    }

    @Test
    public void createEntity() {
        EntityUnderTest entity = EntityBase.createEntity(EntityUnderTest.class);
        assertTrue(entity.getIdentifier() > 0);
        assertFalse(entity.isPersistent());
    }

    @Test
    public void createEntityWithExistingId() {
        EntityUnderTest entity = EntityBase.createEntityWithExistingId(
                EntityUnderTest.class, 123L);
        assertEquals(123L, entity.getIdentifier());
    }

    @Test
    public void isPersistent() throws Exception {
        EntityUnderTest entity = EntityBase.createEntity(EntityUnderTest.class);
        FieldUtil.setFieldValue(entity, EntityBase.PROP_OPT_LOCK_VERSION, 1L);
        assertTrue(entity.isPersistent());
    }

    @Test
    public void equalsNull() {
        EntityUnderTest entity = EntityBase.createEntity(EntityUnderTest.class);
        assertFalse(entity.equals(null));
    }

    @Test
    public void equalsSelf() {
        EntityUnderTest entity = EntityBase.createEntity(EntityUnderTest.class);
        assertTrue(entity.equals(entity));
    }

    @Test
    public void equalsWrongType() {
        EntityUnderTest entity = EntityBase.createEntity(EntityUnderTest.class);
        assertFalse(entity.equals("a string"));
    }

    @Test
    public void equalsAndHashCodeWithDifferentIdentifiers() {
        EntityUnderTest firstEntity = EntityBase
                .createEntity(EntityUnderTest.class);
        EntityUnderTest secondEntity = EntityBase
                .createEntity(EntityUnderTest.class);

        assertFalse(firstEntity.equals(secondEntity));
        assertFalse(firstEntity.hashCode() == secondEntity.hashCode());
    }

    @Test
    public void equalsAndHashCodeWithEqualIdentifiers() {
        EntityUnderTest firstEntity = EntityBase
                .createEntity(EntityUnderTest.class);
        EntityUnderTest secondEntity = (EntityUnderTest) firstEntity.clone();

        assertTrue(firstEntity.equals(secondEntity));
        assertTrue(firstEntity.hashCode() == secondEntity.hashCode());
    }

    @Test
    public void createFromExistingEntity() throws Exception {
        EntityUnderTest existingEntity = EntityBase
                .createEntity(EntityUnderTest.class);
        FieldUtil.setFieldValue(existingEntity,
                EntityBase.PROP_OPT_LOCK_VERSION, 123L);

        EntityUnderTest newEntity = EntityBase
                .createFromExistingEntity(existingEntity);

        assertFalse(newEntity.getIdentifier() == existingEntity.getIdentifier());
        assertFalse(newEntity.isPersistent());
    }

    @Test
    public void createFromExistingEntity_Reference() {
        EntityUnderTest existingEntity = EntityBase
                .createEntity(EntityUnderTest.class);
        existingEntity.aReference = EntityBase
                .createEntity(ReferencedEntity.class);

        EntityUnderTest newEntity = EntityBase
                .createFromExistingEntity(existingEntity);

        assertSame(existingEntity.aReference, newEntity.aReference);
    }

    @Test
    public void createFromExistingEntity_Aggregate() {
        EntityUnderTest existingEntity = EntityBase
                .createEntity(EntityUnderTest.class);
        existingEntity.anAggregate = EntityBase
                .createEntity(AggregateEntity.class);
        existingEntity.anAggregate.aReference = EntityBase
                .createEntity(ReferencedEntity.class);
        existingEntity.anAggregate.anAggregate = EntityBase
                .createEntity(AggregateEntity.class);

        EntityUnderTest newEntity = EntityBase
                .createFromExistingEntity(existingEntity);

        assertFalse(newEntity.anAggregate.getIdentifier() == existingEntity.anAggregate
                .getIdentifier());
        assertFalse(newEntity.anAggregate.anAggregate.getIdentifier() == existingEntity.anAggregate.anAggregate
                .getIdentifier());
        assertSame(newEntity.anAggregate.aReference,
                existingEntity.anAggregate.aReference);
    }

    @Test
    public void createFromExistingEntity_AggregateList() throws Exception {
        EntityUnderTest existingEntity = EntityBase
                .createEntity(EntityUnderTest.class);
        existingEntity.anAggregateList.add(EntityBase
                .createEntity(AggregateEntity.class));

        EntityUnderTest newEntity = EntityBase
                .createFromExistingEntity(existingEntity);

        assertFalse(newEntity.anAggregateList.get(0).getIdentifier() == existingEntity.anAggregateList
                .get(0).getIdentifier());
    }

    @Test
    public void createFromExistingEntity_AggregateArray() throws Exception {
        EntityUnderTest existingEntity = EntityBase
                .createEntity(EntityUnderTest.class);
        existingEntity.anAggregateArray = new AggregateEntity[] { EntityBase
                .createEntity(AggregateEntity.class) };

        EntityUnderTest newEntity = EntityBase
                .createFromExistingEntity(existingEntity);

        assertFalse(newEntity.anAggregateArray[0].getIdentifier() == existingEntity.anAggregateArray[0]
                .getIdentifier());
    }

    @Test
    public void createFromExistingEntity_AggregateMap() throws Exception {
        EntityUnderTest existingEntity = EntityBase
                .createEntity(EntityUnderTest.class);
        existingEntity.anAggregateMap.put("key",
                EntityBase.createEntity(AggregateEntity.class));

        EntityUnderTest newEntity = EntityBase
                .createFromExistingEntity(existingEntity);

        assertFalse(newEntity.anAggregateMap.get("key").getIdentifier() == existingEntity.anAggregateMap
                .get("key").getIdentifier());
    }
}
