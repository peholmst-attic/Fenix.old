package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * TODO Document me!
 */
@Entity
@Table(name = "message_receipts")
public class MessageReceipt extends AbstractFireDepartmentSpecificEntity {

    private static final int EXCERPT_LENGTH = 200;

    @Column(name = "ts_utc", nullable = false)
    private Timestamp timestamp;

    @Column(name = "msg_excerpt", length = EXCERPT_LENGTH, nullable = false)
    private String messageExcerpt = "";

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receipt_id", nullable = false)
    private Set<MessageReceiptCommunicationMethodStatus> status = new HashSet<>();

    public Instant getTimestamp() {
        return timestamp == null ? null : timestamp.toInstant();
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = Timestamp.from(Objects.requireNonNull(timestamp));
    }

    public String getMessageExcerpt() {
        return messageExcerpt;
    }

    public void setMessageExcerpt(String messageExcerpt) {
        final String msg = Strings.nullToEmpty(messageExcerpt);
        if (msg.length() > EXCERPT_LENGTH) {
            this.messageExcerpt = msg.substring(0, EXCERPT_LENGTH);
        } else {
            this.messageExcerpt = msg;
        }
    }

    public Set<MessageReceiptCommunicationMethodStatus> getStatus() {
        return status;
    }

    public void setStatus(Set<MessageReceiptCommunicationMethodStatus> status) {
        this.status = Objects.requireNonNull(status);
    }

    public boolean isSuccessful() {
        for (MessageReceiptCommunicationMethodStatus s : status) {
            if (s.getCode() != MessageReceiptCommunicationMethodStatus.Code.SUCCESSFUL) {
                return false;
            }
        }
        return true;
    }
}
