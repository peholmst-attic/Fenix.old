package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * Extended version of {@link org.springframework.security.core.userdetails.UserDetails} that add some additional
 * user attributes.
 */
public interface FenixUserDetails extends UserDetails {

    /**
     * Returns the first name of the user.
     */
    String getFirstName();

    /**
     * Returns the last name of the user.
     */
    String getLastName();

    /**
     * Returns the name of the user as displayed to other users in the system.
     */
    String getDisplayName();

    /**
     * Returns the fire department of the user, if any.
     */
    Optional<FireDepartment> getFireDepartment();

}
