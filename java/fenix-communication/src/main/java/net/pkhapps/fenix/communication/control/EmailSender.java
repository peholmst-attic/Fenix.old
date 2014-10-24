package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.EmailRecipient;
import net.pkhapps.fenix.core.security.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * TODO Implement me!
 */
@Service
class EmailSender extends Sender<EmailRecipient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSender.class);

    @Autowired
    EmailSender(ApplicationContext applicationContext, SessionInfo sessionInfo) {
        super(applicationContext, sessionInfo);
    }

    @Override
    public CommunicationMethod getCommunicationMethod() {
        return CommunicationMethod.EMAIL;
    }

    @Override
    protected void doSend(String message, Collection<EmailRecipient> recipients) throws Exception {
        throw new UnsupportedOperationException("Email has not been implemented yet");
    }
}
