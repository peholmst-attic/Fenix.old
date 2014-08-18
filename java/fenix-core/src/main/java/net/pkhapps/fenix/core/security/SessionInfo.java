package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.annotations.SessionScope;
import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Optional;

/**
 * Session scoped bean that provides information about the current session.
 */
@Component
@SessionScope
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
        Assert.isInstanceOf(FenixUserDetails.class, "Stored user details are not an instance of FenixUserDetails");
        return (FenixUserDetails) authentication.getDetails();
    }

    /**
     * Returns the fire department of the current user, if the user is associated with one.
     */
    public Optional<FireDepartment> getCurrentFireDepartment() {
        return fenixUserDetailsService.getFireDepartmentOfUser(getCurrentUserDetails());
    }
}
