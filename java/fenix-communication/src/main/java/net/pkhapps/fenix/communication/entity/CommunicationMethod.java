package net.pkhapps.fenix.communication.entity;

/**
 * Enumeration of supported communication methods.
 */
public enum CommunicationMethod {
    SMS(1377, SmsRecipient.class), EMAIL(-1, EmailRecipient.class);

    private final int maxLength;

    private final Class<? extends Recipient> recipientClass;

    CommunicationMethod(int maxLength, Class<? extends Recipient> recipientClass) {
        this.maxLength = maxLength;
        this.recipientClass = recipientClass;
    }

    /**
     * Returns the maximum length of the message.
     *
     * @return the maximum length (in number of characters), or -1 if the length is unlimited.
     */
    public int getMessageMaxLength() {
        return maxLength;
    }

    /**
     * Returns the subclass of {@link net.pkhapps.fenix.communication.entity.Recipient} that
     * recipients supported by this communication method should implement.
     */
    public Class<? extends Recipient> getRecipientClass() {
        return recipientClass;
    }
}
