package net.pkhapps.fenix.core.security;

import com.google.common.collect.Sets;
import net.pkhapps.fenix.core.security.entity.SystemUser;
import net.pkhapps.fenix.core.security.entity.SystemUserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Implementation of {@link net.pkhapps.fenix.core.security.FenixUserDetailsService} that returns
 * {@link net.pkhapps.fenix.core.security.entity.SystemUser}s. If no admin user exists, a default one will
 * be created upon startup. The username and password are sent to STDOUT.
 */
@Service
class FenixUserDetailsServiceBean implements FenixUserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FenixUserDetailsServiceBean.class);

    private final SystemUserRepository systemUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    FenixUserDetailsServiceBean(SystemUserRepository systemUserRepository, PasswordEncoder passwordEncoder) {
        this.systemUserRepository = systemUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    void createAdminUserIfNotExists() {
        SystemUser adminUser = systemUserRepository.findByUsername("admin");
        if (adminUser == null) {
            LOGGER.info("Creating default admin user, sending username and password to STDOUT");
            final String password = RandomStringUtils.randomAlphanumeric(10);
            adminUser = new SystemUser();
            adminUser.setUsername("admin");
            adminUser.setFirstName("System");
            adminUser.setLastName("Administrator");
            adminUser.setEncryptedPassword(passwordEncoder.encode(password));
            adminUser.setGrantedAuthorities(Sets.newHashSet(SystemAuthorities.MANAGE_SYSTEM));
            systemUserRepository.saveAndFlush(adminUser);
            System.out.println(" ! ! ! !");
            System.out.println(String.format(" Created default admin user, username = %s, password = %s", adminUser.getUsername(), password));
            System.out.println(" Please change the password immediately!");
            System.out.println(" ! ! ! !");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public FenixUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Looking up user with username {}", username);
        final SystemUser systemUser = systemUserRepository.findByUsername(username);
        if (systemUser == null) {
            LOGGER.debug("Username {} was not found", username);
            throw new UsernameNotFoundException("Username " + username + " was not found");
        }
        return systemUser;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void changePassword(String username, String oldPassword, String newPassword) throws UsernameNotFoundException, BadCredentialsException {
        LOGGER.debug("Attempting to change password of {}", username);
        SystemUser user = systemUserRepository.findByUsername(username);
        if (user == null) {
            LOGGER.debug("Username {} was not found", username);
            throw new UsernameNotFoundException("Username " + username + " was not found");
        }
        if (!passwordEncoder.matches(oldPassword, user.getEncryptedPassword())) {
            LOGGER.debug("Old password for user {} was not correct", username);
            throw new BadCredentialsException("Old password is not correct");
        }
        user.setEncryptedPassword(passwordEncoder.encode(newPassword));
        systemUserRepository.saveAndFlush(user);
        LOGGER.debug("Password for user {} was changed successfully");
    }
}
