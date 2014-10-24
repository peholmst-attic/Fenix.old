package net.pkhapps.fenix.communication.events;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.MessageId;

import java.util.Optional;

/**
 * Event published when the sending of a message fails
 * using a specific {@link net.pkhapps.fenix.communication.entity.CommunicationMethod}. Thus,
 * {@link #getCommunicationMethod()} will never return an empty optional for this class.
 */
public class MessageFailedEvent extends AbstractMessageEvent {

    public MessageFailedEvent(Object source, MessageId messageId, CommunicationMethod communicationMethod) {
        super(source, messageId, Optional.of(communicationMethod));
    }
}
