package net.pkhapps.fenix.core.security;

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
        return (FenixUserDetails) fenixUserDetailsService.loadUserByUsername(authentication.getName());
    }
}
