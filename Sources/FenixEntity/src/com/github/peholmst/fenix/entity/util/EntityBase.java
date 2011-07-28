/*
 * Copyright (c) 2011 The original developers
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

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.github.peholmst.stuff4vaadin.clone.CloneThis;
import com.github.peholmst.stuff4vaadin.clone.CloneUtil;
import com.github.peholmst.stuff4vaadin.common.FieldUtil;

/**
 * This is a base class for all entities. It includes an identifier and a
 * version number for optimistic locking. Equality and hash code is based on the
 * identifier. If none is set, an entity is only equal to itself.
 * <p>
 * Cloning is handled by {@link CloneUtil#deepClone(Cloneable)}, which means
 * that subclasses can use the {@link CloneThis} annotation if they want to.
 * Please note, that the <code>clone()</code> method will make an exact clone of
 * the entity graph, including the identifiers.
 * 
 * @author Petter Holmström
 */
@MappedSuperclass
public abstract class EntityBase implements java.io.Serializable, Cloneable {

    private static final long serialVersionUID = 9158596445082221718L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long identifier;

    @Version
    @Column(name = "OPTLOCKVER")
    protected Long optLockVersion;

    /**
     * Returns the identifier of this entity, or <code>null</code> if it has not
     * been persisted yet.
     */
    public Long getIdentifier() {
        return identifier;
    }

    /**
     * Returns the optimistic locking version number of the entity, or
     * <code>null</code> if it has not been persisted yet.
     */
    public Long getOptLockVersion() {
        return optLockVersion;
    }

    /**
     * Checks if the entity is persistent or transient.
     */
    public boolean isPersistent() {
        return getIdentifier() != null;
    }

    /**
     * Creates a new transient entity instance that is a copy of the specified
     * entity. This means that all entity identifiers and the optimistic locking
     * versions have been set to <code>null</code>. All fields annotated with
     * {@link CloneThis} have also been made transient recursively. Maps, arrays
     * and Collections are made transient if {@link CloneThis#deepClone()} is
     * true.
     * 
     * @param <T>
     *            the type of the entity to create.
     * @param original
     *            the original entity.
     * @return a new entity instance.
     */
    @SuppressWarnings("unchecked")
    public static <T extends EntityBase> T createFromExistingEntity(T original) {
        assert original != null : "oldEntity must not be null";
        T newEntity = (T) original.clone();
        try {
            newEntity.makeTransient();
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Could not create a new entity based on the existing one",
                    e);
        }
        return newEntity;
    }

    /**
     * Makes this entity transient (i.e. sets the identifier and optimistic
     * locking version to <code>null</code>).All fields annotated with
     * {@link CloneThis} have also been made transient recursively. Maps, arrays
     * and Collections are made transient if {@link CloneThis#deepClone()} is
     * true.
     * <p>
     * Note, that this has nothing to do with Java serialization and the
     * <code>transient</code> keyword!
     * 
     * @throws Exception
     *             if something went wrong.
     */
    public void makeTransient() throws Exception {
        makeObjectTransient(this);
    }

    private static void makeObjectTransient(final Object object)
            throws Exception {
        if (object == null) {
            return;
        }
        if (object instanceof EntityBase) {
            ((EntityBase) object).identifier = null;
            ((EntityBase) object).optLockVersion = null;
        }
        FieldUtil.visitAllDeclaredFields(object.getClass(),
                new FieldUtil.AccessibleFieldVisitor() {

                    @Override
                    public void visitAccessibleField(Field field)
                            throws Exception {
                        CloneThis cloneAnnotation = field
                                .getAnnotation(CloneThis.class);
                        if (cloneAnnotation != null) {
                            if (field.getType().isArray()) {
                                makeArrayFieldTransient(field, object,
                                        cloneAnnotation);
                            } else {
                                makeObjectFieldTransient(field, object,
                                        cloneAnnotation);
                            }
                        }
                    }
                });
    }

    @SuppressWarnings("unchecked")
    private static void makeObjectFieldTransient(Field field, Object owner,
            CloneThis cloneAnnotation) throws Exception {
        Object value = field.get(owner);
        if (value instanceof Collection) {
            makeCollectionTransient((Collection<Object>) value, cloneAnnotation);
        } else if (value instanceof Map) {
            makeMapTransient((Map<Object, Object>) value, cloneAnnotation);
        } else {
            makeObjectTransient(value);
        }
    }

    private static void makeCollectionTransient(Collection<Object> collection,
            CloneThis cloneAnnotation) throws Exception {
        if (cloneAnnotation.deepClone()) {
            for (Object collectionItem : collection) {
                makeObjectTransient(collectionItem);
            }
        }
    }

    private static void makeMapTransient(Map<Object, Object> map,
            CloneThis cloneAnnotation) throws Exception {
        if (cloneAnnotation.deepClone()) {
            for (Object value : map.values()) {
                makeObjectTransient(value);
            }
        }
    }

    private static void makeArrayFieldTransient(Field field, Object owner,
            CloneThis cloneAnnotation) throws Exception {
        if (cloneAnnotation.deepClone()) {
            Object[] value = (Object[]) field.get(owner);
            if (value != null) {
                for (Object arrayItem : value) {
                    makeObjectTransient(arrayItem);
                }
            }
        }
    }

    @Override
    public int hashCode() {
        return (int) (identifier ^ (identifier >>> 32));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final EntityBase other = (EntityBase) obj;
        return identifier != null && identifier.equals(other.identifier);
    }

    @Override
    public Object clone() {
        try {
            return CloneUtil.deepClone((EntityBase) super.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
