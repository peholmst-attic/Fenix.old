package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.Message;
import net.pkhapps.fenix.communication.entity.MessageState;
import net.pkhapps.fenix.communication.events.MessageFailedEvent;
import net.pkhapps.fenix.communication.events.MessageSentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Base class for controls that send a {@link net.pkhapps.fenix.communication.entity.Message} using a
 * {@link net.pkhapps.fenix.communication.entity.CommunicationMethod}.
 */
public abstract class Sender {

    private final static Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    private final ApplicationContext applicationContext;
    private final MessageStateHelper messageStateHelper;
    private final TransactionTemplate txTemplate;

    protected Sender(ApplicationContext applicationContext, MessageStateHelper messageStateHelper, PlatformTransactionManager platformTransactionManager) {
        this.applicationContext = applicationContext;
        this.messageStateHelper = messageStateHelper;
        txTemplate = new TransactionTemplate(platformTransactionManager,
                new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW));
    }

    /**
     * Attempts to asynchronously send the specified message. Sender may assume the message
     * is to be sent using a supported communication method. The result of the operation is published
     * on the application context event bus. If this sender does not know how to send the specified message,
     * nothing happens.
     *
     * @see net.pkhapps.fenix.communication.events.MessageSentEvent
     * @see net.pkhapps.fenix.communication.events.MessageFailedEvent
     */
    @Async
    public void send(Message message) {
        if (message.getSendAs().contains(getCommunicationMethod())) {
            doSend(message);
        }
    }

    protected abstract void doSend(Message message);

    protected void failed(Message message) {
        LOGGER.debug("Sending of message {} failed", message);
        updateState(message, MessageState.FAILED);
        applicationContext.publishEvent(new MessageFailedEvent(this, message, getCommunicationMethod()));
    }

    protected void succeeded(Message message) {
        LOGGER.debug("Sending of message {} succeeded", message);
        updateState(message, MessageState.SENT);
        applicationContext.publishEvent(new MessageSentEvent(this, message, getCommunicationMethod()));
    }

    protected abstract CommunicationMethod getCommunicationMethod();

    protected void updateState(Message message, MessageState newState) {
        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                messageStateHelper.updateState(message, getCommunicationMethod(), newState);
            }
        });
    }
}
