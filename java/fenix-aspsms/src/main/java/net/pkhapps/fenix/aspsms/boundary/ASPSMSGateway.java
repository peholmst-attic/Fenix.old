package net.pkhapps.fenix.aspsms.boundary;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import net.pkhapps.fenix.aspsms.ASPSMSX2;
import net.pkhapps.fenix.aspsms.ASPSMSX2Soap;
import net.pkhapps.fenix.core.sms.boundary.SmsGateway;
import net.pkhapps.fenix.core.sms.entity.SmsProperties;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Implementation of {@link net.pkhapps.fenix.core.sms.boundary.SmsGateway} that uses ASPSMS.COM.
 */
@Service
class ASPSMSGateway implements SmsGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(ASPSMSGateway.class);

    private final ASPSMSX2Soap aspsmsx2Soap;

    ASPSMSGateway() {
        aspsmsx2Soap = initializeGateway();
    }

    // TODO Refactor this, it is ugly.

    private ASPSMSX2Soap initializeGateway() {
        return ProxyFactory.getProxy(ASPSMSX2Soap.class, new MethodInterceptor() {

            private ASPSMSX2Soap aspsmsx2Soap;

            private synchronized Optional<ASPSMSX2Soap> getTarget() {
                if (aspsmsx2Soap == null) {
                    try {
                        aspsmsx2Soap = new ASPSMSX2().getASPSMSX2Soap();
                    } catch (Exception e) {
                        LOGGER.warn("Failed to initialize ASPSMS web service client", e);
                    }
                }
                return Optional.of(aspsmsx2Soap);
            }

            @Override
            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
                ASPSMSX2Soap target = getTarget().orElseThrow(() -> new RuntimeException("ASPSMS web service not available"));
                return methodInvocation.getMethod().invoke(target, methodInvocation.getArguments());
            }
        });
    }

    @Override
    public Observable<?> sendSms(String messageText, Collection<String> phoneNumbers, SmsProperties smsProperties) {
        return new SendSmsCommand(messageText, phoneNumbers, smsProperties).observe();
    }

    private class SendSmsCommand extends HystrixCommand<Object> {

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
        protected Object run() throws Exception {
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
            return null;
        }
    }

}
