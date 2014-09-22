package net.pkhapps.fenix.communication.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import net.pkhapps.fenix.communication.boundary.ContactGroupService;
import net.pkhapps.fenix.communication.boundary.ContactService;
import net.pkhapps.fenix.communication.boundary.MessageSenderService;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactGroup;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.AbstractPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Presenter that contains the logic of the {@link net.pkhapps.fenix.communication.ui.NewMessageView}.
 */
@Component
@PrototypeScope
class NewMessagePresenter extends AbstractPresenter<NewMessagePresenter.ViewDelegate> {

    private final ContactService contactService;
    private final ContactGroupService contactGroupService;
    private final MessageSenderService messageSenderService;

    @Autowired
    NewMessagePresenter(ContactService contactService, ContactGroupService contactGroupService, MessageSenderService messageSenderService) {
        this.contactService = contactService;
        this.contactGroupService = contactGroupService;
        this.messageSenderService = messageSenderService;
    }

    @Override
    protected void viewDelegateEntered() {
        Set<Recipient> recipients = new HashSet<>();
        recipients.addAll(contactService.findAll().stream().map(ContactRecipient::new).collect(Collectors.toSet()));
        recipients.addAll(contactGroupService.findAll().stream().map(GroupRecipient::new).collect(Collectors.toSet()));
        getViewDelegate().setAvailableRecipients(recipients);
    }

    @Override
    protected void updateButtonStates() {
    }

    interface ViewDelegate extends AbstractPresenter.ViewDelegate {

        void setAvailableRecipients(Collection<Recipient> recipients);
    }

    public interface Recipient extends Serializable {

        String PROP_NAME = "name";
        String PROP_ICON = "icon";

        String getName();

        Resource getIcon();
    }

    public static class ContactRecipient implements Recipient {

        private final Contact contact;

        protected ContactRecipient(Contact contact) {
            this.contact = contact;
        }

        @Override
        public String getName() {
            return contact.getDisplayName();
        }

        @Override
        public Resource getIcon() {
            return FontAwesome.USER;
        }
    }

    public static class GroupRecipient implements Recipient {

        private final ContactGroup group;

        protected GroupRecipient(ContactGroup group) {
            this.group = group;
        }

        @Override
        public String getName() {
            return group.getName();
        }

        @Override
        public Resource getIcon() {
            return FontAwesome.USERS;
        }
    }
}
