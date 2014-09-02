package net.pkhapps.fenix.core.security;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception thrown when an operation is attempted on an object that is associated with another fire department
 * than the one that the current user belongs to.
 */
public class WrongFireDepartmentException extends AuthenticationException {

    public WrongFireDepartmentException() {
        super("The subject of the operation is associated with another fire department than the one the current user belongs to");
    }

    public WrongFireDepartmentException(String message) {
        super(message);
    }
}
