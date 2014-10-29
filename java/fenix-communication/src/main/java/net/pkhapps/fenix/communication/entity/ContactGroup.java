package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Entity representing a group of {@link net.pkhapps.fenix.communication.entity.Contact}s.
 */
@Entity
@Table(name = "contact_groups")
public class ContactGroup extends AbstractFireDepartmentSpecificEntity implements SmsRecipient, EmailRecipient {

    public static final String PROP_NAME = "name";
    public static final String PROP_MEMBERS = "members";

    @Column(name = "group_name", nullable = false)
    private String name = "";

    @ManyToMany(fetch = FetchType.EAGER)
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

    @Override
    public Collection<String> getEmailAddresses() {
        return members.stream().flatMap(contact -> contact.getEmailAddresses().stream()).collect(Collectors.toSet());
    }

    @Override
    public Collection<String> getPhoneNumbers() {
        return members.stream().flatMap(contact -> contact.getPhoneNumbers().stream()).collect(Collectors.toSet());
    }

    @Override
    public String getRecipientName() {
        return getName();
    }

    @Override
    public boolean supports(Class<? extends Recipient> recipientClass) {
        if (recipientClass == EmailRecipient.class) {
            return getEmailAddresses().size() > 0;
        } else if (recipientClass == SmsRecipient.class) {
            return getPhoneNumbers().size() > 0;
        } else {
            return false;
        }
    }
}
