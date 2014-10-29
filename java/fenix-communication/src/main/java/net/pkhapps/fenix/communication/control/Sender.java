package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.MessageReceiptCommunicationMethodStatus;
import net.pkhapps.fenix.communication.entity.Recipient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Base class for controls that send a message using a
 * {@link net.pkhapps.fenix.communication.entity.CommunicationMethod}.
 */
public abstract class Sender<R extends Recipient> {

    protected Sender() {
    }

    /**
     * Attempts to asynchronously send the specified message to the recipients that are supported by this
     * particular sender. The result of the operation is returned in a {@link java.util.concurrent.Future}.
     * <p/>
     * This method is expected to <b>never</b> throw any exceptions.
     *
     * @param message    the message text.
     * @param recipients the recipients of the message.
     * @return a {@code Future} containing the status of this operation.
     */
    @Async
    public Future<MessageReceiptCommunicationMethodStatus> send(String message, Collection<Recipient> recipients) {
        Set<R> supportedRecipients = recipients.stream().filter(r -> r.supports(getRecipientClass())).map(r -> getRecipientClass().cast(r)).collect(Collectors.toSet());
        if (supportedRecipients.size() > 0) {
            return new AsyncResult<>(doSend(message, supportedRecipients));
        } else {
            return new AsyncResult<>(new MessageReceiptCommunicationMethodStatus(getCommunicationMethod(), MessageReceiptCommunicationMethodStatus.Code.NO_RECIPIENTS, Optional.empty(), Collections.emptySet()));
        }
    }

    @SuppressWarnings("unchecked")
    protected Class<R> getRecipientClass() {
        return (Class<R>) getCommunicationMethod().getRecipientClass();
    }

    /**
     * Returns the supported communication method.
     */
    public abstract CommunicationMethod getCommunicationMethod();

    /**
     * Sends the message to the specified recipients.
     *
     * @param message    the message text.
     * @param recipients the recipients of the message.
     * @return the status of the operation.
     */
    protected abstract MessageReceiptCommunicationMethodStatus doSend(String message, Collection<R> recipients);
}
