package net.pkhapps.fenix.communication.events;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.MessageId;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;
import java.util.Optional;

public abstract class AbstractMessageEvent extends ApplicationEvent {

    private final MessageId messageId;
    private final Optional<CommunicationMethod> communicationMethod;

    public AbstractMessageEvent(Object source, MessageId messageId, Optional<CommunicationMethod> communicationMethod) {
        super(source);
        this.messageId = Objects.requireNonNull(messageId);
        this.communicationMethod = Objects.requireNonNull(communicationMethod);
    }

    public MessageId getMessageId() {
        return messageId;
    }

    public Optional<CommunicationMethod> getCommunicationMethod() {
        return communicationMethod;
    }
}
