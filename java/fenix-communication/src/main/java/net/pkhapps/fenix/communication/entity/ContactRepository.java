package net.pkhapps.fenix.communication.entity;

import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository of {@link net.pkhapps.fenix.communication.entity.Contact}s.
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByFireDepartment(FireDepartment fireDepartment);
}
