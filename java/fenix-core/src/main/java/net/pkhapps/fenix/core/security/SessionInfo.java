package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Bean that provides information about the current session.
 */
@Component
public class SessionInfo implements Serializable {

    private final FenixUserDetailsService fenixUserDetailsService;

    @Autowired
    public SessionInfo(FenixUserDetailsService fenixUserDetailsService) {
        this.fenixUserDetailsService = fenixUserDetailsService;
    }

    /**
     * Returns the details of the current user.
     */
    public FenixUserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Assert.notNull(authentication, "No authentication stored in SecurityContext");
        return fenixUserDetailsService.loadUserByUsername(authentication.getName());
    }

    /**
     * Returns the fire department of the current user, or throws an exception if the current user
     * does not belong to any.
     */
    public FireDepartment getCurrentFireDepartment() throws FireDepartmentRequiredException {
        return getCurrentUserDetails().getFireDepartment().orElseThrow(FireDepartmentRequiredException::new);
    }
}
