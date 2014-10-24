package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.MessageId;
import net.pkhapps.fenix.communication.entity.Recipient;

import java.util.Collection;

/**
 * TODO Document me!
 */
public interface MessageSenderService {

    /**
     * Sends the specified message asynchronously. The result of this operation will be published on the
     * Spring event bus.
     *
     * @param message    the message to send.
     * @param recipients the recipients of the message.
     * @param sendAs     the communication methods to use when sending the message.
     * @return the message ID.
     * @see net.pkhapps.fenix.communication.events.MessageSentEvent
     * @see net.pkhapps.fenix.communication.events.MessageFailedEvent
     */
    MessageId sendMessage(String message, Collection<Recipient> recipients, Collection<CommunicationMethod> sendAs);

}
