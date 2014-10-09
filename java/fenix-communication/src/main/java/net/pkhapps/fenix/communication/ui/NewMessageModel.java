package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.util.ObjectProperty;
import com.vaadin.server.Resource;
import net.pkhapps.fenix.communication.entity.ArchivedMessageRecipient;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import org.vaadin.spring.PrototypeScope;
import org.vaadin.spring.VaadinComponent;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO Document me!
 */
@VaadinComponent
@PrototypeScope
class NewMessageModel implements Serializable {

    private final ObjectProperty<String> message = new ObjectProperty<>("");

    private final ObjectProperty<Set<CommunicationMethod>> communicationMethods = new ObjectProperty<>(new HashSet<>());

    private final ObjectProperty<Set<Recipient>> recipients = new ObjectProperty<>(new HashSet<>());

    public ObjectProperty<String> getMessage() {
        return message;
    }

    public ObjectProperty<Set<CommunicationMethod>> getCommunicationMethods() {
        return communicationMethods;
    }

    public ObjectProperty<Set<Recipient>> getRecipients() {
        return recipients;
    }

    public interface Recipient extends Serializable {

        String PROP_NAME = "name";
        String PROP_ICON = "icon";

        String getName();

        Resource getIcon();

        Collection<ArchivedMessageRecipient> toMessageRecipients();
    }

}
