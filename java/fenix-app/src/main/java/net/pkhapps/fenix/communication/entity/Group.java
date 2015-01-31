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

    @Column(name = "group_name", nullable = false)
    private String name = "";

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "contact_group_members", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<Contact> members = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public Set<Contact> getMembers() {
        return members;
    }

    public void setMembers(Set<Contact> members) {
        this.members = Objects.requireNonNull(members);
    }

}
