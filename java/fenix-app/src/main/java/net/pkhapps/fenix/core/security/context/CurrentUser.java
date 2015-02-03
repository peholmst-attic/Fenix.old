package net.pkhapps.fenix.core.security.context;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Utility class that is used to retrieve the current user.
 *
 * @see org.springframework.security.core.context.SecurityContextHolder
 * @see org.springframework.security.core.context.SecurityContext#getAuthentication()
 */
public class CurrentUser {

    /**
     * User name returned by {@link #currentUserName()} when there is no user information bound to the current thread.
     */
    public static final String UNKNOWN_USER_NAME = "unknown";

    private CurrentUser() {
    }

    /**
     * Returns the {@link org.springframework.security.core.Authentication} token bound to the current thread, if any.
     */
    public static Optional<Authentication> currentUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    /**
     * Returns the name of the current user, or {@link #UNKNOWN_USER_NAME} if there is none.
     */
    public static String currentUserName() {
        return currentUser().map(Authentication::getName).orElse(UNKNOWN_USER_NAME);
    }
}
