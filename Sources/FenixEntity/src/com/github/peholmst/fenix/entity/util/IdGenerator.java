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

import com.github.peholmst.fenix.common.SystemConstants;

/**
 * This is a helper class that generates unique numeric ID values by using an
 * underlying {@link Sequence}. Its primary purpose is to make it possible to
 * generate Entity identifiers early (i.e. when they are created as opposed to
 * when they are persisted) without having to use UUIDs.
 * <p>
 * By default, a {@link JndiDataSourceSequence} is used and configured using
 * {@link SystemConstants#ENTITY_IDENTIFIER_SEQUENCE_NAME} and
 * {@link SystemConstants#JDBC_DATASOURCE_JNDI_NAME}.
 * 
 * @author Petter Holmström
 */
public class IdGenerator {

	private static Sequence sequence = new JndiDataSourceSequence(
			SystemConstants.ENTITY_IDENTIFIER_SEQUENCE_NAME,
			SystemConstants.JDBC_DATASOURCE_JNDI_NAME);

	private IdGenerator() {
	}

	/**
	 * Gets the next available ID value. As long as the underlying sequence
	 * remains the same, subsequent calls to this method will never return the
	 * same value.
	 * 
	 * @return the ID value.
	 */
	public static long getNextValue() {
		return getSequence().getNextValue();
	}

	/**
	 * Set the sequence to use for generating ID values.
	 * 
	 * @param sequence
	 *            the sequence (must not be <code>null</code>).
	 */
	public static synchronized void setSequence(Sequence sequence) {
		assert sequence != null : "sequence must not be null";
		IdGenerator.sequence = sequence;
	}

	/**
	 * Gets the sequence that is used for generating ID values.
	 * 
	 * @return the sequence (never <code>null</code>).
	 */
	public static synchronized Sequence getSequence() {
		return IdGenerator.sequence;
	}
}
