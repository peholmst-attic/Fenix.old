package net.pkhapps.fenix.communication.entity;

import java.util.Collection;

/**
 * TODO Document me!
 */
public interface SmsRecipient extends Recipient {

    /**
     * Returns a collection of phone numbers to send the SMS to, or an empty collection if this recipient does
     * not support receiving SMSes.
     */
    Collection<String> getPhoneNumbers();
}
