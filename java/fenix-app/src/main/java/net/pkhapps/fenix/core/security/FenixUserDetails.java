package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetails} that wraps a {@link net.pkhapps.fenix.core.entity.SystemUser}.
 * {@link net.pkhapps.fenix.core.security.UserRoles#makeFireDepartmentSpecificRole(String, net.pkhapps.fenix.core.entity.FireDepartment)} is used
 * when creating the granted authorities.
 */
class FenixUserDetails implements UserDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(FenixUserDetails.class);
    private final SystemUser systemUser;

    FenixUserDetails(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Please note that the result of this method depends on the state of {@link net.pkhapps.fenix.core.boundary.rest.context.CurrentFireDepartment}.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
        if (systemUser.isSysadmin()) {
            authoritySet.add(new SimpleGrantedAuthority(UserRoles.ROLE_SYSTEM_ADMINISTRATOR));
        }
        systemUser.getFireDepartmentRoles()
                .forEach((fd, role) -> authoritySet.add(
                                new SimpleGrantedAuthority(UserRoles.makeFireDepartmentSpecificRole(role, fd))
                        )
                );
        LOGGER.debug("User {} has authorities {}", getUsername(), authoritySet);
        return authoritySet;
    }

    @Override
    public String getPassword() {
        return systemUser.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return systemUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return systemUser.getAccountExpirationDate() == null || systemUser.getAccountExpirationDate().after(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !systemUser.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return systemUser.getPasswordExpirationDate() == null || systemUser.getPasswordExpirationDate().after(new Date());
    }

    @Override
    public boolean isEnabled() {
        return systemUser.isEnabled();
    }
}
