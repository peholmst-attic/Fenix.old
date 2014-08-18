package net.pkhapps.fenix.core.security.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of {@link net.pkhapps.fenix.core.security.entity.SystemUser}s.
 */
public interface SystemUserRepository extends JpaRepository<SystemUser, Long> {

    SystemUser findByUsername(String username);

}
