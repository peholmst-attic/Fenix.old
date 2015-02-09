package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.FireDepartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;

import static net.pkhapps.fenix.core.security.context.CurrentFireDepartment.currentFireDepartment;

/**
 * An access decision voter that knows how to handle fire department specific roles, i.e. role names that include
 * the ID of a fire department.
 * <p>
 * This role voter supports all roles that are {@link UserRoles#isFireDepartmentSpecificRole(String) fire department specific}.
 * It will abstain from voting only if the config attributes do not contain such a role at all. Otherwise it will deny
 * access unless the authentication has the required role within the {@link net.pkhapps.fenix.core.security.context.CurrentFireDepartment current fire department}.
 *
 * @see net.pkhapps.fenix.core.security.UserRoles#makeFireDepartmentSpecificRole(String, net.pkhapps.fenix.core.entity.FireDepartment)
 */
public class FireDepartmentAwareRoleVoter implements AccessDecisionVoter<Object> {

    public static final Logger LOGGER = LoggerFactory.getLogger(FireDepartmentAwareRoleVoter.class);

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return (attribute.getAttribute() != null) && (UserRoles.isFireDepartmentSpecificRole(attribute.getAttribute()));
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        int result = ACCESS_ABSTAIN;
        Optional<FireDepartment> fireDepartment = currentFireDepartment();
        if (fireDepartment.isPresent()) {
            for (ConfigAttribute attribute : attributes) {
                if (this.supports(attribute)) {
                    result = ACCESS_DENIED;
                }
                for (GrantedAuthority authority : authentication.getAuthorities()) {
                    if (UserRoles.makeFireDepartmentSpecificRole(attribute.getAttribute(), fireDepartment.get()).equals(authority.getAuthority())) {
                        LOGGER.debug("Granting access to user {} based on {}", authentication.getName(), authority);
                        return ACCESS_GRANTED;
                    }
                }
            }
        } else {
            result = ACCESS_DENIED;
        }
        if (LOGGER.isDebugEnabled()) {
            if (result == ACCESS_DENIED) {
                LOGGER.debug("Denying access to user {}", authentication.getName());
            } else if (result == ACCESS_ABSTAIN) {
                LOGGER.debug("Abstaining from vote for user {}", authentication.getName());
            }
        }
        return result;
    }
}
