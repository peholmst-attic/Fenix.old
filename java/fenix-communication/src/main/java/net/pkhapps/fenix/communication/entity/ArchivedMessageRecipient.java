package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * TODO Document me!
 */
@Embeddable
public class ArchivedMessageRecipient implements Serializable {

    public static final String PROP_NAME = "name";
    public static final String PROP_DETAILS = "details";

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "details", nullable = false)
    private String details = "";

    public ArchivedMessageRecipient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Strings.nullToEmpty(name);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = Strings.nullToEmpty(details);
    }
}
