package net.pkhapps.fenix.communication.entity;

import net.pkhapps.fenix.core.entity.AbstractEntity;
import org.springframework.util.Assert;

import javax.persistence.*;
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

    protected MessageCommunicationMethodState() {
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

    protected void setCommunicationMethod(CommunicationMethod communicationMethod) {
        this.communicationMethod = communicationMethod;
    }

    public MessageState getState() {
        return state;
    }

    protected void setState(MessageState state) {
        this.state = state;
    }

    public static class Builder extends AbstractEntity.Builder<MessageCommunicationMethodState, Builder> {

        public Builder() {
        }

        public Builder(MessageCommunicationMethodState original) {
            super(original);
        }

        public Message getMessage() {
            return getInstance().getMessage();
        }

        public Builder setMessage(Message message) {
            getInstance().setMessage(Objects.requireNonNull(message));
            return myself();
        }

        public CommunicationMethod getCommunicationMethod() {
            return getInstance().getCommunicationMethod();
        }

        public Builder setCommunicationMethod(CommunicationMethod communicationMethod) {
            getInstance().setCommunicationMethod(Objects.requireNonNull(communicationMethod));
            return myself();
        }

        public MessageState getState() {
            return getInstance().getState();
        }

        public Builder setState(MessageState state) {
            getInstance().setState(Objects.requireNonNull(state));
            return myself();
        }

        @Override
        protected void validateBeforeBuild() {
            Assert.notNull(getCommunicationMethod(), "communicationMethod must be specified");
            Assert.notNull(getMessage(), "message must be specified");
        }

        @Override
        protected MessageCommunicationMethodState newInstance() {
            return new MessageCommunicationMethodState();
        }
    }
}
