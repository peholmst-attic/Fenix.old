package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;
import net.pkhapps.fenix.core.entity.FireDepartment;

/**
 * Interface for accessing information about the current session.
 */
public interface SessionInfo {

    /**
     * Returns the details of the current user.
     */
    FenixUserDetails getCurrentUserDetails();

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
