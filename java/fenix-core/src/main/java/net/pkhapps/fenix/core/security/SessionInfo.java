package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.annotations.SessionScope;
import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Optional;

/**
 * Session scoped bean that provides information about the current session.
 */
@Component
@SessionScope
public class SessionInfo implements Serializable {

    /**
     * Returns the details of the current user.
     */
    public FenixUserDetails getCurrentUserDetails() {
        return null; // TODO Implement me!
    }

    /**
     * Returns the fire department of the current user, if the user is associated with one.
     */
    public Optional<FireDepartment> getCurrentFireDepartment() {
        return null; // TODO Implement me!
    }
}
