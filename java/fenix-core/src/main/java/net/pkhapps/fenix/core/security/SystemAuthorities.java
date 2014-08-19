package net.pkhapps.fenix.core.security;

/**
 * Enumeration of system authorities that can be granted to users. System modules may also
 * define authorities of their own.
 */
public enum SystemAuthorities {

    /**
     * The user has permission to manage the entire system.
     */
    MANAGE_SYSTEM,

    /**
     * The user has permission to manage the users of his or her fire department.
     */
    MANAGE_FIRE_DEPARTMENT_USERS

}
