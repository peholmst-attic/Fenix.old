/*
 * Copyright (c) 2011 The original developers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.peholmst.fenix.entity.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.peholmst.stuff4vaadin.common.StringUtil;

/**
 * Base entity class for User accounts. A user is authenticated using a username
 * and password combination. The username can be changed afterwards as long as
 * it remains unique. Internally, a user ID of type long is used to keep track
 * of users. This value never changes.
 * 
 * @author Petter Holmström
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements java.io.Serializable {

    private static final long serialVersionUID = -6136022837562210593L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long userId;

    @Column(nullable = false, unique = true, length = 40)
    @Size(max = 40,
            min = 1,
            message = "{entity.util.User.userName.Size.message}")
    @NotNull(message = "{entity.util.User.userName.NotNull.message}")
    private String userName;

    @Column(nullable = false)
    @NotNull(message = "{entity.util.User.fullName.NotNull.message}")
    private String fullName;

    @Column(nullable = false)
    @NotNull(message = "{entity.util.User.emailAddress.NotNull.message}")
    // TODO Validate e-mail address
    private String emailAddress;

    @Column(nullable = false, length = 81)
    private String encryptedPassword;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean locked;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastFailedLoginAttempt;

    @Column(nullable = false)
    private int numberOfFailedLoginAttempts;

    @Column(nullable = false)
    private boolean forcePasswordChangeOnNextLogin;

    @Temporal(TemporalType.DATE)
    @Future(message = "{entity.util.User.expirationDate.Future.message}")
    private Date expirationDate;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    /**
     * Encrypts the given clear text password using the user name as salt and
     * sets the <tt>encryptedPassword</tt> property.
     * 
     * @see #setEncryptedPassword(String)
     */
    public void setCleartextPassword(String cleartextPassword) {
        setEncryptedPassword(encryptPassword(getUserName(), cleartextPassword));
    }

    /**
     * Checks if the given clear text password matches the encrypted one.
     */
    public boolean givenPasswordMatchesStoredOne(String cleartextPassword) {
        return encryptPassword(extractSaltFromEncryptedPassword(),
                cleartextPassword).equals(getEncryptedPassword());
    }

    private String extractSaltFromEncryptedPassword() {
        int separatorOffset = getEncryptedPassword().indexOf("$");
        if (separatorOffset == -1) {
            return "";
        } else {
            return getEncryptedPassword().substring(0, separatorOffset);
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Date getLastFailedLoginAttempt() {
        return lastFailedLoginAttempt;
    }

    public void setLastFailedLoginAttempt(Date lastFailedLoginAttempt) {
        this.lastFailedLoginAttempt = lastFailedLoginAttempt;
    }

    public int getNumberOfFailedLoginAttempts() {
        return numberOfFailedLoginAttempts;
    }

    public void setNumberOfFailedLoginAttempts(int numberOfFailedLoginAttempts) {
        this.numberOfFailedLoginAttempts = numberOfFailedLoginAttempts;
    }

    public boolean isForcePasswordChangeOnNextLogin() {
        return forcePasswordChangeOnNextLogin;
    }

    public void setForcePasswordChangeOnNextLogin(
            boolean forcePasswordChangeOnNextLogin) {
        this.forcePasswordChangeOnNextLogin = forcePasswordChangeOnNextLogin;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Encrypts the clear text password with SHA-1, using the given salt.
     * 
     * @param salt
     *            the salt.
     * @param cleartext
     *            the clear text password.
     * @return the encrypted password of the form "salt$encrypted password".
     */
    public static String encryptPassword(final String salt,
            final String cleartext) {
        final String clearTextToDigest = String.format("%s%s", salt, cleartext);
        try {
            final MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            final byte[] hash = sha1.digest(clearTextToDigest.getBytes());
            StringBuilder sb = new StringBuilder();
            sb.append(salt);
            sb.append("$");
            sb.append(StringUtil.toHex(hash));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Could not compute SHA1 checksum",
                    e);
        }
    }
}
