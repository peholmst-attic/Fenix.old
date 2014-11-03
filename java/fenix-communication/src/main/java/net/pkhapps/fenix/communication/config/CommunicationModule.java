package net.pkhapps.fenix.communication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vaadin.spring.stuff.i18n.MessageProvider;
import org.vaadin.spring.stuff.i18n.ResourceBundleMessageProvider;
import org.vaadin.spring.stuff.sidebar.SideBarSection;

/**
 * Spring configuration for the Communication module.
 */
@Configuration
@SideBarSection(id = CommunicationModule.SECTION_ID, captionCode = "net.pkhapps.fenix.communication.sidebar.section.caption", order = 100)
public class CommunicationModule {

    public static final String SECTION_ID = "communication.section";

    /**
     * Messages for the Communication module.
     */
    @Bean
    MessageProvider communicationMessages() {
        return new ResourceBundleMessageProvider("net.pkhapps.fenix.communication.messages");
    }
}
