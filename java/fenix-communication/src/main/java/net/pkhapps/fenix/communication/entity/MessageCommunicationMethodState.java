package net.pkhapps.fenix.communication.entity;

import net.pkhapps.fenix.core.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

/**
 * Entity that combines a {@link net.pkhapps.fenix.communication.entity.CommunicationMethod} and a {@link net.pkhapps.fenix.communication.entity.MessageState}
 * for a particular {@link net.pkhapps.fenix.communication.entity.Message}.
 */
@Entity
@Table(name = "message_states", uniqueConstraints = @UniqueConstraint(columnNames = {"message_id", "communication_method"}))
public class MessageCommunicationMethodState extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    @Enumerated(EnumType.STRING)
    @Column(name = "communication_method", nullable = false)
    private CommunicationMethod communicationMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private MessageState state = MessageState.UNSENT;

    @Column(name = "ts", nullable = false)
    private Timestamp timestamp;

    protected MessageCommunicationMethodState() {
    }

    public MessageCommunicationMethodState(Message message, CommunicationMethod communicationMethod, MessageState state) {
        this.message = Objects.requireNonNull(message);
        this.communicationMethod = Objects.requireNonNull(communicationMethod);
        this.state = Objects.requireNonNull(state);
        this.timestamp = Timestamp.from(Instant.now());
    }

    public Message getMessage() {
        return message;
    }

    protected void setMessage(Message message) {
        this.message = message;
    }

    public CommunicationMethod getCommunicationMethod() {
        return communicationMethod;
    }

    public void setCommunicationMethod(CommunicationMethod communicationMethod) {
        this.communicationMethod = Objects.requireNonNull(communicationMethod);
    }

    public MessageState getState() {
        return state;
    }

    protected void setState(MessageState state) {
        this.state = Objects.requireNonNull(state);
    }

    public Instant getTimestamp() {
        return timestamp.toInstant();
    }

    protected void setTimestamp(Instant timestamp) {
        this.timestamp = Timestamp.from(Objects.requireNonNull(timestamp));
    }
}
