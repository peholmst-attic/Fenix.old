package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.aspsms.ASPSMSX2;
import net.pkhapps.fenix.aspsms.ASPSMSX2Soap;
import net.pkhapps.fenix.communication.entity.*;
import net.pkhapps.fenix.core.entity.FireDepartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@link net.pkhapps.fenix.communication.control.Sender}-implementation for the {@link net.pkhapps.fenix.communication.entity.CommunicationMethod#SMS}
 * communication method that uses ASPSMS.COM to send messages. Information needed by the SMS gateway are retrieved from
 * an {@link net.pkhapps.fenix.communication.entity.SmsProperties} instance.
 */
@Service
class SmsSender extends Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSender.class);

    private final SmsPropertiesRepository smsPropertiesRepository;
    private final ASPSMSX2Soap aspsmsx2Soap;

    @Autowired
    SmsSender(ApplicationContext applicationContext, MessageStateHelper messageStateHelper, PlatformTransactionManager platformTransactionManager, SmsPropertiesRepository smsPropertiesRepository) {
        super(applicationContext, messageStateHelper, platformTransactionManager);
        this.smsPropertiesRepository = smsPropertiesRepository;
        LOGGER.info("Initializing ASPSMS.COM web service client");
        aspsmsx2Soap = new ASPSMSX2().getASPSMSX2Soap();
    }

    @Override
    protected void doSend(Message message) {
        final Optional<SmsProperties> smsProperties = getSmsProperties(message);
        if (smsProperties.isPresent()) {
            if (sendMessage(message, smsProperties.get())) {
                succeeded(message);
            } else {
                failed(message);
            }
        } else {
            LOGGER.warn("No SMSProperties found for fire department {}", message.getFireDepartment());
            failed(message);
        }
    }

    @Override
    protected CommunicationMethod getCommunicationMethod() {
        return CommunicationMethod.SMS;
    }

    private Optional<SmsProperties> getSmsProperties(Message message) {
        final FireDepartment fireDepartment = message.getFireDepartment();
        Assert.notNull(fireDepartment, "Message was not associated with a fire department");
        LOGGER.debug("Looking up SMSProperties of fire department {}", fireDepartment);
        final SmsProperties smsProperties = smsPropertiesRepository.findByFireDepartment(fireDepartment);
        return Optional.ofNullable(smsProperties);
    }

    private Set<String> getPhoneNumbers(Collection<MessageRecipient> recipientCollection) {
        return recipientCollection
                .stream()
                .filter(recipient -> recipient.getCellPhoneNumber().isPresent())
                .map(recipient -> recipient.getCellPhoneNumber().get())
                .collect(Collectors.toSet());
    }

    private boolean sendMessage(Message message, SmsProperties smsProperties) {
        final Set<String> phoneNumbers = getPhoneNumbers(message.getRecipients());
        if (phoneNumbers.isEmpty()) {
            LOGGER.warn("Message {} did not include any recipients", message);
            return false;
        }
        LOGGER.debug("Sending message {} to recipients {}", message, phoneNumbers);
        final String resultCode = aspsmsx2Soap.sendSimpleTextSMS(
                smsProperties.getUserKey(),
                smsProperties.getPassword(),
                String.join(";", phoneNumbers),
                smsProperties.getOriginator(),
                message.getMessageText()
        );
        if (resultCode.equals("StatusCode:1")) {
            return true;
        } else {
            LOGGER.warn("Message {} was not successfully sent: {}", resultCode);
            return false;
        }
    }
}
