package net.pkhapps.fenix.core.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Base class for entities that are always associated with exactly one {@link net.pkhapps.fenix.core.entity.FireDepartment}.
 */
@MappedSuperclass
public abstract class AbstractFireDepartmentSpecificEntity extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "fire_department_id", nullable = false)
    private FireDepartment fireDepartment;

    protected AbstractFireDepartmentSpecificEntity() {
    }

    public FireDepartment getFireDepartment() {
        return fireDepartment;
    }

    public void setFireDepartment(FireDepartment fireDepartment) {
        this.fireDepartment = fireDepartment;
    }
}
