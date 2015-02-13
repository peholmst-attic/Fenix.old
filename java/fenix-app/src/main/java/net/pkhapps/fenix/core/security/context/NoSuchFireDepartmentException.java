package net.pkhapps.fenix.core.security.context;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Exception thrown when an operation is attempted on a fire department that does not exist.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = NoSuchFireDepartmentException.REASON)
public class NoSuchFireDepartmentException extends RuntimeException {

    public static final String REASON = "No such fire department";

    /**
     * Sends this exception as a response to an HTTP request.
     */
    public static void sendResponse(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, REASON);
    }
}
