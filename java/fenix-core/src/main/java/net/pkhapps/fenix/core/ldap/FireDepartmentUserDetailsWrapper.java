package net.pkhapps.fenix.core.ldap;

import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.entity.FireDepartmentUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Wrapper class that binds together a {@link net.pkhapps.fenix.core.ldap.FenixLdapUserDetails} instance with a
 * {@link net.pkhapps.fenix.core.entity.FireDepartmentUser} instance.
 */
public class FireDepartmentUserDetailsWrapper implements FenixLdapUserDetails {

    private final FenixLdapUserDetails userDetails;
    private final FireDepartmentUser fireDepartmentUser;
    private final Set<GrantedAuthority> grantedAuthorities;

    public FireDepartmentUserDetailsWrapper(FenixLdapUserDetails userDetails, FireDepartmentUser fireDepartmentUser) {
        Assert.notNull(userDetails);
        Assert.isTrue(fireDepartmentUser == null || userDetails.getUsername().equals(fireDepartmentUser.getUid()));
        this.userDetails = userDetails;
        this.fireDepartmentUser = fireDepartmentUser;

        final Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.addAll(userDetails.getAuthorities());
        if (fireDepartmentUser != null) {
            grantedAuthorities.addAll(fireDepartmentUser.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
        }
        this.grantedAuthorities = Collections.unmodifiableSet(grantedAuthorities);
    }

    /**
     * Returns the wrapped {@link net.pkhapps.fenix.core.ldap.FenixLdapUserDetails} instance.
     */
    public FenixLdapUserDetails getUserDetails() {
        return userDetails;
    }

    /**
     * Returns the {@link net.pkhapps.fenix.core.entity.FireDepartmentUser} that corresponds to the LDAP user,
     * or {@code null} if no such object exists.
     */
    public FireDepartmentUser getFireDepartmentUser() {
        return fireDepartmentUser;
    }

    /**
     * Returns the {@link net.pkhapps.fenix.core.entity.FireDepartment} of the user, or {@code null} if not found.
     */
    public FireDepartment getFireDepartment() {
        return fireDepartmentUser == null ? null : fireDepartmentUser.getFireDepartment();
    }

    @Override
    public String getDisplayName() {
        return userDetails.getDisplayName();
    }

    @Override
    public String getDn() {
        return userDetails.getDn();
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation returns the union of the authorities of the {@link FenixLdapUserDetails} instance
     * and the authorities of the {@link net.pkhapps.fenix.core.entity.FireDepartmentUser} instance.
     * </p>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        if (fireDepartmentUser == null) {
            return false;
        } else {
            return fireDepartmentUser.getFireDepartment().isEnabled() && userDetails.isEnabled();
        }
    }
}
