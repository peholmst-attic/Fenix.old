package net.pkhapps.fenix.core.admin;

import org.springframework.stereotype.Component;
import org.vaadin.spring.stuff.sidebar.SideBarSection;

/**
 * TODO Document me
 */
@Component
@SideBarSection(id = AdminSection.SECTION_ID, captionCode = "net.pkhapps.fenix.core.admin.sidebar.section.caption")
public class AdminSection {

    public static final String SECTION_ID = "admin.section";
}
