package net.pkhapps.fenix.core.security.context;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Default implementation of {@link net.pkhapps.fenix.core.security.context.CurrentUser}.
 *
 * @see org.springframework.security.core.context.SecurityContextHolder
 * @see org.springframework.security.core.context.SecurityContext#getAuthentication()
 */
@Component
class CurrentUserImpl implements CurrentUser {

    @Override
    public Optional<Authentication> getUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    @Override
    public String getUserName() {
        return getUser().map(Authentication::getName).orElse(UNKNOWN_USER_NAME);
    }
}
