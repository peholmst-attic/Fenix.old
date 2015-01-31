package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.entity.SystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * Implementation of {@link org.springframework.security.core.userdetails.UserDetails} that wraps a {@link net.pkhapps.fenix.core.entity.SystemUser}.
 * The current fire department is taken from {@link net.pkhapps.fenix.core.security.CurrentFireDepartment} when calculating the
 * granted authorities.
 */
class FenixUserDetails implements UserDetails {

    private final SystemUser systemUser;

    FenixUserDetails(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Please note that the result of this method depends on the state of {@link net.pkhapps.fenix.core.security.CurrentFireDepartment}.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authoritySet = new HashSet<>();
        if (systemUser.isSysadmin()) {
            authoritySet.add(new SimpleGrantedAuthority(UserRoles.ROLE_SYSTEM_ADMINISTRATOR));
        }
        getCurrentFireDepartmentRole().ifPresent(authoritySet::add);
        return authoritySet;
    }

    private Optional<SimpleGrantedAuthority> getCurrentFireDepartmentRole() {
        return CurrentFireDepartment.get().flatMap(this::getRole).map(SimpleGrantedAuthority::new);
    }

    private Optional<String> getRole(FireDepartment fireDepartment) {
        return Optional.ofNullable(systemUser.getFireDepartmentRoles().get(fireDepartment));
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
