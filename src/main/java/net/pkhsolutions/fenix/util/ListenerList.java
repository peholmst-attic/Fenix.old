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

import java.io.Serializable;
import java.util.LinkedList;

/**
 * This is a utility class that can be used by classes that need to maintain a
 * list of listeners and notify them of different things.
 * 
 * @author Petter Holmström
 * 
 * @param <L>
 *            the listener type.
 */
public class ListenerList<L> implements Serializable {

	private static final long serialVersionUID = -6537218047823884495L;

	private LinkedList<L> listenerList;

	/**
	 * Adds <code>listener</code> to the list. It is possible to add the same
	 * listener multiple times. If the listener is <code>null</code>, nothing
	 * happens.
	 * 
	 * @param listener
	 *            the listener to add.
	 */
	public void addListener(L listener) {
		if (listener == null) {
			return;
		}
		if (listenerList == null) {
			listenerList = new LinkedList<L>();
		}
		listenerList.add(listener);
	}

	/**
	 * Removes <code>listener</code> from the list. If the listener has been
	 * added several times, only the first occurrence will be removed. If the
	 * listener is <code>null</code> or has never been added, nothing happens.
	 * 
	 * @param listener
	 *            the listener to remove.
	 */
	public void removeListener(L listener) {
		if (listener == null) {
			return;
		}
		if (listenerList != null) {
			listenerList.remove(listener);
			if (listenerList.isEmpty()) {
				listenerList = null;
			}
		}
	}

	/**
	 * This interface defines a Visitor that visits every listener in the list.
	 * 
	 * @author Petter Holmström
	 * 
	 * @param <L>
	 *            the listener type.
	 */
	public static interface ListenerVisitor<L> {

		/**
		 * Performs some action on <code>listener</code>, such as invoking an
		 * event notification method.
		 * 
		 * @param listener
		 *            the listener to visit (never <code>null</code>).
		 */
		public void visit(L listener);
	}

	/**
	 * Visits all the listeners in the list. This can be used to invoke
	 * different methods on the listeners. For example:
	 * 
	 * <pre>
	 * <code>
	 * public interface MyListener {
	 *     public void somethingHasChanged(Object source);
	 * }
	 * 
	 * ...
	 * 
	 * myListenerList.visitListeners(new {@link ListenerVisitor}&lt;MyListener&gt;() {
	 *     public void {@link ListenerVisitor#visit(Object) visit}(MyListener listener) {
	 *         listener.somethingHasChanged(theSource);
	 *     }
	 * });
	 * </code>
	 * </pre>
	 * 
	 * If the visitor is <code>null</code>, nothing happens.
	 * 
	 * @param visitor
	 *            the visitor to use.
	 */
	public void visitListeners(ListenerVisitor<L> visitor) {
		if (visitor == null) {
			return;
		}
		@SuppressWarnings("unchecked")
		LinkedList<L> clonedList = (LinkedList<L>) listenerList.clone();
		for (L listener : clonedList) {
			visitor.visit(listener);
		}
	}
}
