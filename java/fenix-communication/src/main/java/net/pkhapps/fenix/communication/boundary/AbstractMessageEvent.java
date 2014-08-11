package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.Message;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by peholmst on 2014-08-08.
 */
public abstract class AbstractMessageEvent extends ApplicationEvent {

    private final Message message;
    private final Optional<CommunicationMethod> communicationMethod;

    public AbstractMessageEvent(Object source, Message message, Optional<CommunicationMethod> communicationMethod) {
        super(source);
        this.message = Objects.requireNonNull(message);
        this.communicationMethod = Objects.requireNonNull(communicationMethod);
    }

    public Message getMessage() {
        return message;
    }

    public Optional<CommunicationMethod> getCommunicationMethod() {
        return communicationMethod;
    }
}
