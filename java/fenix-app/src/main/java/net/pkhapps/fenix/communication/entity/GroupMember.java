package net.pkhapps.fenix.communication.entity;

import net.pkhapps.fenix.core.entity.AbstractEntity;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Association class between {@link Contact} and {@link net.pkhapps.fenix.communication.entity.Group}. The individual communication
 * methods of the referenced contact can be overridden.
 */
@Entity
@Table(name = "contact_group_members")
public class GroupMember extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    @CollectionTable(name = "contact_group_member_communication_methods",
            joinColumns = @JoinColumn(name = "group_member_id", nullable = false))
    private Set<CommunicationMethod> customCommunicationMethods = new HashSet<>();

    protected GroupMember() {
    }

    public GroupMember(Contact contact) {
        this.contact = Objects.requireNonNull(contact);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = Objects.requireNonNull(contact);
    }

    public Collection<CommunicationMethod> getCommunicationMethods() {
        Assert.notNull(contact, "contact should not be null when this method is invoked");
        if (customCommunicationMethods.isEmpty()) {
            return new HashSet<>(contact.getCommunicationMethods());
        } else {
            return new HashSet<>(customCommunicationMethods);
        }
    }

    public Set<CommunicationMethod> getCustomCommunicationMethods() {
        return customCommunicationMethods;
    }

    public void setCustomCommunicationMethods(Set<CommunicationMethod> customCommunicationMethods) {
        this.customCommunicationMethods = Objects.requireNonNull(customCommunicationMethods);
    }
}
