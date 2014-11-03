package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;
import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.security.authentication.BadCredentialsException;

/**
 * Interface for accessing information about the current session.
 */
public interface SessionInfo {

    /**
     * Returns the details of the current user.
     */
    FenixUserDetails getCurrentUserDetails();

    /**
     * Changes the password of the current user.
     *
     * @param oldPassword the user's current pasword.
     * @param newPassword the user's new password.
     * @throws org.springframework.security.authentication.BadCredentialsException if the old password was invalid.
     */
    void changePasswordOfCurrentUser(String oldPassword, String newPassword) throws BadCredentialsException;

    /**
     * Returns the fire department of the current user, or throws an exception if the current user
     * does not belong to any.
     */
    FireDepartment getCurrentFireDepartment() throws FireDepartmentRequiredException;

    /**
     * Throws an exception if the current user does not belong to any fire department, or if the
     * specified entity is associated with a different fire department than the current user's.
     */
    void checkFireDepartment(AbstractFireDepartmentSpecificEntity entity) throws WrongFireDepartmentException, FireDepartmentRequiredException;
}
