package net.pkhapps.fenix.core.sms.boundary;

import net.pkhapps.fenix.core.sms.entity.SmsProperties;

import java.util.Collection;

/**
 * Boundary interface for an SMS gateway that can be used to send SMS messages.
 */
public interface SmsGateway {

    /**
     * Attempts to synchronously send the specified SMS to the specified phone numbers.
     *
     * @param messageText   the text of the message.
     * @param phoneNumbers  the phone numbers to send the message to (in international format, eg. +35840123..).
     * @param smsProperties the SMS properties to use when sending the message.
     * @throws Exception if the operation failed.
     */
    void sendSMS(String messageText, Collection<String> phoneNumbers, SmsProperties smsProperties) throws Exception;

}
