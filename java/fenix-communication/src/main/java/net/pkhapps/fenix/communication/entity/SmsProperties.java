package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity containing fire department specific SMS properties (for aspsms.com).
 */
@Entity
@Table(name = "sms_properties", uniqueConstraints = @UniqueConstraint(columnNames = "fire_department_id"))
public class SmsProperties extends AbstractFireDepartmentSpecificEntity {

    @Column(name = "user_key", nullable = false)
    private String userKey = "";

    @Column(name = "password", nullable = false)
    private String password = "";

    @Column(name = "originator", nullable = false)
    private String originator;

    protected SmsProperties() {
    }

    public String getUserKey() {
        return userKey;
    }

    protected void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public String getOriginator() {
        return originator;
    }

    protected void setOriginator(String originator) {
        this.originator = originator;
    }

    public static class Builder extends AbstractFireDepartmentSpecificEntity.Builder<SmsProperties, Builder> {

        public Builder() {
        }

        public Builder(SmsProperties original) {
            super(original);
            setOriginator(original.getOriginator());
            setPassword(original.getPassword());
            setUserKey(original.getUserKey());
        }

        public String getOriginator() {
            return getInstance().getOriginator();
        }

        public Builder setOriginator(String originator) {
            getInstance().setOriginator(Strings.nullToEmpty(originator));
            return myself();
        }

        public String getPassword() {
            return getInstance().getPassword();
        }

        public Builder setPassword(String password) {
            getInstance().setPassword(Strings.nullToEmpty(password));
            return myself();
        }

        public String getUserKey() {
            return getInstance().getUserKey();
        }

        public Builder setUserKey(String userKey) {
            getInstance().setUserKey(userKey);
            return myself();
        }

        @Override
        protected SmsProperties newInstance() {
            return new SmsProperties();
        }
    }
}
