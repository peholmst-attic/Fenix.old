package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity representing a single recipient of a {@link Message}.
 */
@Entity
@Table(name = "message_recipients")
public class MessageRecipient extends AbstractEntity {

    public static final String PROP_NAME = "name";
    public static final String PROP_CELL_PHONE_NUMBER = "cellPhoneNumber";
    public static final String PROP_EMAIL = "email";

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "cell_phone", nullable = false)
    private String cellPhoneNumber = "";

    @Column(name = "email", nullable = false)
    private String email = "";

    public MessageRecipient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
