package net.pkhapps.fenix.communication.entity;

/**
 * Enumeration of the different states a {@link net.pkhapps.fenix.communication.entity.Message} can be in.
 */
public enum MessageState {
    /**
     * The message is still being edited.
     */
    DRAFT,
    /**
     * The message is being sent.
     */
    SENDING,
    /**
     * The message has been sent successfully.
     */
    SENT,
    /**
     * The message could not be sent for some reason.
     */
    FAILED
}
