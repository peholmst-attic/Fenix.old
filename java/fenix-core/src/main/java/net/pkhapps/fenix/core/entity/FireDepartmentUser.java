package net.pkhapps.fenix.core.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a user associated with a {@link net.pkhapps.fenix.core.entity.FireDepartment}.
 */
@Entity
@Table(name = "fire_department_users")
public class FireDepartmentUser extends AbstractEntity {

    @Column(name = "uid", nullable = false, unique = true)
    // So far, to keep things simple, we only allow one fire department per user
    private String uid = "";

    @ManyToOne(optional = false)
    @JoinColumn(name = "fire_department_id", nullable = false)
    private FireDepartment fireDepartment;

    @ElementCollection
    @Column(name = "authority", nullable = false)
    @CollectionTable(name = "fire_department_user_authorities",
            joinColumns = @JoinColumn(name = "fire_department_user_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"authority", "fire_department_user_id"}))
    private Set<String> authorities = new HashSet<String>();

    protected FireDepartmentUser() {
    }

    /**
     * Returns the unique user ID. This is typically a username, although this is not a requirement. The only requirement
     * is that once a user ID has been assigned to a user, it must never ever change.
     */
    public String getUid() {
        return uid;
    }

    /**
     * Returns the fire department that the user belongs to.
     */
    public FireDepartment getFireDepartment() {
        return fireDepartment;
    }

    /**
     * Checks if the user has the specified authority in this particular {@link net.pkhapps.fenix.core.entity.FireDepartment}.
     */
    public boolean hasAuthority(String authority) {
        return authorities.contains(authority);
    }
}
