package net.pkhapps.fenix.core.security.context;

import org.springframework.security.core.Authentication;

import java.util.Optional;

/**
 * Utility interface that allows easy access to the current user. Use it by requesting an instance of it from
 * the application context.
 */
public interface CurrentUser {

    /**
     * User name returned by {@link #getUserName()} when there is no user information bound to the current thread.
     */
    String UNKNOWN_USER_NAME = "unknown";

    /**
     * Returns the {@link org.springframework.security.core.Authentication} token bound to the current thread, if any.
     */
    Optional<Authentication> getUser();

    /**
     * Returns the name of the current user, or {@link #UNKNOWN_USER_NAME} if there is none.
     */
    String getUserName();
}
