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

import java.util.EventObject;

/**
 * Base class for events that are fired by {@link View}s. Views can use these
 * events to inform other objects of significant events, e.g. events that affect
 * the flow of the application itself (login, logout, etc.).
 * 
 * @see View#fireViewEvent(ViewEvent)
 * 
 * @author Petter Holmström
 */
public abstract class ViewEvent extends EventObject {

	private static final long serialVersionUID = -4292025849319932278L;

	/**
	 * Creates a new <code>ViewEvent</code>.
	 * 
	 * @param source
	 *            the view in which the event originally occurred.
	 */
	public ViewEvent(View source) {
		super(source);
	}

	@Override
	public View getSource() {
		return (View) super.getSource();
	}

}
