package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.security.entity.FireDepartmentUser;
import net.pkhapps.fenix.core.security.entity.SystemUser;
import net.pkhapps.fenix.core.security.entity.SystemUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Implementation of {@link net.pkhapps.fenix.core.security.FenixUserDetailsService} that returns
 * {@link net.pkhapps.fenix.core.security.entity.SystemUser}s.
 */
@Service
class FenixUserDetailsServiceBean implements FenixUserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FenixUserDetailsServiceBean.class);

    private final SystemUserRepository systemUserRepository;

    @Autowired
    FenixUserDetailsServiceBean(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
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
