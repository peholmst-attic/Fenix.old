package net.pkhapps.fenix.communication.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

/**
 * View for managing contacts.
 */
@VaadinView(name = ContactsView.VIEW_NAME)
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "communication.sidebar.contacts.caption", order = 20)
@PrototypeScope
public class ContactsView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "communication/contacts";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
