package net.pkhapps.fenix.communication.events;

import net.pkhapps.fenix.communication.entity.ArchivedMessage;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by peholmst on 2014-08-08.
 */
public abstract class AbstractMessageEvent extends ApplicationEvent {

    private final ArchivedMessage message;
    private final Optional<CommunicationMethod> communicationMethod;

    public AbstractMessageEvent(Object source, ArchivedMessage message, Optional<CommunicationMethod> communicationMethod) {
        super(source);
        this.message = Objects.requireNonNull(message);
        this.communicationMethod = Objects.requireNonNull(communicationMethod);
    }

    public ArchivedMessage getMessage() {
        return message;
    }

    public Optional<CommunicationMethod> getCommunicationMethod() {
        return communicationMethod;
    }
}
