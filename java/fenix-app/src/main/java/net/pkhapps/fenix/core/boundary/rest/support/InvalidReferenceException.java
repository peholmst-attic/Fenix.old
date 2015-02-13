package net.pkhapps.fenix.core.boundary.rest.support;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an operation that involves multiple resources is attempted, and at least one of
 * those resources does not exist. If the target resource does not exist, {@link net.pkhapps.fenix.core.boundary.rest.support.NoSuchResourceException} should
 * be used instead.
 *
 * @see net.pkhapps.fenix.core.boundary.rest.support.NoSuchResourceException
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = InvalidReferenceException.REASON)
public class InvalidReferenceException extends RuntimeException {

    public static final String REASON = "The request contained a reference to a resource that does not exist";

    public InvalidReferenceException() {
    }

    public InvalidReferenceException(String message) {
        super(message);
    }
}
