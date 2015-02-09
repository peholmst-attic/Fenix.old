package net.pkhapps.fenix.core.security.context;

import net.pkhapps.fenix.core.entity.FireDepartment;

import java.util.Optional;

/**
 * Component used by components that set the {@link net.pkhapps.fenix.core.security.context.CurrentFireDepartment}.
 */
public interface FireDepartmentRetriever {

    /**
     * Returns the fire department with the specified ID if it exists and the current user
     * has access to it.
     *
     * @see net.pkhapps.fenix.core.security.context.CurrentUser
     */
    Optional<FireDepartment> getFireDepartmentIfPermitted(Long fireDepartmentId);

    /**
     * Returns the fire department with the specified ID if it exists and the current user has access to it,
     * or throws an exception.
     */
    FireDepartment requireFireDepartment(Long fireDepartmentId) throws NoSuchFireDepartmentException;
}
