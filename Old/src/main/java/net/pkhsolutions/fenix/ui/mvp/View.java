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
import java.util.Map;

/**
 * This interface represents a View in the Model-View-Presenter (MVP) pattern.
 * The view is responsible for displaying information to the user when requested
 * by the presenter. The view also informs the presenter of actions taken by the
 * user. In addition, it is possible to fire {@link ViewEvent}s from the view.
 * <p>
 * Normally, there is a circular dependency between a view and a presenter. That
 * is, the view holds a reference to its presenter and the presenter holds a
 * reference to its view. In the case of this particular MVP-implementation, the
 * view is always initialized first and is also responsible for both creating
 * and initializing the presenter (see {@link #init()}).
 * 
 * @see AbstractView
 * @author Petter Holmström
 */
public interface View extends Serializable {

	/**
	 * Gets the display name of the view to be shown in the UI. If this method
	 * is called before the view has been initialized, the results are
	 * undefined.
	 * 
	 * @see #init()
	 * @return the display name
	 */
	String getDisplayName();

	/**
	 * Gets the description of the view to be shown in the UI. If this method is
	 * called before the view has been initialized, the results are undefined.
	 * 
	 * @see #init()
	 * @return the description
	 */
	String getDescription();

	/**
	 * Initializes the view and the presenter. If the view will be shown outside
	 * of a {@link ViewController}, the view should create any GUI components in
	 * this method call. Otherwise, {@link #showView(Map)} may be a good
	 * alternative. Implementations may also choose to initialize the GUI
	 * components in a completely different method, if that is more suitable.
	 * 
	 * @see #showView(ViewController, Map)
	 */
	void init();

	/**
	 * This method is called by the view controller before it closes the view.
	 * The view can prevent itself from being closed by returning false, e.g. if
	 * the user has unsaved changes.
	 * <p>
	 * If the view will not be used outside of the view controller, it may
	 * dispose of any UI resources in this method. This is, however, not a
	 * requirement.
	 * 
	 * @return true if it is OK to close the view, false if the view should
	 *         remain open.
	 */
	boolean okToClose();

	/**
	 * This method is called by the view controller when the view is opened and
	 * added to the view stack. If the view will not be used outside of the view
	 * controller, it may create the GUI components and allocate resources in
	 * this method.
	 * 
	 * @see #init()
	 * 
	 * @param viewController
	 *            the view controller (never <code>null</code>).
	 * @param userData
	 *            a map of user-definable parameters (may be <code>null</code>).
	 */
	void showView(ViewController viewController, Map<String, Object> userData);

	/**
	 * Registers a listener to be notified of {@link ViewEvent}s. A listener can
	 * be registered several times and will be notified once for each
	 * registration. If the listener is <code>null</code>, nothing happens.
	 * 
	 * @param listener
	 *            the listener to add.
	 */
	void addListener(ViewListener listener);

	/**
	 * Unregisters a listener previously registered using
	 * {@link #addListener(ViewListener)}. If the listener was registered
	 * multiple times, it will be notified one time less after this method
	 * invocation. If the listener is <code>null</code> or was never added,
	 * nothing happens.
	 * 
	 * @param listener
	 *            the listener to remove.
	 */
	void removeListener(ViewListener listener);

	/**
	 * Fires <code>event</code> to all the registered listeners. This method is
	 * primarily intended to be called by the presenter, but may be called by
	 * other classes as well.
	 * 
	 * @param event
	 *            the event to fire.
	 */
	void fireViewEvent(ViewEvent event);
}
