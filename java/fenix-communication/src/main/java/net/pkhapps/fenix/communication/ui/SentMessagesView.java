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
 * View for browsing sent messages.
 */
@VaadinView(name = SentMessagesView.VIEW_NAME)
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "communication.sidebar.sentMessages.caption", order = 10)
@FontAwesomeIcon(FontAwesome.ARCHIVE)
@PrototypeScope
public class SentMessagesView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "communication/sentMessages";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
