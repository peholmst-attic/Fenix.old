package net.pkhapps.fenix.communication.entity;

import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a group of {@link net.pkhapps.fenix.communication.entity.Contact contacts}.
 */
@Entity
@Table(name = "contact_groups")
public class Group extends AbstractFireDepartmentSpecificEntity {

    // TODO Add validation constraints

    @Column(name = "group_name", nullable = false)
    private String name = "";

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "group_id")
    private Set<GroupMember> members = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public Set<GroupMember> getMembers() {
        return members;
    }

    public void setMembers(Set<GroupMember> members) {
        this.members = Objects.requireNonNull(members);
    }

}
