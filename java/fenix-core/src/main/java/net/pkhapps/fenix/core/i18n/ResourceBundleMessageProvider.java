package net.pkhapps.fenix.core.i18n;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Implementation of {@link net.pkhapps.fenix.core.i18n.MessageProvider} that reads messages
 * from {@link java.util.ResourceBundle}s with a specific base name.
 */
public class ResourceBundleMessageProvider implements MessageProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceBundleMessageProvider.class);

    private final String baseName;

    /**
     * Creates a new {@code ResourceBundleMessageProvider} with the given base name.
     *
     * @param baseName the base name to use.
     */
    public ResourceBundleMessageProvider(@NotNull String baseName) {
        this.baseName = baseName;
    }

    @NotNull
    @Override
    public Optional<MessageFormat> resolveCode(@NotNull String s, @NotNull Locale locale) {
        return getResourceBundle(locale)
                .map(bundle -> getString(bundle, s))
                .map(message -> getMessageFormat(message, locale));
    }

    @NotNull
    private Optional<ResourceBundle> getResourceBundle(@NotNull Locale locale) {
        try {
            return Optional.of(ResourceBundle.getBundle(baseName, locale));
        } catch (MissingResourceException ex) {
            LOGGER.warn("No message bundle with basename [{}] found for locale [{}]", baseName, locale);
            return Optional.empty();
        }
    }

    @Nullable
    private String getString(@NotNull ResourceBundle bundle, @NotNull String s) {
        try {
            return bundle.getString(s);
        } catch (MissingResourceException ex) {
            return null;
        }
    }

    @NotNull
    private MessageFormat getMessageFormat(@NotNull String message, @NotNull Locale locale) {
        return new MessageFormat(message, locale);
    }
}
