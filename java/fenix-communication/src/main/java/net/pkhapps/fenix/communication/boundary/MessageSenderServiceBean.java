package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.control.Sender;
import net.pkhapps.fenix.communication.entity.*;
import net.pkhapps.fenix.core.security.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Default implementation of {@link MessageSenderService} that delegates
 * the actual sending to an implementation of {@link net.pkhapps.fenix.communication.control.Sender}.
 */
@Service
@Deprecated
class MessageSenderServiceBean implements MessageSenderService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageSenderServiceBean.class);

    private final ApplicationContext applicationContext;
    private final SessionInfo sessionInfo;
    private final MessageReceiptRepository messageReceiptRepository;

    @Autowired
    MessageSenderServiceBean(ApplicationContext applicationContext, SessionInfo sessionInfo, MessageReceiptRepository messageReceiptRepository) {
        this.applicationContext = applicationContext;
        this.sessionInfo = sessionInfo;
        this.messageReceiptRepository = messageReceiptRepository;
    }

    @Override
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @SuppressWarnings("unchecked")
    public Future<MessageReceipt> sendMessage(String message, Collection<Recipient> recipients, Collection<CommunicationMethod> sendAs) {
        Assert.isTrue(message.length() > 0, "Message must not be empty");
        Assert.notEmpty(recipients, "Recipients must be specified");
        Assert.notEmpty(sendAs, "At least one communication method must be specified");

        MessageReceipt receipt = new MessageReceipt();
        receipt.setFireDepartment(sessionInfo.getCurrentFireDepartment());
        receipt.setMessageExcerpt(message);

        final Set<Future<MessageReceiptCommunicationMethodStatus>> status = new HashSet<>();
        applicationContext.getBeansOfType(Sender.class)
                .values()
                .stream()
                .filter(sender -> sendAs.contains(sender.getCommunicationMethod()))
                .forEach(sender -> status.add(sender.send(message, recipients)));

        final Iterator<Future<MessageReceiptCommunicationMethodStatus>> it = status.iterator();
        while (it.hasNext()) {
            try {
                receipt.getStatus().add(it.next().get());
                it.remove();
            } catch (InterruptedException | ExecutionException ex) {
                LOGGER.error("An error occurred while sending the message", ex);
                throw new RuntimeException("An unexpected error occurred while sending the message", ex);
            }
        }

        receipt.setTimestamp(Instant.now());
        receipt = messageReceiptRepository.saveAndFlush(receipt);
        return new AsyncResult<>(receipt);
    }
}
