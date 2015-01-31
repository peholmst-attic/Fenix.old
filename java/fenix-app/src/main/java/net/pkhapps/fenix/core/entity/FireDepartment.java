package net.pkhapps.fenix.core.entity;

import com.google.common.base.Strings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity representing a fire department.
 */
@Entity
@Table(name = "fire_departments")
public class FireDepartment extends AbstractEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name = "";

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    public FireDepartment() {
    }

    /**
     * Return the unique name of the fire department.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Strings.nullToEmpty(name);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
