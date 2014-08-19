package net.pkhapps.fenix.core.security;

/**
 * Enumeration of system authorities that can be granted to users. System modules may also
 * define authorities of their own.
 */
public final class SystemAuthorities {

    /**
     * The user belongs to a fire department.
     */
    public static final String ROLE_FIRE_DEPARTMENT_USER = "ROLE_FD_USER";
    /**
     * The user has permission to manage the entire system.
     */
    public static final String MANAGE_SYSTEM = "MANAGE_SYSTEM";

    private SystemAuthorities() {
    }
}
