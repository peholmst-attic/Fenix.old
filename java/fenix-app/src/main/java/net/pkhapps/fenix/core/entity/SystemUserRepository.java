package net.pkhapps.fenix.core.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of {@link SystemUser}s.
 */
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

    SystemUser findByEmail(String email);

}
