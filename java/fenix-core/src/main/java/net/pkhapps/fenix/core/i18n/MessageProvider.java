package net.pkhapps.fenix.core.i18n;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Optional;

/**
 * A {@code MessageProvider} provides messages for a {@link net.pkhapps.fenix.core.i18n.CompositeMessageSource}.
 * There can be multiple message provider beans in the same application context.
 */
public interface MessageProvider {

    /**
     * Attempts to resolve the specified code for the specified locale.
     *
     * @param s      the code of the message.
     * @param locale the locale
     * @return an {@code Optional} containing a {@code MessageFormat} for the message, or nothing if not found
     */
    @NotNull
    Optional<MessageFormat> resolveCode(@NotNull String s, @NotNull Locale locale);
}
