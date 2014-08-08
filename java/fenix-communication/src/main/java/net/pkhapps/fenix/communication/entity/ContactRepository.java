package net.pkhapps.fenix.communication.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of {@link net.pkhapps.fenix.communication.entity.Contact}s.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
