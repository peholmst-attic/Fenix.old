package net.pkhapps.fenix.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity representing a fire department.
 *
 * @see net.pkhapps.fenix.core.entity.FireDepartmentUser
 */
@Entity
@Table(name = "fire_departments")
public class FireDepartment extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name = "";

    @Column(nullable = false)
    private boolean enabled = true;

    protected FireDepartment() {
    }

    /**
     * Return the unique name of the fire department.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns whether the fire department is enabled (i.e. users belonging to the fire department are allowed to use the system).
     */
    public boolean isEnabled() {
        return enabled;
    }
}
