package net.pkhapps.fenix.core.boundary.rest.context;

import net.pkhapps.fenix.core.entity.FireDepartment;

import java.util.Optional;

/**
 * Component used by the REST controllers and {@link CurrentFireDepartmentAwareRequestFilter}
 * to fetch a {@link net.pkhapps.fenix.core.entity.FireDepartment} instance.
 */
public interface FireDepartmentRetriever {

    /**
     * Returns the fire department with the specified ID if it exists and the current user
     * has access to it.
     *
     * @see net.pkhapps.fenix.core.security.context.CurrentUser
     */
    Optional<FireDepartment> getFireDepartmentIfPermitted(Long fireDepartmentId);
}
