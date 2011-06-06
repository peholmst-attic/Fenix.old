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

/**
 * Listener interface to be implemented by classes that need to be notified of
 * {@link ViewEvent}s.
 * 
 * @see View#addListener(ViewListener)
 * @see View#removeListener(ViewListener)
 * 
 * @author Petter Holmström
 */
public interface ViewListener extends Serializable {

	/**
	 * Called when <code>event</code> has occurred.
	 * 
	 * @param event
	 *            the event.
	 */
	public void onViewEvent(ViewEvent event);

}
