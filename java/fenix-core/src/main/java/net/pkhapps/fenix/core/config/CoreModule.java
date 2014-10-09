package net.pkhapps.fenix.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vaadin.spring.stuff.i18n.MessageProvider;
import org.vaadin.spring.stuff.i18n.ResourceBundleMessageProvider;

/**
 * Spring configuration for the Core module.
 */
@Configuration
public class CoreModule {

    /**
     * Messages for the Core module.
     */
    @Bean
    MessageProvider coreMessages() {
        return new ResourceBundleMessageProvider("net.pkhapps.fenix.core.messages");
    }
}
