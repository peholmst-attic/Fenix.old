package net.pkhapps.fenix.communication.entity;

/**
 * Enumeration of supported communication methods.
 */
public enum CommunicationMethod {
    SMS(1377), EMAIL(-1);

    private final int maxLength;

    CommunicationMethod(int maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Returns the maximum length of the message.
     *
     * @return the maximum length (in number of characters), or -1 if the length is unlimited.
     */
    public int getMessageMaxLength() {
        return maxLength;
    }
}
