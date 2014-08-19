package net.pkhapps.fenix.core.security;

/**
 * System authorities that can be granted to users. System modules may also
 * define authorities of their own.
 */
public final class SystemAuthorities {

    /**
     * The user has permission to manage the entire system.
     */
    public static final String MANAGE_SYSTEM = "MANAGE_SYSTEM";

    private SystemAuthorities() {
    }
}
