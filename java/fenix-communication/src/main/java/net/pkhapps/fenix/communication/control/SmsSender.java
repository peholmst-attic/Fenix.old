package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.MessageReceiptCommunicationMethodStatus;
import net.pkhapps.fenix.communication.entity.SmsRecipient;
import net.pkhapps.fenix.core.sms.boundary.SmsGateway;
import net.pkhapps.fenix.core.sms.boundary.SmsPropertiesService;
import net.pkhapps.fenix.core.sms.entity.SmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * {@link net.pkhapps.fenix.communication.control.Sender}-implementation for the {@link net.pkhapps.fenix.communication.entity.CommunicationMethod#SMS}
 * communication method that uses ASPSMS.COM to send messages. Information needed by the SMS gateway are retrieved from
 * an {@link net.pkhapps.fenix.core.sms.entity.SmsProperties} instance.
 */
@Service
class SmsSender extends Sender<SmsRecipient> {

    private final SmsPropertiesService smsPropertiesService;
    private final SmsGateway smsGateway;

    @Autowired
    SmsSender(SmsPropertiesService smsPropertiesService, SmsGateway smsGateway) {
        this.smsPropertiesService = smsPropertiesService;
        this.smsGateway = smsGateway;
    }

    @Override
    public CommunicationMethod getCommunicationMethod() {
        return CommunicationMethod.SMS;
    }

    @Override
    protected MessageReceiptCommunicationMethodStatus doSend(String message, Collection<SmsRecipient> recipients) {
        final Optional<SmsProperties> smsProperties = smsPropertiesService.getSmsProperties();
        try {
            if (smsProperties.isPresent()) {
                final Set<String> phoneNumbers = getPhoneNumbers(recipients);
                if (phoneNumbers.isEmpty()) {
                    return new MessageReceiptCommunicationMethodStatus(getCommunicationMethod(),
                            MessageReceiptCommunicationMethodStatus.Code.NO_RECIPIENTS,
                            Optional.empty(), phoneNumbers);
                }
                smsGateway.sendSMS(message, phoneNumbers, smsProperties.get());
                return new MessageReceiptCommunicationMethodStatus(getCommunicationMethod(),
                        MessageReceiptCommunicationMethodStatus.Code.SUCCESSFUL,
                        Optional.empty(),
                        phoneNumbers);
            } else {
                throw new IllegalStateException("No SMSProperties found");
            }
        } catch (Exception ex) {
            return new MessageReceiptCommunicationMethodStatus(getCommunicationMethod(),
                    MessageReceiptCommunicationMethodStatus.Code.FAILED,
                    Optional.of(ex.getMessage()), Collections.emptySet());
        }
    }

    private Set<String> getPhoneNumbers(Collection<SmsRecipient> recipientCollection) {
        return recipientCollection.stream().flatMap(r -> r.getPhoneNumbers().stream()).collect(Collectors.toSet());
    }
}
