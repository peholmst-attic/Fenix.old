package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.Message;

import java.util.Optional;

/**
 * Event published when the sending of a {@link net.pkhapps.fenix.communication.entity.Message} succeeds
 * using a specific {@link net.pkhapps.fenix.communication.entity.CommunicationMethod}. Thus,
 * {@link #getCommunicationMethod()} will never return an empty optional for this class.
 *
 * @see net.pkhapps.fenix.communication.entity.MessageState#SENT
 */
public class MessageSentEvent extends AbstractMessageEvent {

    public MessageSentEvent(Object source, Message message, CommunicationMethod communicationMethod) {
        super(source, message, Optional.of(communicationMethod));
    }
}
