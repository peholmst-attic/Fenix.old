package net.pkhapps.fenix.communication.events;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.MessageId;

import java.util.Optional;

/**
 * Event published when the sending of a {message succeeds
 * using a specific {@link net.pkhapps.fenix.communication.entity.CommunicationMethod}. Thus,
 * {@link #getCommunicationMethod()} will never return an empty optional for this class.
 */
public class MessageSentEvent extends AbstractMessageEvent {

    public MessageSentEvent(Object source, MessageId messageId, CommunicationMethod communicationMethod) {
        super(source, messageId, Optional.of(communicationMethod));
    }
}
