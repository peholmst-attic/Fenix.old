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

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.github.peholmst.fenix.common.util.CloneUtil;

/**
 * This is a base class for all entities. It includes an identifier and a
 * version number for optimistic locking. Equality and hash code is based on the
 * identifier alone and therefore it is assigned already when the entity
 * instance is created. Please see the factory methods (
 * {@link #createEntity(Class)} and
 * {@link #createEntityWithExistingId(Class, long)}) for more information.
 * <p>
 * Cloning is handled by {@link CloneUtil#deepClone(Cloneable)}, which means
 * that subclasses can use the {@link CloneThis} annotation if they want to.
 * 
 * @author Petter Holmström
 */
@MappedSuperclass
public abstract class EntityBase implements java.io.Serializable, Cloneable {

	// TODO Test me!
	
	private static final long serialVersionUID = 9158596445082221718L;

	@Id
	private long identifier = 0L;

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

	/**
	 * Gets the identifier of this entity.
	 * 
	 * @return the identifier.
	 */
	public long getIdentifier() {
		return identifier;
	}

	/**
	 * Gets the version number that is used for optimistic locking.
	 * 
	 * @return the optimistic locking version number or <code>null</code> if the
	 *         entity is transient.
	 */
	public Long getOptLockVersion() {
		return optLockVersion;
	}

	/**
	 * Checks if the entity is persistent or transient.
	 * 
	 * @return true if it is persistent, false if it is transient.
	 */
	public boolean isPersistent() {
		return optLockVersion != null;
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
