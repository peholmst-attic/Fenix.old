package net.pkhapps.fenix.core.security.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.security.FenixUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Entity representing a user of the system.
 */
@Entity
@Table(name = "users")
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

    @Temporal(TemporalType.DATE)
    @Column(name = "password_exp_date", nullable = true)
    private Date passwordExpirationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "account_exp_date", nullable = true)
    private Date accountExpirationDate;

    @Column(name = "locked", nullable = false)
    private boolean locked = false;

    @ManyToOne(optional = true)
    @JoinColumn(name = "fire_department_id", nullable = true)
    private FireDepartment fireDepartment;

    public SystemUser() {
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = Strings.nullToEmpty(firstName);
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = Strings.nullToEmpty(lastName);
    }

    @Override
    public String getDisplayName() {
        return String.format("%s %s", firstName, lastName);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities == null) {
            authorities = Collections.unmodifiableSet(
                    grantedAuthorities.stream().map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toSet())
            );
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return encryptedPassword;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = Strings.nullToEmpty(encryptedPassword);
    }

    public Set<String> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Set<String> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public Date getPasswordExpirationDate() {
        return passwordExpirationDate;
    }

    public void setPasswordExpirationDate(Date passwordExpirationDate) {
        this.passwordExpirationDate = passwordExpirationDate;
    }

    public Date getAccountExpirationDate() {
        return accountExpirationDate;
    }

    public void setAccountExpirationDate(Date accountExpirationDate) {
        this.accountExpirationDate = accountExpirationDate;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = Strings.nullToEmpty(username);
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpirationDate == null || accountExpirationDate.after(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return passwordExpirationDate == null || passwordExpirationDate.after(new Date());
    }

    @Override
    public boolean isEnabled() {
        return fireDepartment == null || fireDepartment.isEnabled();
    }

    @Override
    public Optional<FireDepartment> getFireDepartment() {
        return Optional.ofNullable(fireDepartment);
    }

    public void setFireDepartment(FireDepartment fireDepartment) {
        this.fireDepartment = fireDepartment;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
