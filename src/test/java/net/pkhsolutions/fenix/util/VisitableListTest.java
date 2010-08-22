/*
 * Copyright (c) 2010 The original author(s)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.pkhsolutions.fenix.util;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import net.pkhsolutions.fenix.util.VisitableList.Visitor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link VisitableList}.
 * 
 * @author Petter Holmström
 */
public class VisitableListTest {

	private VisitableList<String> list;
	private Visitor<String> visitor;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		list = new VisitableList<String>();
		visitor = createMock(Visitor.class);
	}

	@After
	public void tearDown() {
		try {
			verify(visitor);
		} catch (IllegalStateException e) {
			// Ignore it, we are not using the visitor mock in this test.
		}
	}

	@Test
	public void testVisitNoItems() {
		assertTrue(list.getBackend().isEmpty());
		list.visitList(new Visitor<String>() {

			@Override
			public void visit(String item) {
				fail("This method should never be called when there are no listeners");
			}
		});
	}

	@Test
	public void testVisitNullVisitor() {
		list.visitList(null); // Should not throw an exception
	}

	@Test
	public void testVisitOneItem() {
		// Instruct mocks
		visitor.visit("Hello");
		replay(visitor);

		// Run test
		list.add("Hello");
		assertEquals(1, list.getBackend().size());
		list.visitList(visitor);
	}

	@Test
	public void testVisitTwoItems() {
		// Instruct mocks
		visitor.visit("Hello");
		visitor.visit("World");
		replay(visitor);

		// Run test
		list.add("Hello");
		list.add("World");
		assertEquals(2, list.getBackend().size());
		list.visitList(visitor);
	}

	@Test
	public void testAddNullItem() {
		list.add(null);
		assertTrue(list.getBackend().isEmpty());
	}

	@Test
	public void testRemoveNullItem() {
		list.remove(null); // Should not throw an exception
	}

	@Test
	public void testRemoveNonexistentItem() {
		list.remove("Nonexistent"); // Should not throw an exception
	}

	@Test
	public void testRemoveItem() {
		list.add("Hello");
		assertFalse(list.getBackend().isEmpty());
		list.remove("Hello");
		assertTrue(list.getBackend().isEmpty());
	}

	@Test
	public void testAddSameItemTwice() {
		list.add("Hello");
		list.add("Hello");
		assertEquals(2, list.getBackend().size());
	}

	@Test
	public void testAddSameItemTwiceRemoveOnce() {
		list.add("Hello");
		list.add("World");
		list.add("Hello");
		list.remove("Hello");
		assertEquals("World", list.getBackend().get(0));
	}

}
