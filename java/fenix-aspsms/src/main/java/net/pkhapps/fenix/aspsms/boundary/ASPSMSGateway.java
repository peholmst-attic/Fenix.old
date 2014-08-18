package net.pkhapps.fenix.aspsms.boundary;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import net.pkhapps.fenix.aspsms.ASPSMSX2;
import net.pkhapps.fenix.aspsms.ASPSMSX2Soap;
import net.pkhapps.fenix.core.sms.boundary.SmsGateway;
import net.pkhapps.fenix.core.sms.entity.SmsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.Collection;

/**
 * Implementation of {@link net.pkhapps.fenix.core.sms.boundary.SmsGateway} that uses ASPSMS.COM.
 */
@Service
public class ASPSMSGateway implements SmsGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(ASPSMSGateway.class);

    private final ASPSMSX2Soap aspsmsx2Soap;

    public ASPSMSGateway() {
        aspsmsx2Soap = new ASPSMSX2().getASPSMSX2Soap();
    }

    @Override
    public Observable<?> sendSms(String messageText, Collection<String> phoneNumbers, SmsProperties smsProperties) {
        return new SendSmsCommand(messageText, phoneNumbers, smsProperties).observe();
    }

    private class SendSmsCommand extends HystrixCommand<String> {

        private final String messageText;
        private final Collection<String> phoneNumbers;
        private final SmsProperties smsProperties;

        protected SendSmsCommand(String messageText, Collection<String> phoneNumbers, SmsProperties smsProperties) {
            super(HystrixCommandGroupKey.Factory.asKey("ASPSMS"));
            this.messageText = messageText;
            this.phoneNumbers = phoneNumbers;
            this.smsProperties = smsProperties;
        }

        @Override
        protected String run() throws Exception {
            LOGGER.debug("Sending message \"{}\" to recipients {}", messageText, phoneNumbers);
            final String resultCode = aspsmsx2Soap.sendSimpleTextSMS(
                    smsProperties.getUserKey(),
                    smsProperties.getPassword(),
                    String.join(";", phoneNumbers),
                    smsProperties.getOriginator(),
                    messageText
            );
            LOGGER.debug("Returning result \"{}\"", resultCode);
            return resultCode;
        }
    }

}
