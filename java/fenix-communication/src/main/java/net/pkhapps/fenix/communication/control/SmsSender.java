package net.pkhapps.fenix.communication.control;

import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.SmsRecipient;
import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.security.FireDepartmentRequiredException;
import net.pkhapps.fenix.core.security.SessionInfo;
import net.pkhapps.fenix.core.sms.boundary.SmsGateway;
import net.pkhapps.fenix.core.sms.entity.SmsProperties;
import net.pkhapps.fenix.core.sms.entity.SmsPropertiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsSender.class);

    private final SmsPropertiesRepository smsPropertiesRepository;
    private final SmsGateway smsGateway;

    @Autowired
    SmsSender(ApplicationContext applicationContext, SessionInfo sessionInfo, SmsPropertiesRepository smsPropertiesRepository, SmsGateway smsGateway) {
        super(applicationContext, sessionInfo);
        this.smsPropertiesRepository = smsPropertiesRepository;
        this.smsGateway = smsGateway;
    }

    @Override
    public CommunicationMethod getCommunicationMethod() {
        return CommunicationMethod.SMS;
    }

    @Override
    protected void doSend(String message, Collection<SmsRecipient> recipients) throws Exception {
        final Optional<SmsProperties> smsProperties = getSmsProperties();
        if (smsProperties.isPresent()) {
            smsGateway.sendSMS(message, getPhoneNumbers(recipients), smsProperties.get());
        } else {
            throw new IllegalStateException("No SMSProperties found");
        }

    }

    private Optional<SmsProperties> getSmsProperties() {
        try {
            final FireDepartment fireDepartment = getSessionInfo().getCurrentFireDepartment();
            LOGGER.debug("Looking up SMSProperties of fire department {}", fireDepartment);
            final SmsProperties smsProperties = smsPropertiesRepository.findByFireDepartment(fireDepartment);
            return Optional.ofNullable(smsProperties);
        } catch (FireDepartmentRequiredException ex) {
            return Optional.empty();
        }
    }

    private Set<String> getPhoneNumbers(Collection<SmsRecipient> recipientCollection) {
        return recipientCollection.stream().flatMap(r -> r.getPhoneNumbers().stream()).collect(Collectors.toSet());
    }
}
