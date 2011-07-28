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
package com.github.peholmst.fenix.entity.util;

import javax.persistence.metamodel.SingularAttribute;

/**
 * An order instruction specifies how an entity set should be ordered. Ordering
 * is done by a single attribute and can be either ascending or descending.
 * 
 * @author Petter Holmström
 */
public final class OrderInstruction<E extends EntityBase> implements
        java.io.Serializable {

    private static final long serialVersionUID = 5991285022478824416L;

    private final boolean ascending;

    private SingularAttribute<E, ?> attribute;

    private OrderInstruction(SingularAttribute<E, ?> attribute,
            boolean ascending) {
        assert attribute != null : "attribute must not be null";
        this.attribute = attribute;
        this.ascending = ascending;
    }

    /**
     * Gets the attribute to order by.
     * 
     * @return the attribute.
     */
    public SingularAttribute<E, ?> getAttribute() {
        return attribute;
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
     * Creates a new <code>OrderInstruction</code> for the given attribute,
     * using ascending order.
     * 
     * @param attribute
     *            the attribute to order by.
     * @return the order instruction.
     */
    public static <E extends EntityBase> OrderInstruction<E> ascending(
            SingularAttribute<E, ?> attribute) {
        return new OrderInstruction<E>(attribute, true);
    }

    /**
     * Creates a new <code>OrderInstruction</code> for the given attribute,
     * using descending order.
     * 
     * @param attribute
     *            the attribute to order by.
     * @return the order instruction.
     */
    public static <E extends EntityBase> OrderInstruction<E> descending(
            SingularAttribute<E, ?> attribute) {
        return new OrderInstruction<E>(attribute, false);
    }
}
