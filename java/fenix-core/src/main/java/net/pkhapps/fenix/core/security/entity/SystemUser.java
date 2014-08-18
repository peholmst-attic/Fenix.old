package net.pkhapps.fenix.core.security.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.security.FenixUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Entity representing a user of the system.
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
@DiscriminatorValue("system")
public class SystemUser extends AbstractEntity implements FenixUserDetails {

    @Column(name = "first_name", nullable = false)
    private String firstName = "";

    @Column(name = "last_name", nullable = false)
    private String lastName = "";

    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword = "";

    @Column(name = "username", unique = true, nullable = false)
    private String username = "";

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "authority", nullable = false)
    @CollectionTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id", nullable = false))
    private Set<String> grantedAuthorities = new HashSet<>();

    @Transient
    private Set<GrantedAuthority> authorities;

    @Column(name = "expired", nullable = false)
    private boolean expired = false;

    @Column(name = "locked", nullable = false)
    private boolean locked = false;

    @Column(name = "password_expired", nullable = false)
    private boolean passwordExpired = false;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    protected SystemUser() {
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getDisplayName() {
        return String.format("%s %s", firstName, lastName);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            authorities = Collections.unmodifiableSet(grantedAuthorities.stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toSet()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return encryptedPassword;
    }

    protected String getEncryptedPassword() {
        return encryptedPassword;
    }

    protected void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    protected Set<String> getGrantedAuthorities() {
        return Collections.unmodifiableSet(grantedAuthorities);
    }

    protected void setGrantedAuthorities(Set<String> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    protected boolean isExpired() {
        return expired;
    }

    protected void setExpired(boolean expired) {
        this.expired = expired;
    }

    protected boolean isLocked() {
        return locked;
    }

    protected void setLocked(boolean locked) {
        this.locked = locked;
    }

    protected boolean isPasswordExpired() {
        return passwordExpired;
    }

    protected void setPasswordExpired(boolean passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    @Override
    public String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !passwordExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    protected void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    protected static abstract class AbstractBuilder<ENTITY extends SystemUser, BUILDER extends AbstractBuilder<ENTITY, BUILDER>> extends AbstractEntity.Builder<ENTITY, BUILDER> {

        protected AbstractBuilder() {
        }

        protected AbstractBuilder(ENTITY original) {
            super(original);
            setLastName(original.getLastName());
            setFirstName(original.getFirstName());
            setUsername(original.getUsername());
            setGrantedAuthorities(original.getGrantedAuthorities());
            setEncryptedPassword(original.getEncryptedPassword());
            setExpired(original.isExpired());
            setEnabled(original.isEnabled());
            setLocked(original.isLocked());
            setPasswordExpired(original.isPasswordExpired());
        }

        public String getLastName() {
            return getInstance().getLastName();
        }

        public BUILDER setLastName(String lastName) {
            getInstance().setLastName(Strings.nullToEmpty(lastName));
            return myself();
        }

        public Set<String> getGrantedAuthorities() {
            return getInstance().getGrantedAuthorities();
        }

        public BUILDER setGrantedAuthorities(Set<String> grantedAuthorities) {
            if (grantedAuthorities == null) {
                grantedAuthorities = Collections.emptySet();
            }
            getInstance().setGrantedAuthorities(new HashSet<>(grantedAuthorities));
            return myself();
        }

        public String getEncryptedPassword() {
            return getInstance().getEncryptedPassword();
        }

        public BUILDER setEncryptedPassword(String encryptedPassword) {
            getInstance().setEncryptedPassword(Strings.nullToEmpty(encryptedPassword));
            return myself();
        }

        public String getUsername() {
            return getInstance().getUsername();
        }

        public BUILDER setUsername(String username) {
            getInstance().setUsername(Strings.nullToEmpty(username));
            return myself();
        }

        public boolean isEnabled() {
            return getInstance().isEnabled();
        }

        public BUILDER setEnabled(boolean enabled) {
            getInstance().setEnabled(enabled);
            return myself();
        }

        public String getFirstName() {
            return getInstance().getFirstName();
        }

        public BUILDER setFirstName(String firstName) {
            getInstance().setFirstName(Strings.nullToEmpty(firstName));
            return myself();
        }

        public boolean isExpired() {
            return getInstance().isExpired();
        }

        public BUILDER setExpired(boolean expired) {
            getInstance().setExpired(expired);
            return myself();
        }

        public boolean isLocked() {
            return getInstance().isLocked();
        }

        public BUILDER setLocked(boolean locked) {
            getInstance().setLocked(locked);
            return myself();
        }

        public boolean isPasswordExpired() {
            return getInstance().isPasswordExpired();
        }

        public BUILDER setPasswordExpired(boolean passwordExpired) {
            getInstance().setPasswordExpired(passwordExpired);
            return myself();
        }
    }

    public static class Builder extends AbstractBuilder<SystemUser, Builder> {

        public Builder() {
        }

        public Builder(SystemUser original) {
            super(original);
        }

        @Override
        protected SystemUser newInstance() {
            return new SystemUser();
        }
    }
}
