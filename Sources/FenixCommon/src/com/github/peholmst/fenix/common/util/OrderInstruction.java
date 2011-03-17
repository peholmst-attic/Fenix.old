/*
 * Copyright (c) 2011 Petter Holmström
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.peholmst.fenix.common.util;

/**
 * An order instruction specifies how a data set should be ordered. Ordering is
 * done by a single property name and can be either ascending or descending.
 * 
 * @author Petter Holmström
 */
public final class OrderInstruction implements java.io.Serializable {

	private static final long serialVersionUID = 5991285022478824416L;

	private final boolean ascending;

	private final String propertyName;

	private OrderInstruction(String propertyName, boolean ascending) {
		assert propertyName != null : "propertyName must not be null";
		this.propertyName = propertyName;
		this.ascending = ascending;
	}

	/**
	 * Gets the name of the property to order by.
	 * 
	 * @return the property name.
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Returns whether the ordering should be ascending or descending.
	 * 
	 * @return true for ascending order, false for descending order.
	 */
	public boolean isAscending() {
		return ascending;
	}

	/**
	 * Creates a new <code>OrderInstruction</code> for the given property name,
	 * using ascending order.
	 * 
	 * @param propertyName
	 *            the property name to order by.
	 * @return the order instruction.
	 */
	public static OrderInstruction ascending(String propertyName) {
		return new OrderInstruction(propertyName, true);
	}

	/**
	 * Creates a new <code>OrderInstruction</code> for the given property name,
	 * using descending order.
	 * 
	 * @param propertyName
	 *            the property name to order by.
	 * @return the order instruction.
	 */
	public static OrderInstruction descending(String propertyName) {
		return new OrderInstruction(propertyName, false);
	}
}
