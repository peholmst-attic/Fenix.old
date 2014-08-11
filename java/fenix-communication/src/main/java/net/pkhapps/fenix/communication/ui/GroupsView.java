package net.pkhapps.fenix.communication.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.FontAwesomeIcon;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

/**
 * View for managing contact groups.
 */
@VaadinView(name = GroupsView.VIEW_NAME)
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "net.pkhapps.fenix.communication.sidebar.groups.caption", order = 30)
@FontAwesomeIcon(FontAwesome.USERS)
@PrototypeScope
public class GroupsView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "communication/groups";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
