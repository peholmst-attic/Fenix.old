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

/**
 * This is a listener interface that allows implementors to be notified when the
 * current view of a {@link ViewController} is changed.
 * 
 * @author Petter Holmström
 */
public interface ViewControllerListener {

	/**
	 * This enumeration defines the directions of the current view change of a
	 * view controller. If the change results in the stack growing, the
	 * direction is FORWARD. Otherwise, the direction is BACKWARD.
	 * 
	 * @author Petter Holmström
	 */
	enum Direction {
		FORWARD, BACKWARD
	}

	/**
	 * This method is called by a {@link ViewController} instance when its
	 * current view has changed.
	 * 
	 * @param source
	 *            the source of the event (never <code>null</code>).
	 * @param oldView
	 *            the old view, or <code>null</code> if <code>newView</code> was
	 *            the first view to be added to the controller.
	 * @param newView
	 *            the new view (never <code>null</code>).
	 * @param direction
	 *            <code>FORWARD</code> if the view stack grew as a result of the
	 *            change, <code>BACKWARD</code> if it shrunk.
	 */
	void currentViewChanged(ViewController source, View oldView, View newView,
			Direction direction);
}
