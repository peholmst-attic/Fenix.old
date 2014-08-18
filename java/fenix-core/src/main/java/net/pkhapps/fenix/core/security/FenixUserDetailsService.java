package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

/**
 * Extended version of {@link org.springframework.security.core.userdetails.UserDetailsService}.
 */
public interface FenixUserDetailsService extends UserDetailsService {

    Optional<FireDepartment> getFireDepartmentOfUser(FenixUserDetails userDetails);
}
