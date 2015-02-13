package net.pkhapps.fenix.core.boundary.rest.support;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Exception thrown when an operation is attempted on a resource that does not exist.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = NoSuchResourceException.REASON)
public class NoSuchResourceException extends RuntimeException {

    public static final String REASON = "No such resource";

    /**
     * Sends this exception as a response to an HTTP request.
     */
    public static void sendResponse(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, REASON);
    }
}
