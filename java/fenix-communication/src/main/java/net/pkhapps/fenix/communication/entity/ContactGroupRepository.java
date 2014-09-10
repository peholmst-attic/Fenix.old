package net.pkhapps.fenix.communication.entity;

import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository of {@link net.pkhapps.fenix.communication.entity.ContactGroup}s.
 */
public interface ContactGroupRepository extends JpaRepository<ContactGroup, Long> {

    List<ContactGroup> findByFireDepartment(FireDepartment fireDepartment);
}
