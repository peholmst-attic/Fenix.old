package net.pkhapps.fenix.core.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of {@link net.pkhapps.fenix.core.entity.FireDepartmentUser}s.
 */
public interface FireDepartmentUserRepository extends JpaRepository<FireDepartmentUser, Long> {

    FireDepartmentUser findByUid(String uid);
}
