package net.pkhapps.fenix.core.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a conflict occurs that prevents an operation from succeeding. For example if a piece of data
 * has been modified simultaneously by another session.
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "A conflicting change prevented the operation from succeeding")
public class ConflictException extends Exception {
}
