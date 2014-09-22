package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.entity.Message;
import net.pkhapps.fenix.core.validation.ValidationFailedException;

/**
 * Created by peholmst on 2014-08-08.
 */
public interface MessageSenderService {

    /**
     * Sends the specified message asynchronously. The result of this operation will be published on the
     * Spring event bus.
     *
     * @param message the message to send. If this message is not new, a copy of the message will be sent.
     * @return the archived message in the {@link net.pkhapps.fenix.communication.entity.MessageState#SENDING} state for all communication methods.
     * @throws ValidationFailedException if the initial message could not be validated.
     * @see net.pkhapps.fenix.communication.events.MessageSentEvent
     * @see net.pkhapps.fenix.communication.events.MessageFailedEvent
     */
    Message sendMessage(Message message) throws ValidationFailedException;

}
