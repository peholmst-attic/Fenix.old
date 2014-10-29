package net.pkhapps.fenix.communication.entity;

/**
 * TODO Document me!
 */
public interface Recipient {

    String getRecipientName();

    default boolean supports(Class<? extends Recipient> recipientClass) {
        return recipientClass.isInstance(this);
    }
}
