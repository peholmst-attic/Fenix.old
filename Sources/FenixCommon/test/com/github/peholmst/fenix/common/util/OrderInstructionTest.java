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
package com.github.peholmst.fenix.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test case for {@link OrderInstruction}.
 * 
 * @author Petter Holmström
 */
public class OrderInstructionTest {

	@Test
	public void ascendingFactoryMethod() {
		OrderInstruction order = OrderInstruction.ascending("propertyName");
		assertEquals("propertyName", order.getPropertyName());
		assertTrue(order.isAscending());
	}

	@Test
	public void descendingFactoryMethod() {
		OrderInstruction order = OrderInstruction.descending("propertyName");
		assertEquals("propertyName", order.getPropertyName());
		assertFalse(order.isAscending());
	}
}
