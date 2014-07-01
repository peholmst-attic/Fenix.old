package net.pkhapps.fenix.communication.config;

import net.pkhapps.fenix.core.i18n.MessageProvider;
import net.pkhapps.fenix.core.i18n.ResourceBundleMessageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vaadin.spring.stuff.sidebar.SideBarSection;

/**
 * Spring configuration for the Communication module.
 */
@Configuration
@SideBarSection(id = CommunicationModule.SECTION_ID, captionCode = "communication.sidebar.section.caption")
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
