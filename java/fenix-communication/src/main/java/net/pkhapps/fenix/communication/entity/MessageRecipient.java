package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Optional;

/**
 * Entity representing a single recipient of a {@link Message}.
 */
@Entity
@Table(name = "message_recipients")
public class MessageRecipient extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "cell_phone", nullable = true)
    private String cellPhoneNumber;

    @Column(name = "email", nullable = true)
    private String email;

    protected MessageRecipient() {
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public Optional<String> getCellPhoneNumber() {
        return Optional.ofNullable(cellPhoneNumber);
    }

    protected void setCellPhoneNumber(Optional<String> cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber.orElse(null);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    protected void setEmail(Optional<String> email) {
        this.email = email.orElse(null);
    }

    public class Builder extends AbstractEntity.Builder<MessageRecipient, Builder> {

        public Builder() {
        }

        public Builder(MessageRecipient original) {
            super(original);
            setName(original.getName());
            setCellPhoneNumber(original.getCellPhoneNumber());
            setEmail(original.getEmail());
        }

        public String getName() {
            return getInstance().getName();
        }

        public Builder setName(String name) {
            getInstance().setName(Strings.nullToEmpty(name));
            return myself();
        }

        public Optional<String> getCellPhoneNumber() {
            return getInstance().getCellPhoneNumber();
        }

        public Builder setCellPhoneNumber(Optional<String> cellPhoneNumber) {
            getInstance().setCellPhoneNumber(Objects.requireNonNull(cellPhoneNumber));
            return myself();
        }

        public Optional<String> getEmail() {
            return getInstance().getEmail();
        }

        public Builder setEmail(Optional<String> email) {
            getInstance().setEmail(Objects.requireNonNull(email));
            return myself();
        }

        @Override
        protected MessageRecipient newInstance() {
            return new MessageRecipient();
        }

    }
}
