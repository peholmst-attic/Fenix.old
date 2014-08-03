package net.pkhapps.fenix.core.ldap;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enumeration of authorities that are granted to system users in LDAP.
 */
public enum FenixLdapAuthority implements GrantedAuthority {

    /**
     * The user is a system administrator.
     */
    SYSADMIN,

    /**
     * The user belongs to a {@link net.pkhapps.fenix.core.entity.FireDepartment} and has additional authorities
     * specified in {@link net.pkhapps.fenix.core.entity.FireDepartmentUser}.
     */
    FIRE_DEPARTMENT_USER;

    @Override
    public String getAuthority() {
        return "LDAP_ROLE_" + name();
    }
}
