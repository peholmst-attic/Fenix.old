package net.pkhapps.fenix.communication.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of {@link net.pkhapps.fenix.communication.entity.Message}s.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
