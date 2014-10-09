package net.pkhapps.fenix.communication.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of {@link ArchivedMessage}s.
 */
public interface MessageRepository extends JpaRepository<ArchivedMessage, Long> {
}
