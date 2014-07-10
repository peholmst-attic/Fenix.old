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
 * View for creating and sending new messages.
 */
@VaadinView(name = NewMessageView.VIEW_NAME)
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "communication.sidebar.newMessage.caption", order = 0)
@FontAwesomeIcon(FontAwesome.ENVELOPE)
@PrototypeScope
public class NewMessageView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "communication/newMessage";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
