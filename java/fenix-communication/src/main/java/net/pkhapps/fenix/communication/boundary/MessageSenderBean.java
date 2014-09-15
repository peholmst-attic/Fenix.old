package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.control.MessageStateHelper;
import net.pkhapps.fenix.communication.control.Sender;
import net.pkhapps.fenix.communication.entity.Message;
import net.pkhapps.fenix.communication.entity.MessageRepository;
import net.pkhapps.fenix.communication.entity.MessageState;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.validation.Validator;
import java.util.Collection;

/**
 * Default implementation of {@link net.pkhapps.fenix.communication.boundary.MessageSender} that delegates
 * the actual sending to an implementation of {@link net.pkhapps.fenix.communication.control.Sender}.
 */
@Service
class MessageSenderBean implements MessageSender {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageSenderBean.class);

    private final ApplicationContext applicationContext;
    private final Validator validator;
    private final MessageRepository messageRepository;
    private final MessageStateHelper messageStateHelper;
    private final TransactionTemplate txTemplate;

    @Autowired
    MessageSenderBean(ApplicationContext applicationContext, Validator validator, MessageRepository messageRepository, MessageStateHelper messageStateHelper, PlatformTransactionManager platformTransactionManager) {
        this.applicationContext = applicationContext;
        this.validator = validator;
        this.messageRepository = messageRepository;
        this.messageStateHelper = messageStateHelper;
        txTemplate = new TransactionTemplate(platformTransactionManager,
                new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW));
    }

    @Override
    public Message sendMessage(Message message) throws ValidationFailedException {
        if (!message.isNew()) {
            LOGGER.debug("Message {} is not new, making a copy");
            message = (Message) message.copy();
            message.setNew();
        }
        LOGGER.debug("Validating message {}", message);
        ValidationFailedException.throwIfNotEmpty(validator.validate(message));
        LOGGER.info("Archiving message {}", message);
        final Message messageToSend = archiveMessage(message);
        LOGGER.info("Attempting to send message {}", messageToSend);
        doSend(messageToSend);
        return messageToSend;
    }

    private Message archiveMessage(Message message) {
        return txTemplate.execute(tx -> {
            final Message messageToSend = messageRepository.saveAndFlush(message);
            messageToSend.getSendAs().forEach(communicationMethod -> messageStateHelper.updateState(messageToSend, communicationMethod, MessageState.SENDING));
            return messageToSend;
        });
    }

    private void doSend(Message message) {
        final Collection<Sender> senders = applicationContext.getBeansOfType(Sender.class).values();
        LOGGER.debug("Found senders {}", senders);
        senders.forEach(sender -> sender.send(message));
    }
}
