package net.pkhapps.fenix.communication.security;

/**
 * Authorities for the Communication module.
 */
public final class CommunicationAuthorities {

    private CommunicationAuthorities() {
    }

    /**
     * The user is allowed to view contacts and groups.
     */
    public static final String VIEW_CONTACTS_AND_GROUPS = "VIEW_CONTACTS_AND_GROUPS";

    /**
     * The user is allowed to edit contacts and groups.
     */
    public static final String EDIT_CONTACTS_AND_GROUPS = "EDIT_CONTACTS_AND_GROUPS";

    /**
     * The user is allowed to view messages.
     */
    public static final String VIEW_MESSAGES = "VIEW_MESSAGES";

    /**
     * The user is allowed to send messages.
     */
    public static final String SEND_MESSAGES = "SEND_MESSAGES";

}
