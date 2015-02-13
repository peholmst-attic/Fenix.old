package net.pkhapps.fenix.core.boundary.rest.support;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an operation is attempted on a resource that does not exist.
 *
 * @see net.pkhapps.fenix.core.boundary.rest.support.InvalidReferenceException
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = NoSuchResourceException.REASON)
public class NoSuchResourceException extends RuntimeException {

    public static final String REASON = "The request tried to access a resource that does not exist";

    public NoSuchResourceException() {
    }

    public NoSuchResourceException(String message) {
        super(message);
    }
}
