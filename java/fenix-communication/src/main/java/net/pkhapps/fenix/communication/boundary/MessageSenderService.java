package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.entity.ArchivedMessage;
import net.pkhapps.fenix.communication.entity.Recipient;

import java.util.Collection;

/**
 *
 */
public interface MessageSenderService {

    /**
     * Sends the specified message asynchronously. The result of this operation will be published on the
     * Spring event bus.
     *
     * @param message    the message to send.
     * @param recipients the recipients of the message.
     * @return the archived message.
     * @see net.pkhapps.fenix.communication.events.MessageSentEvent
     * @see net.pkhapps.fenix.communication.events.MessageFailedEvent
     */
    ArchivedMessage sendMessage(String message, Collection<Recipient> recipients);

}
