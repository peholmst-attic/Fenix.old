package net.pkhapps.fenix.core.ldap;

import org.springframework.security.ldap.userdetails.LdapUserDetails;

/**
 * Extended version of {@link org.springframework.security.ldap.userdetails.LdapUserDetails} that adds some
 * additional LDAP attributes.
 */
public interface FenixLdapUserDetails extends LdapUserDetails {

    /**
     * Returns the display name of the user. In LDAP, this is typically the {@code displayName} attribute value,
     * or the user's CN if not set.
     */
    String getDisplayName();
}
