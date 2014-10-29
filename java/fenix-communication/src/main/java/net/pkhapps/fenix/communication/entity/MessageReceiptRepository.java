package net.pkhapps.fenix.communication.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of {@link net.pkhapps.fenix.communication.entity.MessageReceipt}s.
 */
public interface MessageReceiptRepository extends JpaRepository<MessageReceipt, Long> {
}
