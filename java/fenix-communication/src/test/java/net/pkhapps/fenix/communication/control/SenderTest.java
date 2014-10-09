package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.ArchivedMessage;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Unit test for {@link Sender}.
 */
public class SenderTest {

    static class TestSender extends Sender {

        TestSender(ApplicationContext applicationContext, MessageStateHelper messageStateHelper, PlatformTransactionManager platformTransactionManager) {
            super(applicationContext, messageStateHelper, platformTransactionManager);
        }

        @Override
        protected void doSend(ArchivedMessage message) {
            // NOP
        }

        @Override
        protected CommunicationMethod getCommunicationMethod() {
            return CommunicationMethod.SMS;
        }
    }



}
