package net.pkhapps.fenix.core.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Extended version of {@link org.springframework.security.core.userdetails.UserDetailsService}.
 */
public interface FenixUserDetailsService extends UserDetailsService {

    /**
     * Attempts to change the password of the specified user.
     *
     * @param username    the username (e-mail address) of the user.
     * @param oldPassword the old password.
     * @param newPassword the new password.
     * @throws UsernameNotFoundException if the username does not exist.
     * @throws BadCredentialsException   if the old password is wrong.
     */
    void changePassword(String username, String oldPassword, String newPassword) throws UsernameNotFoundException, BadCredentialsException;

    /**
     * Enumeration of audit event types recorded by this service.
     */
    enum AuditTypes {
        PASSWORD_CHANGE_SUCCESS,
        PASSWORD_CHANGE_FAILED
    }
}
