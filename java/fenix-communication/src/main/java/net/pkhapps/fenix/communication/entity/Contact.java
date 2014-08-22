package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a single contact.
 */
@Entity
@Table(name = "contacts")
public class Contact extends AbstractFireDepartmentSpecificEntity {

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
    private Set<CommunicationMethod> communicationMethods = Collections.emptySet();

    protected Contact() {
    }

    public String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSingleName() {
        return singleName;
    }

    protected void setSingleName(String singleName) {
        this.singleName = singleName;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    protected void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    public Collection<CommunicationMethod> getCommunicationMethods() {
        return Collections.unmodifiableSet(communicationMethods);
    }

    protected void setCommunicationMethods(Set<CommunicationMethod> communicationMethods) {
        this.communicationMethods = communicationMethods;
    }

    public String getDisplayName() {
        if (singleName.isEmpty()) {
            return String.format("%s, %s", lastName, firstName);
        } else {
            return singleName;
        }
    }

    public static class Builder extends AbstractFireDepartmentSpecificEntity.Builder<Contact, Builder> {

        public Builder() {
        }

        public Builder(Contact original) {
            super(original);
            setCommunicationMethods(original.getCommunicationMethods());
            setEmail(original.getEmail());
            setCellPhoneNumber(original.getCellPhoneNumber());
            setSingleName(original.getSingleName());
            setLastName(original.getLastName());
            setFirstName(original.getFirstName());
        }

        public Collection<CommunicationMethod> getCommunicationMethods() {
            return getInstance().getCommunicationMethods();
        }

        public Builder setCommunicationMethods(Collection<CommunicationMethod> communicationMethods) {
            if (communicationMethods == null) {
                communicationMethods = Collections.emptySet();
            }
            getInstance().setCommunicationMethods(new HashSet<>(communicationMethods));
            return myself();
        }

        public String getEmail() {
            return getInstance().getEmail();
        }

        public Builder setEmail(String email) {
            getInstance().setEmail(Strings.nullToEmpty(email));
            return myself();
        }

        public String getCellPhoneNumber() {
            return getInstance().getCellPhoneNumber();
        }

        public Builder setCellPhoneNumber(String cellPhoneNumber) {
            getInstance().setCellPhoneNumber(Strings.nullToEmpty(cellPhoneNumber));
            return myself();
        }

        public String getSingleName() {
            return getInstance().getSingleName();
        }

        public Builder setSingleName(String singleName) {
            getInstance().setSingleName(Strings.nullToEmpty(singleName));
            return myself();
        }

        public String getLastName() {
            return getInstance().getLastName();
        }

        public Builder setLastName(String lastName) {
            getInstance().setLastName(Strings.nullToEmpty(lastName));
            return myself();
        }

        public String getFirstName() {
            return getInstance().getFirstName();
        }

        public Builder setFirstName(String firstName) {
            getInstance().setFirstName(Strings.nullToEmpty(firstName));
            return myself();
        }

        @Override
        protected Contact newInstance() {
            return new Contact();
        }
    }
}
