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

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a {@link DataSourceSequence} that uses JNDI to lookup the data
 * source.
 * 
 * @author Petter Holmström
 */
public class JndiDataSourceSequence extends DataSourceSequence {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final String jndiName;

    /**
     * Creates a new <code>JndiDataSourceSequence</code>.
     * 
     * @param sequenceName
     *            the name of the database sequence.
     * @param jndiName
     *            the JNDI name of the data source.
     */
    public JndiDataSourceSequence(String sequenceName, String jndiName) {
        super(sequenceName);
        assert jndiName != null && !jndiName.isEmpty() : "jndiName must not be null nor empty";
        this.jndiName = jndiName;
    }

    @Override
    protected DataSource lookupDataSource() {
        log.info("Looking up data source with JNDI name {}", getJndiName());
        try {
            return (DataSource) new InitialContext().lookup(getJndiName());
        } catch (NamingException e) {
            log.error("Could not find data source", e);
            throw new IllegalStateException("Could not find data source", e);
        }
    }

    /**
     * Returns the JNDI name of the data source.
     */
    public final String getJndiName() {
        return jndiName;
    }

}
