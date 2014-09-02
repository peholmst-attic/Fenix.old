package net.pkhapps.fenix.core.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception thrown when an operation is attempted that requires the current user to belong
 * to a fire department, and the current user does not.
 */
public class FireDepartmentRequiredException extends AuthenticationException {

    public FireDepartmentRequiredException() {
        super("The operation requires the current user to belong to a fire department");
    }

    public FireDepartmentRequiredException(String message) {
        super(message);
    }
}
