package net.pkhapps.fenix.core.security.context;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an operation is attempted on a fire department that exists, but is not the expected one in the given context.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = WrongFireDepartmentException.REASON)
public class WrongFireDepartmentException extends RuntimeException {

    public static final String REASON = "Wrong fire department";
}
