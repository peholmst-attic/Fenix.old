package net.pkhapps.fenix.communication.entity;

import java.util.Collection;

/**
 * TODO Document me!
 */
public interface EmailRecipient extends Recipient {

    /**
     * Returns a collection of e-mail addresses to send the e-mail to, or an empty collection if this recipient does
     * not support receiving e-mails.
     */
    Collection<String> getEmailAddresses();
}
