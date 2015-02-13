package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.SystemUser;
import net.pkhapps.fenix.core.entity.SystemUserRepository;
import net.pkhapps.fenix.core.security.context.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of {@link net.pkhapps.fenix.core.security.FenixUserDetailsService}.
 */
@Service
class FenixUserDetailsServiceBean implements FenixUserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FenixUserDetailsServiceBean.class);

    private final SystemUserRepository systemUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationContext applicationContext;
    private final CurrentUser currentUser;

    @Autowired
    FenixUserDetailsServiceBean(SystemUserRepository systemUserRepository, PasswordEncoder passwordEncoder, ApplicationContext applicationContext, CurrentUser currentUser) {
        this.systemUserRepository = systemUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.applicationContext = applicationContext;
        this.currentUser = currentUser;
    }

    @Override
    public FenixUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Looking up user with username {}", username);
        final SystemUser systemUser = systemUserRepository.findByEmail(username);
        if (systemUser == null) {
            LOGGER.debug("Username {} was not found", username);
            throw new UsernameNotFoundException("Username " + username + " was not found");
        }
        return new FenixUserDetails(systemUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changePassword(String username, String oldPassword, String newPassword) throws UsernameNotFoundException, BadCredentialsException {
        LOGGER.debug("Attempting to change password of {}", username);
        SystemUser user = systemUserRepository.findByEmail(username);
        if (user == null) {
            LOGGER.debug("Username {} was not found", username);
            throw new UsernameNotFoundException("Username " + username + " was not found");
        }
        if (!passwordEncoder.matches(oldPassword, user.getEncryptedPassword())) {
            LOGGER.debug("Old password for user {} was not correct", username);
            applicationContext.publishEvent(new AuditApplicationEvent(currentUser.getUserName(), AuditTypes.PASSWORD_CHANGE_FAILED.name()));
            throw new BadCredentialsException("Old password is not correct");
        }
        user.setEncryptedPassword(passwordEncoder.encode(newPassword));
        systemUserRepository.saveAndFlush(user);
        LOGGER.debug("Password for user {} was changed successfully");
        applicationContext.publishEvent(new AuditApplicationEvent(currentUser.getUserName(), AuditTypes.PASSWORD_CHANGE_SUCCESS.name()));
    }
}
