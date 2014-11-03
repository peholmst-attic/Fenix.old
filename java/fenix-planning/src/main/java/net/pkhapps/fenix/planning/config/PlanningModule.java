package net.pkhapps.fenix.planning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vaadin.spring.stuff.i18n.MessageProvider;
import org.vaadin.spring.stuff.i18n.ResourceBundleMessageProvider;
import org.vaadin.spring.stuff.sidebar.SideBarSection;

/**
 * Spring configuration for the Planning module.
 */
@Configuration
@SideBarSection(id = PlanningModule.SECTION_ID, captionCode = "net.pkhapps.fenix.planning.sidebar.section.caption", order = 200)
public class PlanningModule {

    public static final String SECTION_ID = "planning.section";

    /**
     * Messages for the Planning module.
     */
    @Bean
    MessageProvider planningMessages() {
        return new ResourceBundleMessageProvider("net.pkhapps.fenix.planning.messages");
    }
}
