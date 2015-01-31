package net.pkhapps.fenix.communication.entity;

import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a contact to which messages can be sent in different ways.
 */
@Entity
@Table(name = "contacts")
public class Contact extends AbstractFireDepartmentSpecificEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName = "";

    @Column(name = "last_name", nullable = false)
    private String lastName = "";

    @Column(name = "single_name", nullable = false)
    private String singleName = "";

    @Column(name = "cell_phone", nullable = false)
    private String cellPhone = "";

    @Column(name = "email", nullable = false)
    private String email = "";

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    @CollectionTable(name = "contact_communication_methods",
            joinColumns = @JoinColumn(name = "contact_id", nullable = false))
    private Set<CommunicationMethod> communicationMethods = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName);
    }

    public String getSingleName() {
        return singleName;
    }

    public void setSingleName(String singleName) {
        this.singleName = Objects.requireNonNull(singleName);
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = Objects.requireNonNull(cellPhone);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email);
    }

    public Collection<CommunicationMethod> getCommunicationMethods() {
        return communicationMethods;
    }

    public void setCommunicationMethods(Set<CommunicationMethod> communicationMethods) {
        this.communicationMethods = Objects.requireNonNull(communicationMethods);
    }
}
