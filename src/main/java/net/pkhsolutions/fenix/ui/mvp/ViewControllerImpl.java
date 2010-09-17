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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import net.pkhsolutions.fenix.ui.mvp.ViewControllerListener.Direction;
import net.pkhsolutions.fenix.util.VisitableList;
import net.pkhsolutions.fenix.util.VisitableList.Visitor;

import org.springframework.util.Assert;

/**
 * This is the default implementation of {@link ViewController}. Initially, it
 * contains no views at all. A root view can be specified by calling
 * {@link #goToView(View)} (or any of the other variants of the method).
 * 
 * @author Petter Holmström
 */
public class ViewControllerImpl implements ViewController {

	private static final long serialVersionUID = -6930768596339648457L;

	private VisitableList<ViewControllerListener> listenerList = new VisitableList<ViewControllerListener>();

	private Stack<View> viewStack = new Stack<View>();

	@Override
	public View getCurrentView() {
		return viewStack.isEmpty() ? null : viewStack.peek();
	}

	@Override
	public View getFirstView() {
		return viewStack.isEmpty() ? null : viewStack.firstElement();
	}

	@Override
	public boolean goBack() {
		if (viewStack.size() <= 1) {
			return false;
		}

		final View oldView = viewStack.peek();
		if (oldView.okToClose()) {
			viewStack.pop();
			fireCurrentViewChanged(oldView, viewStack.peek(),
					Direction.BACKWARD);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean goToFirstView() {
		return viewStack.isEmpty() ? false : goToView(getFirstView());
	}

	@Override
	public boolean goToView(View view) {
		return goToView(view, null);
	}

	@Override
	public boolean goToView(View view, String userDataKey, Object userDataValue) {
		Assert.notNull(userDataKey, "userDataKey must not be null");
		// The view parameter will be checked for null in the call to
		// goToView(View, Map) => no need to check it here
		HashMap<String, Object> userDataMap = new HashMap<String, Object>();
		userDataMap.put(userDataKey, userDataValue);
		return goToView(view, userDataMap);
	}

	@Override
	public boolean goToView(View view, Map<String, Object> userData) {
		Assert.notNull(view, "view must not be null");

		final View oldView = getCurrentView();
		if (view.equals(oldView)) {
			// view is already the current view
			return false;
		}
		if (viewStack.contains(view)) {
			// The view is already in the stack => we are moving backwards
			while (!view.equals(viewStack.peek())) {
				if (viewStack.peek().okToClose()) {
					viewStack.pop();
				} else {
					break;
				}
			}
			// It is possible that the current view != the specified view,
			// therefore we have to check
			final View newView = viewStack.peek();
			if (oldView.equals(newView)) {
				// The current view refused to close => no change
				return false;
			}
			fireCurrentViewChanged(oldView, newView, Direction.BACKWARD);
		} else {
			// Push the view onto the stack
			viewStack.push(view);
			view.showView(this, userData);
			fireCurrentViewChanged(oldView, view, Direction.FORWARD);
		}
		return true;
	}

	@Override
	public List<View> getTrail() {
		return Collections.unmodifiableList(viewStack);
	}

	@Override
	public void addListener(ViewControllerListener listener) {
		listenerList.add(listener);
	}

	@Override
	public void removeListener(ViewControllerListener listener) {
		listenerList.remove(listener);
	}

	/**
	 * Notifies all listeners that the current view has changed.
	 * 
	 * @see ViewControllerListener#currentViewChanged(ViewController, View,
	 *      View, Direction)
	 * 
	 * @param oldView
	 *            the old view.
	 * @param newView
	 *            the new view.
	 * @param direction
	 *            the direction of the change.
	 */
	protected void fireCurrentViewChanged(final View oldView,
			final View newView, final ViewControllerListener.Direction direction) {
		listenerList.visitList(new Visitor<ViewControllerListener>() {
			@Override
			public void visit(ViewControllerListener item) {
				item.currentViewChanged(ViewControllerImpl.this, oldView,
						newView, direction);
			}
		});
	}

}
