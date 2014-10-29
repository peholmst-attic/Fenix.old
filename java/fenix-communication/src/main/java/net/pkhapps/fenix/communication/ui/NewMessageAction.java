package net.pkhapps.fenix.communication.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.UI;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.stuff.sidebar.FontAwesomeIcon;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

import java.util.Optional;

/**
 * Side bar action that opens a {@link net.pkhapps.fenix.communication.ui.NewMessageWindow}.
 */
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "net.pkhapps.fenix.communication.sidebar.newMessage.caption", order = 0)
@FontAwesomeIcon(FontAwesome.ENVELOPE)
@UIScope
@VaadinComponent
class NewMessageAction implements Runnable {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    UI ui;

    @Override
    public void run() {
        applicationContext.getBean(NewMessageWindow.class).openWindow(ui, Optional.empty());
    }

}
