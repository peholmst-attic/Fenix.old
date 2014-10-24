package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.control.Sender;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.MessageId;
import net.pkhapps.fenix.communication.entity.Recipient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Default implementation of {@link MessageSenderService} that delegates
 * the actual sending to an implementation of {@link net.pkhapps.fenix.communication.control.Sender}.
 */
@Service
class MessageSenderServiceBean implements MessageSenderService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MessageSenderServiceBean.class);

    private final ApplicationContext applicationContext;
    private final AtomicLong nextMessageId = new AtomicLong(0);

    @Autowired
    MessageSenderServiceBean(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public MessageId sendMessage(String message, Collection<Recipient> recipients, Collection<CommunicationMethod> sendAs) {
        MessageId messageId = new MessageId(nextMessageId.getAndIncrement());
        applicationContext.getBeansOfType(Sender.class)
                .values()
                .stream()
                .filter(sender -> sendAs.contains(sender.getCommunicationMethod()))
                .forEach(sender -> sender.send(message, recipients, messageId));
        return messageId;
    }
}
