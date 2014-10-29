package net.pkhapps.fenix.communication.entity;

import net.pkhapps.fenix.core.entity.AbstractEntity;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * TODO Documenet me!
 */
@Entity
@Table(name = "message_receipt_status")
public class MessageReceiptCommunicationMethodStatus extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "com_method", nullable = false)
    private CommunicationMethod communicationMethod;

    @Column(name = "successful", nullable = false)
    private boolean successful = true;

    @Column(name = "additional_info", nullable = true)
    private String additionalInfo;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "message_receipt_recipients", joinColumns = @JoinColumn(name = "status_id"))
    @Column(name = "recipient", nullable = false)
    private Set<String> recipients = new HashSet<>();

    public MessageReceiptCommunicationMethodStatus() {
    }

    public MessageReceiptCommunicationMethodStatus(CommunicationMethod communicationMethod, boolean successful, Optional<String> additionalInfo, Set<String> recipients) {
        this.communicationMethod = Objects.requireNonNull(communicationMethod);
        this.successful = successful;
        this.additionalInfo = Objects.requireNonNull(additionalInfo).orElse(null);
        this.recipients = Objects.requireNonNull(recipients);
    }

    public CommunicationMethod getCommunicationMethod() {
        return communicationMethod;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public Optional<String> getAdditionalInfo() {
        return Optional.ofNullable(additionalInfo);
    }

    public Set<String> getRecipients() {
        return recipients;
    }
}
