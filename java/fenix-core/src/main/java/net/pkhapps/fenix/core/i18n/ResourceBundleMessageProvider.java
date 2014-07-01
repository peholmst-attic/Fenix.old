package net.pkhapps.fenix.core.i18n;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.*;

/**
 * Implementation of {@link net.pkhapps.fenix.core.i18n.MessageProvider} that reads messages
 * from {@link java.util.ResourceBundle}s with a specific base name.
 */
public class ResourceBundleMessageProvider implements MessageProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceBundleMessageProvider.class);

    private final String baseName;
    private final String encoding;

    /**
     * Creates a new {@code ResourceBundleMessageProvider} with the given base name and the UTF-8 encoding.
     *
     * @param baseName the base name to use.
     */
    public ResourceBundleMessageProvider(@NotNull String baseName) {
        this(baseName, "UTF-8");
    }

    /**
     * Creates a new {@code ResourceBundleMessageProvider} with the given base name and encoding.
     *
     * @param baseName the base name to use.
     * @param encoding the encoding to use when reading the resource bundle.
     */
    public ResourceBundleMessageProvider(@NotNull String baseName, @NotNull String encoding) {
        this.baseName = baseName;
        this.encoding = encoding;
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
            return Optional.of(ResourceBundle.getBundle(baseName, locale, new MessageControl()));
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

    private class MessageControl extends ResourceBundle.Control {
        @Override
        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
            if ("java.properties".equals(format)) {
                final String resourceName = toResourceName(toBundleName(baseName, locale), "properties");
                final InputStream stream = loader.getResourceAsStream(resourceName);
                try (Reader reader = new InputStreamReader(stream, encoding)) {
                    return new PropertyResourceBundle(reader);
                }
            } else {
                return super.newBundle(baseName, locale, format, loader, reload);
            }
        }
    }
}
