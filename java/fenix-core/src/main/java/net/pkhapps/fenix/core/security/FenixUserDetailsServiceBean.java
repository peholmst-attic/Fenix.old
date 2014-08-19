package net.pkhapps.fenix.core.security;

import com.google.gwt.thirdparty.guava.common.collect.Sets;
import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.security.entity.FireDepartmentUser;
import net.pkhapps.fenix.core.security.entity.SystemUser;
import net.pkhapps.fenix.core.security.entity.SystemUserRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Optional;

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
            adminUser = new SystemUser.Builder()
                    .setUsername("admin")
                    .setFirstName("System")
                    .setLastName("Administrator")
                    .setEncryptedPassword(passwordEncoder.encode(password))
                    .setGrantedAuthorities(Sets.newHashSet(SystemAuthorities.MANAGE_SYSTEM.name()))
                    .build();
            systemUserRepository.saveAndFlush(adminUser);
            System.out.println(" ! ! ! !");
            System.out.println(String.format(" Created default admin user, username = %s, password = %s", adminUser.getUsername(), password));
            System.out.println(" Please change the password immediately!");
            System.out.println(" ! ! ! !");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.debug("Looking up user with username {}", username);
        final SystemUser systemUser = systemUserRepository.findByUsername(username);
        if (systemUser == null) {
            LOGGER.debug("Username {} was not found", username);
            throw new UsernameNotFoundException("Username " + username + " was not found");
        }
        return systemUser;
    }

    @Override
    public Optional<FireDepartment> getFireDepartmentOfUser(FenixUserDetails userDetails) {
        final String username = userDetails.getUsername();
        if (userDetails instanceof FireDepartmentUser) {
            final FireDepartment fireDepartment = ((FireDepartmentUser) userDetails).getFireDepartment();
            LOGGER.debug("User {} belongs to fire department {}", username, fireDepartment);
            return Optional.of(fireDepartment);
        } else {
            LOGGER.debug("User {} did not belong to any fire department", username);
            return Optional.empty();
        }
    }
}
