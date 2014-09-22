package net.pkhapps.fenix.communication.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.UI;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import net.pkhapps.fenix.core.components.ViewWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.stuff.sidebar.FontAwesomeIcon;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

/**
 * Side bar action that opens a {@link net.pkhapps.fenix.communication.ui.NewMessageView} window.
 */
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "net.pkhapps.fenix.communication.sidebar.newMessage.caption", order = 0)
@FontAwesomeIcon(FontAwesome.ENVELOPE)
@UIScope
@VaadinComponent
public class NewMessageAction implements Runnable {

    @Autowired
    UI ui;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    I18N i18n;

    @Override
    public void run() {
        final ViewWindow<NewMessageView> window = new ViewWindow<>(
                i18n.get("net.pkhapps.fenix.communication.ui.NewMessageView.title"),
                applicationContext.getBean(NewMessageView.class),
                "700px", "600px");
        ui.addWindow(window);
    }
}
