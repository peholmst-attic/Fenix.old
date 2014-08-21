package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.Message;
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
        protected void doSend(Message message) {
            // NOP
        }

        @Override
        protected CommunicationMethod getCommunicationMethod() {
            return CommunicationMethod.SMS;
        }
    }



}
