/*
 * Fenix
 * Copyright (C) 2014 Petter Holmström
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.pkhapps.fenix.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base class for entities.
 *
 * @author Petter Holmström
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Version
    @Column(name = "optlockver", nullable = false)
    private Long optLockVersion;

    /**
     * Returns the ID of this entity, or {@code null} if the entity has not been
     * persisted yet.
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the optimistic locking version of this entity, or {@code null} if
     * the entity has not been persisted yet.
     */
    public Long getOptLockVersion() {
        return optLockVersion;
    }

    /**
     * Returns whether this entity has been persisted or not.
     */
    public boolean isPersistent() {
        return id != null;
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return System.identityHashCode(this);
        } else {
            return id.hashCode();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return obj == this || id != null && id.equals(((AbstractEntity) obj).id);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Same as {@link #clone() }, but without the checked exception.
     *
     * @return a clone of the object.
     * @throws RuntimeException if cloning is not supported.
     */
    public Object uncheckedClone() {
        try {
            return clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
