package net.pkhapps.fenix.communication.entity;

/**
 * Enumeration of the different states a {@link ArchivedMessage} can be in for a
 * particular {@link net.pkhapps.fenix.communication.entity.CommunicationMethod}.
 *
 * @see net.pkhapps.fenix.communication.entity.MessageCommunicationMethodState
 */
public enum MessageState {
    /**
     * The message has not been sent yet.
     */
    UNSENT,
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
