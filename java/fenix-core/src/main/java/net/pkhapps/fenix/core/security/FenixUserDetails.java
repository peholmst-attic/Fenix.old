package net.pkhapps.fenix.core.security;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by peholmst on 2014-08-06.
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

}
