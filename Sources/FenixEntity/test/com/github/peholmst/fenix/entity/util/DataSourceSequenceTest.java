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

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test case for {@link DataSourceSequence}.
 * 
 * @author Petter Holmström
 */
public class DataSourceSequenceTest {

    private static JdbcDataSource dataSource;

    @BeforeClass
    public static void setUpDataSource() throws Exception {
        dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement
                .execute("CREATE SEQUENCE mySequence START WITH 1 INCREMENT BY 10");
        statement.close();
        connection.close();
    }

    static class SequenceUnderTest extends DataSourceSequence {

        public SequenceUnderTest() {
            super("mySequence");
        }

        @Override
        protected DataSource lookupDataSource() {
            return dataSource;
        }
    }

    @Test
    public void initialReservationByConstructor() {
        SequenceUnderTest seq = new SequenceUnderTest();
        assertEquals(1L, seq.getNextValue());
    }

    @Test
    public void loopThroughNextValuesUntilRangeRunsOut() {
        SequenceUnderTest seq = new SequenceUnderTest();
        long oldValue = seq.getNextValue();
        for (int i = 0; i < 10; ++i) {
            long newValue = seq.getNextValue();
            assertEquals(oldValue + 1, newValue);
            oldValue = newValue;
        }
    }

}
