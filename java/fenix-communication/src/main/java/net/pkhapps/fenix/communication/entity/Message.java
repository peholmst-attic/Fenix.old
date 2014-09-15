package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entity representing a message.
 */
@Entity
@Table(name = "messages")
public class Message extends AbstractFireDepartmentSpecificEntity {

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    @CollectionTable(name = "message_communication_methods",
            joinColumns = @JoinColumn(name = "message_id", nullable = false))
    private Set<CommunicationMethod> sendAs = Collections.emptySet();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id", nullable = false)
    private Set<MessageRecipient> recipients = new HashSet<>();

//    @Column(name = "sent_ts", nullable = true)
//    private Timestamp sent;

    @Column(name = "message_text", nullable = false)
    @Lob
    private String messageText = "";

    public Message() {
    }

    public Collection<CommunicationMethod> getSendAs() {
        return sendAs;
    }

    public void setSendAs(Set<CommunicationMethod> sendAs) {
        this.sendAs = Objects.requireNonNull(sendAs);
    }

    public Collection<MessageRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(Set<MessageRecipient> recipients) {
        this.recipients = Objects.requireNonNull(recipients);
    }
/*
    public Optional<Instant> getSent() {
        if (sent == null) {
            return Optional.empty();
        } else {
            return Optional.of(sent.toInstant());
        }
    }

    public void setSent(Optional<Instant> sent) {
        this.sent = sent.map(Timestamp::from).orElse(null);
    }*/

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = Strings.nullToEmpty(messageText);
    }
}
