package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.ArchivedMessage;
import net.pkhapps.fenix.communication.entity.ArchivedMessageRecipient;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.sms.boundary.SmsGateway;
import net.pkhapps.fenix.core.sms.entity.SmsProperties;
import net.pkhapps.fenix.core.sms.entity.SmsPropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.Assert;
import rx.Observable;
import rx.Observer;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * {@link net.pkhapps.fenix.communication.control.Sender}-implementation for the {@link net.pkhapps.fenix.communication.entity.CommunicationMethod#SMS}
 * communication method that uses ASPSMS.COM to send messages. Information needed by the SMS gateway are retrieved from
 * an {@link net.pkhapps.fenix.core.sms.entity.SmsProperties} instance.
 */
@Service
class SmsSender extends Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSender.class);

    private final SmsPropertiesRepository smsPropertiesRepository;
    private final SmsGateway smsGateway;

    @Autowired
    SmsSender(ApplicationContext applicationContext, MessageStateHelper messageStateHelper, PlatformTransactionManager platformTransactionManager, SmsPropertiesRepository smsPropertiesRepository, SmsGateway smsGateway) {
        super(applicationContext, messageStateHelper, platformTransactionManager);
        this.smsPropertiesRepository = smsPropertiesRepository;
        this.smsGateway = smsGateway;
    }

    @Override
    protected void doSend(ArchivedMessage message) {
        final Optional<SmsProperties> smsProperties = getSmsProperties(message);
        if (smsProperties.isPresent()) {
            Observable<?> result = smsGateway.sendSms(message.getMessageText(), getPhoneNumbers(message.getRecipients()), smsProperties.get());
            result.subscribe(new Observer<Object>() {
                @Override
                public void onCompleted() {
                    succeeded(message);
                }

                @Override
                public void onError(Throwable throwable) {
                    failed(message);
                }

                @Override
                public void onNext(Object o) {
                    // NOP
                }
            });
        } else {
            LOGGER.warn("No SMSProperties found for fire department {}", message.getFireDepartment());
            failed(message);
        }
    }

    @Override
    protected CommunicationMethod getCommunicationMethod() {
        return CommunicationMethod.SMS;
    }

    private Optional<SmsProperties> getSmsProperties(ArchivedMessage message) {
        final FireDepartment fireDepartment = message.getFireDepartment();
        Assert.notNull(fireDepartment, "Message was not associated with a fire department");
        LOGGER.debug("Looking up SMSProperties of fire department {}", fireDepartment);
        final SmsProperties smsProperties = smsPropertiesRepository.findByFireDepartment(fireDepartment);
        return Optional.ofNullable(smsProperties);
    }

    private Set<String> getPhoneNumbers(Collection<ArchivedMessageRecipient> recipientCollection) {
        /*return recipientCollection
                .stream()
                .filter(recipient -> recipient.getCellPhoneNumber().isPresent())
                .map(recipient -> recipient.getCellPhoneNumber().get())
                .collect(Collectors.toSet());*/
        return null;
    }
}
