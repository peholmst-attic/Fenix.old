/*
 * Fenix
 * Copyright (C) 2014 Petter Holmström
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.pkhapps.fenix.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import net.pkhapps.fenix.validation.Phone;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Abstract entity class for users of the system.
 *
 * @see OrganizationUser
 * @see SysAdmin
 * @author Petter Holmström
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Table(name = "users")
public abstract class User extends AbstractEntity implements Cloneable, UserDetails {

    @Column(name = "uname", nullable = false, unique = true)
    @NotBlank
    private String username = "";

    @Column(name = "pword", nullable = false)
    @NotBlank
    private String password = "";

    @Column(name = "locked", nullable = false)
    private boolean locked = false;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "aexpires")
    @Temporal(TemporalType.DATE)
    private Date accountExpires;

    @Column(name = "pexpires")
    @Temporal(TemporalType.DATE)
    private Date passwordExpires;

    @Column(name = "email", nullable = false)
    @Email
    @NotBlank
    private String email;

    @Column(name = "mobile")
    @Phone
    private String mobilePhone;

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Date getAccountExpires() {
        return accountExpires;
    }

    public void setAccountExpires(Date accountExpires) {
        this.accountExpires = accountExpires;
    }

    public Date getPasswordExpires() {
        return passwordExpires;
    }

    public void setPasswordExpires(Date passwordExpires) {
        this.passwordExpires = passwordExpires;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountExpires == null || accountExpires.after(new Date());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return passwordExpires == null || passwordExpires.after(new Date());
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
