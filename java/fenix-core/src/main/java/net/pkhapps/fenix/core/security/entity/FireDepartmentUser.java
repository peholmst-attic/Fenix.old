package net.pkhapps.fenix.core.security.entity;

import com.google.gwt.thirdparty.guava.common.collect.Sets;
import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.security.SystemAuthorities;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Set;

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

    @Override
    public boolean isEnabled() {
        if (fireDepartment != null) {
            return fireDepartment.isEnabled() && super.isEnabled();
        } else {
            return super.isEnabled();
        }
    }

    @Override
    protected Set<? extends GrantedAuthority> getDefaultRoles() {
        return Sets.newHashSet(new SimpleGrantedAuthority(SystemAuthorities.ROLE_FIRE_DEPARTMENT_USER));
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
