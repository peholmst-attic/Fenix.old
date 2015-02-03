package net.pkhapps.fenix.core.boundary.rest.context;

import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.entity.FireDepartmentRepository;
import net.pkhapps.fenix.core.entity.SystemUser;
import net.pkhapps.fenix.core.entity.SystemUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static net.pkhapps.fenix.core.security.context.CurrentUser.currentUser;

/**
 * Implementation of {@link FireDepartmentRetriever}.
 */
@Component
class FireDepartmentRetrieverImpl implements FireDepartmentRetriever {

    private static final Logger LOGGER = LoggerFactory.getLogger(FireDepartmentRetrieverImpl.class);
    private final FireDepartmentRepository fireDepartmentRepository;
    private final SystemUserRepository systemUserRepository;

    @Autowired
    FireDepartmentRetrieverImpl(FireDepartmentRepository fireDepartmentRepository, SystemUserRepository systemUserRepository) {
        this.fireDepartmentRepository = fireDepartmentRepository;
        this.systemUserRepository = systemUserRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Optional<FireDepartment> getFireDepartmentIfPermitted(Long fireDepartmentId) {
        if (currentUser().isPresent()) {
            final String email = currentUser().get().getName();
            FireDepartment fireDepartment = fireDepartmentRepository.findOne(fireDepartmentId);
            if (fireDepartment != null) {
                SystemUser user = systemUserRepository.findByEmail(email);
                if (user != null && user.getFireDepartmentRoles().containsKey(fireDepartment)) {
                    return Optional.of(fireDepartment);
                } else {
                    LOGGER.debug("User {} does not have access to fire department {}", email, fireDepartment);
                }
            } else {
                LOGGER.debug("Fire department with ID {} does not exist", fireDepartmentId);
            }
        } else {
            LOGGER.debug("No user bound to current thread");
        }
        return Optional.empty();
    }
}
