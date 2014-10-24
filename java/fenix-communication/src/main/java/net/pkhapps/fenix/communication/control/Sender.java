package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.MessageId;
import net.pkhapps.fenix.communication.entity.Recipient;
import net.pkhapps.fenix.communication.events.MessageFailedEvent;
import net.pkhapps.fenix.communication.events.MessageSentEvent;
import net.pkhapps.fenix.core.security.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Base class for controls that send a message using a
 * {@link net.pkhapps.fenix.communication.entity.CommunicationMethod}.
 */
public abstract class Sender<R extends Recipient> {

    private final static Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    private final ApplicationContext applicationContext;

    private final SessionInfo sessionInfo;

    protected Sender(ApplicationContext applicationContext, SessionInfo sessionInfo) {
        this.applicationContext = applicationContext;
        this.sessionInfo = sessionInfo;
    }

    /**
     * Attempts to asynchronously send the specified message to the recipients that are supported by this
     * particular sender. The result of the operation is published
     * on the application context event bus. If this sender does not know how to send the specified message,
     * nothing happens.
     *
     * @param message    the message text.
     * @param recipients the recipients of the message.
     * @param messageId  the ID of the message to use when publishing events.
     * @see net.pkhapps.fenix.communication.events.MessageSentEvent
     * @see net.pkhapps.fenix.communication.events.MessageFailedEvent
     */
    @Async
    public void send(String message, Collection<Recipient> recipients, MessageId messageId) {
        Set<R> supportedRecipients = recipients.stream().filter(r -> getRecipientClass().isInstance(r)).map(r -> getRecipientClass().cast(r)).collect(Collectors.toSet());
        if (supportedRecipients.size() > 0) {
            try {
                doSend(message, supportedRecipients);
                LOGGER.debug("Publishing MessageSentEvent for message {} and communication method {}", messageId, getCommunicationMethod());
                applicationContext.publishEvent(new MessageSentEvent(this, messageId, getCommunicationMethod()));
            } catch (Exception ex) {
                LOGGER.error("Error sending message", ex);
                LOGGER.debug("Publishing MessageFailedEvent for message {} and communication method {}", messageId, getCommunicationMethod());
                applicationContext.publishEvent(new MessageFailedEvent(this, messageId, getCommunicationMethod()));
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected Class<R> getRecipientClass() {
        return (Class<R>) getCommunicationMethod().getRecipientClass();
    }

    protected SessionInfo getSessionInfo() {
        return sessionInfo;
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
     * @throws java.lang.Exception if the message is not sent successfully.
     */
    protected abstract void doSend(String message, Collection<R> recipients) throws Exception;
}
