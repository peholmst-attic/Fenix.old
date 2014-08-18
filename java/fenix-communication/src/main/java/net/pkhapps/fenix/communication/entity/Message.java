package net.pkhapps.fenix.communication.entity;

import com.google.gwt.thirdparty.guava.common.base.Strings;
import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Entity representing a message.
 */
@Entity
@Table(name = "messages")
public class Message extends AbstractFireDepartmentSpecificEntity {

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    @CollectionTable(name = "message_communication_methods",
            joinColumns = @JoinColumn(name = "message_id", nullable = false))
    private Set<CommunicationMethod> sendAs = Collections.emptySet();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id", nullable = false)
    private Set<MessageRecipient> recipients = new HashSet<>();

    @Column(name = "sent_ts", nullable = true)
    private Timestamp sent;

    @Column(name = "message_text", nullable = false)
    @Lob
    private String messageText = "";

    protected Message() {
    }

    public Collection<CommunicationMethod> getSendAs() {
        return Collections.unmodifiableCollection(sendAs);
    }

    protected void setSendAs(Set<CommunicationMethod> sendAs) {
        this.sendAs = sendAs;
    }

    public Collection<MessageRecipient> getRecipients() {
        return Collections.unmodifiableCollection(recipients);
    }

    protected void setRecipients(Set<MessageRecipient> recipients) {
        this.recipients = recipients;
    }

    public Optional<Instant> getSent() {
        if (sent == null) {
            return Optional.empty();
        } else {
            return Optional.of(sent.toInstant());
        }
    }

    protected void setSent(Optional<Instant> sent) {
        this.sent = sent.map(Timestamp::from).orElse(null);
    }

    public String getMessageText() {
        return messageText;
    }

    protected void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public static class Builder extends AbstractFireDepartmentSpecificEntity.Builder<Message, Builder> {

        public Builder() {
        }

        public Builder(Message original) {
            super(original);
            setMessageText(original.getMessageText());
            setSent(original.getSent());
            setRecipients(original.getRecipients());
            setSendAs(original.getSendAs());
        }

        @Override
        public Builder asNew() {
            super.asNew();
            getInstance().getRecipients().forEach(this::setNew);
            return myself();
        }

        public String getMessageText() {
            return getInstance().getMessageText();
        }

        public Builder setMessageText(String messageText) {
            getInstance().setMessageText(Strings.nullToEmpty(messageText));
            return myself();
        }

        public Optional<Instant> getSent() {
            return getInstance().getSent();
        }

        public Builder setSent(Optional<Instant> sent) {
            getInstance().setSent(Objects.requireNonNull(sent));
            return myself();
        }

        public Collection<MessageRecipient> getRecipients() {
            return getInstance().getRecipients();
        }

        public Builder setRecipients(Collection<MessageRecipient> recipients) {
            if (recipients == null) {
                recipients = Collections.emptySet();
            }
            getInstance().setRecipients(recipients
                            .stream()
                            .map(new CopyFunction<>(MessageRecipient.Builder.class, getInstance().isNew()))
                            .collect(Collectors.toSet())
            );
            return myself();
        }

        public Collection<CommunicationMethod> getSendAs() {
            return getInstance().getSendAs();
        }

        public Builder setSendAs(Collection<CommunicationMethod> sendAs) {
            if (sendAs == null) {
                sendAs = Collections.emptySet();
            }
            getInstance().setSendAs(new HashSet<>(sendAs));
            return myself();
        }

        @Override
        protected Message newInstance() {
            return new Message();
        }
    }
}
