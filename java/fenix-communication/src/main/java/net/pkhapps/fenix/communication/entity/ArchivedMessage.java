package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a message.
 */
@Entity
@Table(name = "messages")
public class ArchivedMessage extends AbstractFireDepartmentSpecificEntity {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "message_recipients",
            joinColumns = @JoinColumn(name = "message_id", nullable = false))
    private Set<ArchivedMessageRecipient> recipients = new HashSet<>();

    @Column(name = "message_text", nullable = false)
    @Lob
    private String messageText = "";

    public ArchivedMessage() {
    }

    public Collection<ArchivedMessageRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<ArchivedMessageRecipient> recipients) {
        this.recipients = Objects.requireNonNull(recipients);
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = Strings.nullToEmpty(messageText);
    }
}
