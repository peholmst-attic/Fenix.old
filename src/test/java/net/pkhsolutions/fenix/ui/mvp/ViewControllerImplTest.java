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
package net.pkhsolutions.fenix.ui.mvp;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import net.pkhsolutions.fenix.ui.mvp.ViewControllerListener.Direction;
import net.pkhsolutions.fenix.util.MockUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for {@link ViewControllerImpl}.
 * 
 * @author Petter Holmström
 */
public class ViewControllerImplTest {

	ViewControllerListener listener;
	ViewControllerImpl controller;
	View view;
	View view2;
	View view3;

	@Before
	public void setUp() {
		view = createMock(View.class);
		view2 = createMock(View.class);
		view3 = createMock(View.class);
		listener = createMock(ViewControllerListener.class);
		controller = new ViewControllerImpl();
		controller.addListener(listener);
	}

	@After
	public void tearDown() {
		MockUtils.verifyMock(listener, view, view2, view3);
		controller.removeListener(listener);
	}

	@Test
	public void testNoViews() {
		replay(listener); // Listener should never be notified

		assertNull(controller.getCurrentView());
		assertNull(controller.getFirstView());
		assertTrue(controller.getTrail().isEmpty());
		assertFalse(controller.goBack());
		assertFalse(controller.goToFirstView());
	}

	@Test
	public void testAddFirstView_NoParameters() {
		listener.currentViewChanged(controller, null, view, Direction.FORWARD);
		replay(listener);
		view.showView(controller, null);
		replay(view);

		assertTrue(controller.goToView(view));
		assertSame(view, controller.getCurrentView());
		assertSame(view, controller.getFirstView());
		assertEquals(1, controller.getTrail().size());
	}

	@Test
	public void goToFirstViewTwice_NoParameters() {
		listener.currentViewChanged(controller, null, view, Direction.FORWARD);
		replay(listener);
		view.showView(controller, null);
		replay(view);

		assertTrue(controller.goToView(view));
		assertFalse(controller.goToView(view));
		assertSame(view, controller.getCurrentView());
		assertSame(view, controller.getFirstView());
		assertEquals(1, controller.getTrail().size());
	}

	@Test
	public void testGoToSecondView_NoParameters() {
		listener.currentViewChanged(controller, null, view, Direction.FORWARD);
		listener.currentViewChanged(controller, view, view2, Direction.FORWARD);
		replay(listener);
		view.showView(controller, null);
		replay(view);
		view2.showView(controller, null);
		replay(view2);

		assertTrue(controller.goToView(view));
		assertTrue(controller.goToView(view2));
		assertSame(view, controller.getFirstView());
		assertSame(view2, controller.getCurrentView());
		assertEquals(2, controller.getTrail().size());
	}

	@Test
	public void testGoBackToFirstView_NoParameters() {
		listener.currentViewChanged(controller, null, view, Direction.FORWARD);
		listener.currentViewChanged(controller, view, view2, Direction.FORWARD);
		listener.currentViewChanged(controller, view2, view, Direction.BACKWARD);
		replay(listener);
		view.showView(controller, null);
		replay(view);
		view2.showView(controller, null);
		expect(view2.okToClose()).andReturn(true);
		replay(view2);

		assertTrue(controller.goToView(view));
		assertTrue(controller.goToView(view2));
		assertTrue(controller.goToView(view));
		assertSame(view, controller.getCurrentView());
		assertSame(view, controller.getFirstView());
		assertEquals(1, controller.getTrail().size());
	}

	@Test
	public void testGoToFirstView() {
		listener.currentViewChanged(controller, null, view, Direction.FORWARD);
		listener.currentViewChanged(controller, view, view2, Direction.FORWARD);
		listener.currentViewChanged(controller, view2, view, Direction.BACKWARD);
		replay(listener);
		view.showView(controller, null);
		replay(view);
		view2.showView(controller, null);
		expect(view2.okToClose()).andReturn(true);
		replay(view2);

		assertTrue(controller.goToView(view));
		assertTrue(controller.goToView(view2));
		assertTrue(controller.goToFirstView());
		assertSame(view, controller.getCurrentView());
		assertSame(view, controller.getFirstView());
		assertEquals(1, controller.getTrail().size());
	}

	@Test
	public void testGoBackToFirsView_CurrentViewRefuses() {
		listener.currentViewChanged(controller, null, view, Direction.FORWARD);
		listener.currentViewChanged(controller, view, view2, Direction.FORWARD);
		replay(listener);
		view.showView(controller, null);
		replay(view);
		view2.showView(controller, null);
		expect(view2.okToClose()).andReturn(false);
		replay(view2);

		assertTrue(controller.goToView(view));
		assertTrue(controller.goToView(view2));
		assertFalse(controller.goToView(view));
		assertSame(view2, controller.getCurrentView());
		assertSame(view, controller.getFirstView());
		assertEquals(2, controller.getTrail().size());
	}

	@Test
	public void testGoBackToFirstView_SecondViewRefuses() {
		listener.currentViewChanged(controller, null, view, Direction.FORWARD);
		listener.currentViewChanged(controller, view, view2, Direction.FORWARD);
		listener.currentViewChanged(controller, view2, view3, Direction.FORWARD);
		listener.currentViewChanged(controller, view3, view2,
				Direction.BACKWARD);
		replay(listener);
		view.showView(controller, null);
		replay(view);
		view2.showView(controller, null);
		expect(view2.okToClose()).andReturn(false);
		replay(view2);
		view3.showView(controller, null);
		expect(view3.okToClose()).andReturn(true);
		replay(view3);

		assertTrue(controller.goToView(view));
		assertTrue(controller.goToView(view2));
		assertTrue(controller.goToView(view3));
		// The current view changes all right, but to view2 instead of view, as
		// view2 refuses to close.
		assertTrue(controller.goToView(view));
		assertSame(view2, controller.getCurrentView());
		assertSame(view, controller.getFirstView());
		assertEquals(2, controller.getTrail().size());

	}

	@Test
	public void testGoBack_OK() {
		listener.currentViewChanged(controller, null, view, Direction.FORWARD);
		listener.currentViewChanged(controller, view, view2, Direction.FORWARD);
		listener.currentViewChanged(controller, view2, view, Direction.BACKWARD);
		replay(listener);
		view.showView(controller, null);
		replay(view);
		view2.showView(controller, null);
		expect(view2.okToClose()).andReturn(true);
		replay(view2);

		assertTrue(controller.goToView(view));
		assertTrue(controller.goToView(view2));
		assertTrue(controller.goBack());
		assertSame(view, controller.getCurrentView());
		assertSame(view, controller.getFirstView());
		assertEquals(1, controller.getTrail().size());
	}

	@Test
	public void testGoBack_Refuse() {
		listener.currentViewChanged(controller, null, view, Direction.FORWARD);
		listener.currentViewChanged(controller, view, view2, Direction.FORWARD);
		replay(listener);
		view.showView(controller, null);
		replay(view);
		view2.showView(controller, null);
		expect(view2.okToClose()).andReturn(false);
		replay(view2);

		assertTrue(controller.goToView(view));
		assertTrue(controller.goToView(view2));
		assertFalse(controller.goBack());
		assertSame(view2, controller.getCurrentView());
		assertSame(view, controller.getFirstView());
		assertEquals(2, controller.getTrail().size());
	}

	@Test
	public void testGoBack_OnlyOneView() {
		listener.currentViewChanged(controller, null, view, Direction.FORWARD);
		replay(listener);
		view.showView(controller, null);
		replay(view);

		assertTrue(controller.goToView(view));
		assertFalse(controller.goBack());
		assertSame(view, controller.getCurrentView());
		assertSame(view, controller.getFirstView());
		assertEquals(1, controller.getTrail().size());
	}

	@Test
	public void testGoToView_SingleParameter() {
		HashMap<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("hello", "world");

		listener.currentViewChanged(controller, null, view, Direction.FORWARD);
		replay(listener);
		view.showView(controller, userMap);
		replay(view);

		assertTrue(controller.goToView(view, "hello", "world"));
		assertSame(view, controller.getCurrentView());
		assertSame(view, controller.getFirstView());
		assertEquals(1, controller.getTrail().size());
	}
}
