package net.pkhapps.fenix.communication.events;

import net.pkhapps.fenix.communication.entity.ArchivedMessage;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;

import java.util.Optional;

/**
 * Event published when the sending of a {@link net.pkhapps.fenix.communication.entity.ArchivedMessage} fails
 * using a specific {@link net.pkhapps.fenix.communication.entity.CommunicationMethod}. Thus,
 * {@link #getCommunicationMethod()} will never return an empty optional for this class.
 *
 * @see net.pkhapps.fenix.communication.entity.MessageState#FAILED
 */
public class MessageFailedEvent extends AbstractMessageEvent {

    public MessageFailedEvent(Object source, ArchivedMessage message, CommunicationMethod communicationMethod) {
        super(source, message, Optional.of(communicationMethod));
    }
}
