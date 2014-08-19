package net.pkhapps.fenix.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity representing a fire department.
 */
@Entity
@Table(name = "fire_departments")
public class FireDepartment extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name = "";

    protected FireDepartment() {
    }

    /**
     * Return the unique name of the fire department.
     */
    public String getName() {
        return name;
    }
}
