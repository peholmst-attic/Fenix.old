package net.pkhapps.fenix.core.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Entity representing a user of the system.
 */
@Entity
@Table(name = "users")
public class SystemUser extends AbstractEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName = "";

    @Column(name = "last_name", nullable = false)
    private String lastName = "";

    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword = "";

    @Column(name = "email", unique = true, nullable = false)
    private String email = "";

    @Temporal(TemporalType.DATE)
    @Column(name = "password_exp_date", nullable = true)
    private Date passwordExpirationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "account_exp_date", nullable = true)
    private Date accountExpirationDate;

    @Column(name = "locked", nullable = false)
    private boolean locked = false;

    @Column(name = "sysadmin", nullable = false)
    private boolean sysadmin = false;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @ElementCollection
    @CollectionTable(name = "user_fire_department_roles",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "fire_department_id", "role"}))
    @MapKeyJoinColumn(name = "fire_department_id", nullable = false)
    @Column(name = "role", nullable = false)
    private Map<FireDepartment, String> fireDepartmentRoles = new HashMap<>();

    public SystemUser() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = Objects.requireNonNull(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = Objects.requireNonNull(lastName);
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = Objects.requireNonNull(encryptedPassword);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email);
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isSysadmin() {
        return sysadmin;
    }

    public void setSysadmin(boolean sysadmin) {
        this.sysadmin = sysadmin;
    }

    public Map<FireDepartment, String> getFireDepartmentRoles() {
        return fireDepartmentRoles;
    }

    public void setFireDepartmentRoles(Map<FireDepartment, String> fireDepartmentRoles) {
        this.fireDepartmentRoles = Objects.requireNonNull(fireDepartmentRoles);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
