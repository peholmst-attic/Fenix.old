package net.pkhapps.fenix.aspsms.boundary;

import net.pkhapps.fenix.aspsms.ASPSMSX2;
import net.pkhapps.fenix.aspsms.ASPSMSX2Soap;
import net.pkhapps.fenix.core.sms.boundary.SmsGateway;
import net.pkhapps.fenix.core.sms.entity.SmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Implementation of {@link net.pkhapps.fenix.core.sms.boundary.SmsGateway} that uses ASPSMS.COM.
 */
@Service
class ASPSMSGateway implements SmsGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(ASPSMSGateway.class);

    private final ASPSMSX2Soap aspsmsx2Soap;

    ASPSMSGateway() {
        aspsmsx2Soap = new ASPSMSX2().getASPSMSX2Soap();
        LOGGER.info("SMS gateway initialized");
    }

    @Override
    public void sendSMS(String messageText, Collection<String> phoneNumbers, SmsProperties smsProperties) throws Exception {
        LOGGER.debug("Sending message \"{}\" to recipients {}", messageText, phoneNumbers);
        final String resultCode = aspsmsx2Soap.sendSimpleTextSMS(
                smsProperties.getUserKey(),
                smsProperties.getPassword(),
                String.join(";", phoneNumbers),
                smsProperties.getOriginator(),
                messageText
        );
        LOGGER.debug("Response: \"{}\"", resultCode);
        if (!resultCode.equals("StatusCode:1")) {
            throw new Exception("Could not send SMS: " + resultCode);
        }
    }
}
