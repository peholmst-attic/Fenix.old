package net.pkhapps.fenix.core.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Extended version of {@link org.springframework.security.core.userdetails.UserDetailsService}.
 */
public interface FenixUserDetailsService extends UserDetailsService {
    FenixUserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
