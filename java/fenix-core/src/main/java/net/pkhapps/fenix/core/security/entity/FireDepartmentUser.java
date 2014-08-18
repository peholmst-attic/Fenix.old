package net.pkhapps.fenix.core.security.entity;

import net.pkhapps.fenix.core.entity.FireDepartment;

import javax.persistence.*;

/**
 * Entity representing a user associated with a {@link net.pkhapps.fenix.core.entity.FireDepartment}.
 */
@Entity
@Table(name = "fire_department_users")
@DiscriminatorValue("fire_department")
public class FireDepartmentUser extends SystemUser {

    @ManyToOne(optional = false)
    @JoinColumn(name = "fire_department_id", nullable = false)
    private FireDepartment fireDepartment;

    protected FireDepartmentUser() {
    }

    /**
     * Returns the fire department that the user belongs to.
     */
    public FireDepartment getFireDepartment() {
        return fireDepartment;
    }

    protected void setFireDepartment(FireDepartment fireDepartment) {
        this.fireDepartment = fireDepartment;
    }

    public static class Builder extends AbstractBuilder<FireDepartmentUser, Builder> {

        public Builder() {
        }

        public Builder(FireDepartmentUser original) {
            super(original);
            setFireDepartment(original.getFireDepartment());
        }

        public FireDepartment getFireDepartment() {
            return getInstance().getFireDepartment();
        }

        public Builder setFireDepartment(FireDepartment fireDepartment) {
            getInstance().setFireDepartment(fireDepartment);
            return myself();
        }

        @Override
        protected FireDepartmentUser newInstance() {
            return new FireDepartmentUser();
        }
    }
}
