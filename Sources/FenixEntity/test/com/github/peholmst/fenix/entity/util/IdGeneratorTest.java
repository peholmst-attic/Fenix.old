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
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.peholmst.fenix.common.SystemConstants;

/**
 * Test case for {@link IdGenerator}.
 * 
 * @author Petter Holmström
 */
public class IdGeneratorTest {

    @Test
    public void defaults() {
        assertTrue(IdGenerator.getSequence() instanceof JndiDataSourceSequence);
        JndiDataSourceSequence seq = (JndiDataSourceSequence) IdGenerator
                .getSequence();
        assertEquals(SystemConstants.ENTITY_IDENTIFIER_SEQUENCE_NAME,
                seq.getSequenceName());
        assertEquals(SystemConstants.JDBC_DATASOURCE_JNDI_NAME,
                seq.getJndiName());
    }

    @Test
    public void getNextValue() {
        IdGenerator.setSequence(new InMemorySequence());
        assertEquals(1L, IdGenerator.getNextValue());
    }
}
