package net.pkhapps.fenix.communication.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import net.pkhapps.fenix.communication.entity.MessageId;
import net.pkhapps.fenix.communication.events.AbstractMessageEvent;
import net.pkhapps.fenix.communication.events.MessageFailedEvent;
import net.pkhapps.fenix.communication.events.MessageSentEvent;
import net.pkhapps.fenix.core.components.AbstractWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.stuff.sidebar.FontAwesomeIcon;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Side bar action that opens a {@link net.pkhapps.fenix.communication.ui.NewMessageWindow}.
 */
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "net.pkhapps.fenix.communication.sidebar.newMessage.caption", order = 0)
@FontAwesomeIcon(FontAwesome.ENVELOPE)
@UIScope
@VaadinComponent
public class NewMessageAction implements Runnable, AbstractWindow.Callback<MessageId>, ApplicationListener<AbstractMessageEvent> {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    UI ui;

    private Set<MessageId> pendingMessages = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void run() {
        applicationContext.getBean(NewMessageWindow.class).openWindow(ui, Optional.of(this));
    }

    // TODO Replace the listeners and notifications with a proper "Sent Messages" view

    @Override
    public void windowClosed(MessageId status) {
        pendingMessages.add(status);
        Notification.show("Meddelandet har lagts till kön för sändning", Notification.Type.TRAY_NOTIFICATION); // TODO Translate me!
    }

    @Override
    public void onApplicationEvent(AbstractMessageEvent event) {
        if (pendingMessages.remove(event.getMessageId())) {
            ui.access(() -> {
                if (event instanceof MessageSentEvent) {
                    Notification.show("Meddelandet har sänts (" + event.getCommunicationMethod() + ")", Notification.Type.TRAY_NOTIFICATION); // TODO Translate me!
                } else if (event instanceof MessageFailedEvent) {
                    Notification.show("Meddelandet kunde inte sändas (" + event.getCommunicationMethod() + ")", Notification.Type.ERROR_MESSAGE); // TODO Translate me!
                }
            });
        }
    }
}
