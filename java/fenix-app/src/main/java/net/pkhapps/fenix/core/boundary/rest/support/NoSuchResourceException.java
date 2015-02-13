package net.pkhapps.fenix.core.boundary.rest.support;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an operation is attempted on a resource that does not exist.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such resource")
public class NoSuchResourceException extends RuntimeException {
}
