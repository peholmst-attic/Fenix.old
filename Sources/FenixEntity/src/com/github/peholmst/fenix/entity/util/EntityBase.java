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

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.github.peholmst.fenix.common.util.CloneThis;
import com.github.peholmst.fenix.common.util.CloneUtil;
import com.github.peholmst.fenix.common.util.FieldUtil;

/**
 * This is a base class for all entities. It includes an identifier and a
 * version number for optimistic locking. Equality and hash code is based on the
 * identifier alone and therefore it is assigned already when the entity
 * instance is created. Please see the factory methods for more information.
 * <p>
 * Cloning is handled by {@link CloneUtil#deepClone(Cloneable)}, which means
 * that subclasses can use the {@link CloneThis} annotation if they want to.
 * Please note, that the <code>clone()</code> method will make an exact clone of
 * the entity graph, including the identifiers. If you want a copy of the entity
 * graph but with different identifiers, you should use
 * {@link #createFromExistingEntity(EntityBase)}.
 * 
 * @see #createEntity(Class)
 * @see #createEntityWithExistingId(Class, long)
 * @see #createFromExistingEntity(EntityBase)
 * 
 * @author Petter Holmström
 */
@MappedSuperclass
public abstract class EntityBase implements java.io.Serializable, Cloneable {

    private static final long serialVersionUID = 9158596445082221718L;

    @Id
    private Long identifier;

    public static final String PROP_IDENTIFIER = "identifier";

    @Version
    private Long optLockVersion;

    public static final String PROP_OPT_LOCK_VERSION = "optLockVersion";

    /**
     * Protected constructor to prevent clients from using it to create new
     * entity instances. They should use the factory methods instead.
     * 
     * @see #createEntity(Class)
     * @see #createEntityWithExistingId(Class, long)
     */
    protected EntityBase() {
    }

    /**
     * Creates a new instance of the specified entity class and assigns an
     * identifier to it using {@link IdGenerator}.
     * 
     * @param <T>
     *            the type of entity to create.
     * @param entityClass
     *            the entity class.
     * @return a new entity instance.
     */
    public static <T extends EntityBase> T createEntity(Class<T> entityClass) {
        return createEntityWithExistingId(entityClass,
                IdGenerator.getNextValue());
    }

    /**
     * Creates a new transient entity instance that is a copy of the specified
     * entity. This means that all entity identifiers have been regenerated and
     * the optimistic locking versions set to <code>null</code>. All fields
     * annotated with {@link CloneThis} have also been made transient
     * recursively. Maps, arrays and Collections are made transient if
     * {@link CloneThis#deepClone()} is true.
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

    private void makeTransient() throws Exception {
        makeObjectTransient(this);
    }

    private static void makeObjectTransient(final Object object)
            throws Exception {
        if (object == null) {
            return;
        }
        if (object instanceof EntityBase) {
            ((EntityBase) object).identifier = IdGenerator.getNextValue();
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

    /**
     * Creates a new instance of the specified entity class with the specified
     * identifier.
     * 
     * @param <T>
     *            the type of the entity to create.
     * @param entityClass
     *            the entity class.
     * @param entityId
     *            the entity identifier.
     * @return a new entity instance with the given identifier.
     */
    public static <T extends EntityBase> T createEntityWithExistingId(
            Class<T> entityClass, long entityId) {
        assert entityClass != null : "entityClass must not be null";
        try {
            T entity = entityClass.newInstance();
            entity.identifier = entityId;
            return entity;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Could not create an instance of class " + entityClass, e);
        }
    }

    public Long getIdentifier() {
        return identifier;
    }

    public Long getOptLockVersion() {
        return optLockVersion;
    }

    /**
     * Checks if the entity is persistent or transient.
     */
    public boolean isPersistent() {
        return getOptLockVersion() != null;
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
        EntityBase other = (EntityBase) obj;
        if (identifier != other.identifier)
            return false;
        return true;
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
