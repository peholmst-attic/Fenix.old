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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a utility class that maintains a list of items and allows clients to
 * perform actions on each item by visiting them.
 * 
 * @see Visitor
 * @see #visitList(Visitor)
 * 
 * @author Petter Holmström
 * 
 * @param <T>
 *            the item type.
 */
public class VisitableList<T> implements Serializable {

	/*
	 * A future implementation could implement the List interface and either use
	 * a default backend instance or let the client provide the backend
	 * instance. In this case, the VisitableList would wrap the provided backend
	 * list.
	 */

	private static final long serialVersionUID = -6537218047823884495L;

	private LinkedList<T> list;

	/**
	 * Adds <code>item</code> to the list. It is possible to add the same item
	 * multiple times. If the item is <code>null</code>, nothing happens.
	 * 
	 * @param item
	 *            the item to add.
	 */
	public void add(T item) {
		if (item == null) {
			return;
		}
		if (list == null) {
			list = new LinkedList<T>();
		}
		list.add(item);
	}

	/**
	 * Removes <code>item</code> from the list. If the item has been added
	 * several times, only the first occurrence will be removed. If the item is
	 * <code>null</code> or has never been added, nothing happens.
	 * 
	 * @param item
	 *            the item to remove.
	 */
	public void remove(T item) {
		if (item == null) {
			return;
		}
		if (list != null) {
			list.remove(item);
			if (list.isEmpty()) {
				list = null;
			}
		}
	}

	/**
	 * Gets the backend list that contains all the items.
	 * 
	 * @return an unmodifiable list instance (never <code>null</code>).
	 */
	@SuppressWarnings("unchecked")
	public List<T> getBackend() {
		return list == null ? Collections.EMPTY_LIST : Collections
				.unmodifiableList(list);
	}

	/**
	 * This interface defines a Visitor that visits every item in the list.
	 * 
	 * @author Petter Holmström
	 * 
	 * @param <T>
	 *            the item type.
	 */
	public static interface Visitor<T> {

		/**
		 * Performs some action on <code>item</code>.
		 * 
		 * @param item
		 *            the item to visit (never <code>null</code>).
		 */
		public void visit(T item);
	}

	/**
	 * Visits all the items in the list. For example, if the list is used to
	 * maintain a collection of listeners and all the listener has to be
	 * notified when something has changed, the code could like something like
	 * this:
	 * 
	 * <pre>
	 * <code>
	 * public interface MyListener {
	 *     public void somethingHasChanged(Object source);
	 * }
	 * 
	 * ...
	 * 
	 * myListenerList.{@link #visitList(Visitor) visitList}(new {@link Visitor}&lt;MyListener&gt;() {
	 *     public void {@link Visitor#visit(Object) visit}(MyListener listener) {
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
	public void visitList(Visitor<T> visitor) {
		if (visitor == null || list == null) {
			return;
		}
		@SuppressWarnings("unchecked")
		LinkedList<T> clonedList = (LinkedList<T>) list.clone();
		for (T listener : clonedList) {
			visitor.visit(listener);
		}
	}
}
