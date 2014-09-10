package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;
import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Default implementation of {@link net.pkhapps.fenix.core.security.SessionInfo}.
 */
@Component
class SessionInfoBean implements Serializable, SessionInfo {

    private final FenixUserDetailsService fenixUserDetailsService;

    @Autowired
    SessionInfoBean(FenixUserDetailsService fenixUserDetailsService) {
        this.fenixUserDetailsService = fenixUserDetailsService;
    }

    @Override
    public FenixUserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(authentication, "No authentication stored in SecurityContext");
        return fenixUserDetailsService.loadUserByUsername(authentication.getName());
    }

    @Override
    public FireDepartment getCurrentFireDepartment() throws FireDepartmentRequiredException {
        return getCurrentUserDetails().getFireDepartment().orElseThrow(FireDepartmentRequiredException::new);
    }

    @Override
    public void checkFireDepartment(AbstractFireDepartmentSpecificEntity entity) throws WrongFireDepartmentException, FireDepartmentRequiredException {
        final FireDepartment expected = getCurrentFireDepartment();
        final FireDepartment actual = entity.getFireDepartment();
        if (actual != null && !actual.equals(expected)) {
            throw new WrongFireDepartmentException();
        }
    }
}
