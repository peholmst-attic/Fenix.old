package net.pkhapps.fenix.core.i18n;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractMessageSource;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Message source that resolves the messages by querying the {@link net.pkhapps.fenix.core.i18n.MessageProvider}s in
 * the application context. The resolved messages are cached.
 */
public class CompositeMessageSource extends AbstractMessageSource implements MessageSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompositeMessageSource.class);

    private final Collection<MessageProvider> messageProviders;

    private final Map<Locale, Map<String, MessageFormat>> messageFormatCache = new ConcurrentHashMap<>();

    public CompositeMessageSource(@NotNull ApplicationContext applicationContext) {
        LOGGER.info("Looking up MessageProviders");
        messageProviders = applicationContext.getBeansOfType(MessageProvider.class).values();
        if (LOGGER.isDebugEnabled()) {
            messageProviders.stream().forEach(messageProvider -> LOGGER.debug("Found MessageProvider [{}]", messageProvider));
        }
        LOGGER.info("Found {} MessageProvider(s)", messageProviders.size());
    }

    @Override
    @Nullable
    protected MessageFormat resolveCode(@NotNull String s, @NotNull Locale locale) {
        return queryCache(s, locale).orElseGet(() -> queryMessageProviders(s, locale).orElse(null));
    }

    @NotNull
    private Optional<MessageFormat> queryCache(@NotNull String s, @NotNull Locale locale) {
        final Map<String, MessageFormat> cache = getMessageFormatCache(locale);
        final Optional<MessageFormat> cachedMessageFormat = Optional.ofNullable(cache.get(s));
        return cachedMessageFormat;
    }

    @NotNull
    private Optional<MessageFormat> queryMessageProviders(@NotNull String s, @NotNull Locale locale) {
        LOGGER.debug("Querying message providers for code [{}] for locale [{}]", s, locale);
        for (MessageProvider messageProvider : messageProviders) {
            Optional<MessageFormat> messageFormat = messageProvider.resolveCode(s, locale);
            if (messageFormat.isPresent()) {
                LOGGER.debug("Code [{}] for locale [{}] found in provider [{}], storing in cache", s, locale, messageProvider);
                getMessageFormatCache(locale).put(s, messageFormat.get());
                return messageFormat;
            }
        }
        LOGGER.debug("Code [{}] for locale [{}] not found", s, locale);
        return Optional.empty();
    }

    @NotNull
    private Map<String, MessageFormat> getMessageFormatCache(@NotNull Locale locale) {
        Map<String, MessageFormat> cache = messageFormatCache.get(locale);
        if (cache == null) {
            cache = new ConcurrentHashMap<>();
            messageFormatCache.put(locale, cache);
        }
        return cache;
    }
}
