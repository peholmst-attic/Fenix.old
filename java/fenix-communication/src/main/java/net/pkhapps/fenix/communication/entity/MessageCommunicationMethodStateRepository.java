package net.pkhapps.fenix.communication.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of {@link net.pkhapps.fenix.communication.entity.MessageCommunicationMethodState}s.
 */
public interface MessageCommunicationMethodStateRepository extends JpaRepository<MessageCommunicationMethodState, Long> {
}
