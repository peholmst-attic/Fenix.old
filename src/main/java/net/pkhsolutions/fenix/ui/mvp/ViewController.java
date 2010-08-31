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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * TODO Document me! 
 * 
 * @author Petter Holmström
 */
public interface ViewController extends Serializable {

	/**
	 * Gets the current view, if available. This is the top element in the stack
	 * returned by {@link #getTrail()}. If the stack is empty, <code>null</code>
	 * is returned.
	 * 
	 * @return the current view, or <code>null</code> if the controller does not
	 *         manage any views.
	 */
	View getCurrentView();

	/**
	 * Gets the first view, if available. This is the bottom element in the
	 * stack returned by {@link #getTrail()}. If the stack is empty,
	 * <code>null</code> is returned.
	 * 
	 * @return the first view, or <code>null</code> if the controller does not
	 *         manage any views.
	 */
	View getFirstView();

	/**
	 * Attempts to close the current view and go back to the previous view. If
	 * the current view aborts the operation or if there are no views to go back
	 * to (i.e. the current view is the first view), this method returns false.
	 * 
	 * @see View#okToClose()
	 * @see #getTrail()
	 * @return true if the current view was changed as a result of this method
	 *         call, false if not (or if there are no views at all).
	 */
	boolean goBack();

	/**
	 * Attempts to go back to the first view, closing all the other views on the
	 * stack. If any of the views abort the operation, or if the first view is
	 * already the current view, or if there are no views at all on the stack,
	 * this method returns false.
	 * 
	 * @see View#okToClose()
	 * @see #getTrail()
	 * @return true if the current view was changed as a result of this method
	 *         call, false if not (or if there are no views at all).
	 */
	boolean goToFirstView();

	/**
	 * Same as calling {@link #goToView(View, Map) goToView(View, null)}.
	 * 
	 * @param view
	 *            the view to go to.
	 * @return true if the current view was changed as a result of this method
	 *         call, false if not (or if there are no views at all).
	 */
	boolean goToView(View view);

	/**
	 * Same as calling {@link #goToView(View, Map)}, but with the user data map
	 * containing a single key-value pair.
	 * 
	 * @param view
	 *            the view to go to.
	 * @param userDataKey
	 *            the key of the user data property.
	 * @param userDataValue
	 *            the value of the user data property.
	 * @return true if the current view was changed as a result of this method
	 *         call, false if not (or if there are no views at all).
	 */
	boolean goToView(View view, String userDataKey, Object userDataValue);

	/**
	 * Attempts to go to the specified view. If the view is not already on the
	 * stack, it is added to the top of the stack and made the current view. Any
	 * user defined data is passed to the view via the
	 * {@link View#showView(Map)} method. If the view is on the stack, all the
	 * views on top of the view are closed. In this case,
	 * {@link View#showView(Map)} will not be called.
	 * <p>
	 * This method will return true if the current view has changed. This may
	 * not necessarily mean that the current view has changed to the specified
	 * view, though. If any of the views in the stack refuses to close, the
	 * current view will change to that view. This method only returns false if
	 * the current view did not change at all.
	 * 
	 * @see View#showView(Map)
	 * @see View#okToClose()
	 * @see #getTrail()
	 * @param view
	 *            the view to go to.
	 * @param userData
	 *            a map with user defined properties that is passed to the view,
	 *            may be <code>null</code>.
	 * @return true if the current view was changed as a result of this method
	 *         call, false if not (or if there are no views at all).
	 */
	boolean goToView(View view, Map<String, Object> userData);

	/**
	 * Gets the stack of open views, with the topmost view being the current
	 * view.
	 * 
	 * @return an unmodifiable list representing the stack, with the bottom most
	 *         view at index 0.
	 */
	List<View> getTrail();

	/**
	 * Registers a listener to be notified when the current view is changed. A
	 * listener can be registered several times and will be notified once for
	 * each registration. If the listener is <code>null</code>, nothing happens.
	 * 
	 * @param listener
	 *            the listener to add.
	 */
	void addListener(ViewControllerListener listener);

	/**
	 * Unregisters a listener previously registered using
	 * {@link #addListener(ViewControllerListener)}. If the listener was
	 * registered multiple times, it will be notified one time less after this
	 * method invocation. If the listener is <code>null</code> or was never
	 * added, nothing happens.
	 * 
	 * @param listener
	 *            the listener to remove.
	 */
	void removeListener(ViewControllerListener listener);

}
