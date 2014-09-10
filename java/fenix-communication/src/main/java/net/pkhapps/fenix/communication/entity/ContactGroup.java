package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a group of {@link net.pkhapps.fenix.communication.entity.Contact}s.
 */
@Entity
@Table(name = "contact_groups")
public class ContactGroup extends AbstractFireDepartmentSpecificEntity {

    @Column(name = "group_name", nullable = false)
    private String name = "";

    @ManyToMany
    @JoinTable(name = "contact_group_members", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<Contact> members = new HashSet<>();

    public ContactGroup() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Strings.nullToEmpty(name);
    }

    public Set<Contact> getMembers() {
        return members;
    }

    public void setMembers(Set<Contact> members) {
        this.members = Objects.requireNonNull(members);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ContactGroup clone = (ContactGroup) super.clone();
        clone.members = new HashSet<>(members);
        return clone;
    }
}