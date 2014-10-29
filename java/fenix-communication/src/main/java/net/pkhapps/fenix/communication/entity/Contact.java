package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a single contact.
 */
@Entity
@Table(name = "contacts")
public class Contact extends AbstractFireDepartmentSpecificEntity implements EmailRecipient, SmsRecipient {

    public static final String PROP_FIRST_NAME = "firstName";
    public static final String PROP_LAST_NAME = "lastName";
    public static final String PROP_SINGLE_NAME = "singleName";
    public static final String PROP_CELL_PHONE_NUMBER = "cellPhoneNumber";
    public static final String PROP_EMAIL = "email";
    public static final String PROP_DISPLAY_NAME = "displayName";
    public static final String PROP_COMMUNICATION_METHODS = "communicationMethods";

    @Column(name = "first_name", nullable = false)
    private String firstName = "";

    @Column(name = "last_name", nullable = false)
    private String lastName = "";

    @Column(name = "single_name", nullable = false)
    private String singleName = "";

    @Column(name = "cell_phone", nullable = false)
    private String cellPhoneNumber = "";

    @Column(name = "email", nullable = false)
    private String email = "";

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    @CollectionTable(name = "contact_communication_methods",
            joinColumns = @JoinColumn(name = "contact_id", nullable = false))
    private Set<CommunicationMethod> communicationMethods = new HashSet<>();

    public Contact() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = Strings.nullToEmpty(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = Strings.nullToEmpty(lastName);
    }

    public String getSingleName() {
        return singleName;
    }

    public void setSingleName(String singleName) {
        this.singleName = Strings.nullToEmpty(singleName);
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = Strings.nullToEmpty(cellPhoneNumber);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Strings.nullToEmpty(email);
    }

    public Collection<CommunicationMethod> getCommunicationMethods() {
        return communicationMethods;
    }

    public void setCommunicationMethods(Set<CommunicationMethod> communicationMethods) {
        this.communicationMethods = Objects.requireNonNull(communicationMethods);
    }

    public String getDisplayName() {
        if (singleName.isEmpty()) {
            return String.format("%s, %s", lastName, firstName);
        } else {
            return singleName;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        final Contact clone = (Contact) super.clone();
        clone.communicationMethods = new HashSet<>(communicationMethods);
        return clone;
    }

    @Override
    public Collection<String> getEmailAddresses() {
        if (communicationMethods.contains(CommunicationMethod.EMAIL) && !email.isEmpty()) {
            return Collections.singleton(email);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public Collection<String> getPhoneNumbers() {
        if (communicationMethods.contains(CommunicationMethod.SMS) && !cellPhoneNumber.isEmpty()) {
            return Collections.singleton(cellPhoneNumber);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public String getRecipientName() {
        return getDisplayName();
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
