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
 * user. The view does not contain any other logic.
 * <p>
 * Normally, there is a circular dependency between a view and a presenter. That
 * is, the view holds a reference to its presenter and the presenter holds a
 * reference to its view. In the case of this particular MVP-implementation, the
 * view is always initialized first and is also responsible for initializing the
 * presenter (see {@link #init()}).
 * <p>
 * This interface does not require the presenter class to extend any abstract
 * base classes or implement any interfaces. It does not contain any methods for
 * injecting the presenter instance into the view either.
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
	 * alternative.
	 * 
	 * @see #showView(Map)
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
	 * @param userData
	 *            a map of user-definable parameters (may be <code>null</code>).
	 */
	void showView(Map<String, Object> userData);
}
