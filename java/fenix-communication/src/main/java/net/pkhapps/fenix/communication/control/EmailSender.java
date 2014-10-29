package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.EmailRecipient;
import net.pkhapps.fenix.communication.entity.MessageReceiptCommunicationMethodStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * TODO Implement me!
 */
@Service
class EmailSender extends Sender<EmailRecipient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSender.class);

    EmailSender() {
    }

    @Override
    public CommunicationMethod getCommunicationMethod() {
        return CommunicationMethod.EMAIL;
    }

    @Override
    protected MessageReceiptCommunicationMethodStatus doSend(String message, Collection<EmailRecipient> recipients) {
        return new MessageReceiptCommunicationMethodStatus(getCommunicationMethod(), MessageReceiptCommunicationMethodStatus.Code.FAILED, Optional.of("Email has not been implemented yet"), Collections.emptySet());
    }
}
