package net.pkhapps.fenix.communication.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import net.pkhapps.fenix.communication.boundary.ContactGroupService;
import net.pkhapps.fenix.communication.boundary.ContactService;
import net.pkhapps.fenix.communication.boundary.MessageSenderService;
import net.pkhapps.fenix.communication.entity.ArchivedMessage;
import net.pkhapps.fenix.communication.entity.ArchivedMessageRecipient;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactGroup;
import net.pkhapps.fenix.core.components.AbstractWindowPresenter;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.PrototypeScope;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Presenter that contains the logic of the {@link net.pkhapps.fenix.communication.ui.NewMessageWindow}.
 */
@Component
@PrototypeScope
class NewMessagePresenter extends AbstractWindowPresenter<NewMessagePresenter.CloseStatus, NewMessagePresenter.ViewDelegate> {

    private final ContactService contactService;
    private final ContactGroupService contactGroupService;
    private final MessageSenderService messageSenderService;

    private NewMessageModel model;

    @Autowired
    NewMessagePresenter(ContactService contactService, ContactGroupService contactGroupService, MessageSenderService messageSenderService) {
        this.contactService = contactService;
        this.contactGroupService = contactGroupService;
        this.messageSenderService = messageSenderService;
    }

    void setModel(NewMessageModel model) {
        this.model = model;
        model.getCommunicationMethods().addValueChangeListener(event -> updateMaxLength());
    }

    @Override
    protected void viewAttached() {
        Set<NewMessageModel.Recipient> recipients = new HashSet<>();
        recipients.addAll(contactService.findAll().stream().map(ContactRecipient::new).collect(Collectors.toSet()));
        recipients.addAll(contactGroupService.findAll().stream().map(GroupRecipient::new).collect(Collectors.toSet()));
        getViewDelegate().setAvailableRecipients(recipients);
    }

    private void updateMaxLength() {
        int maxLength = -1;
        for (CommunicationMethod communicationMethod : model.getCommunicationMethods().getValue()) {
            if (communicationMethod.getMessageMaxLength() > maxLength) {
                maxLength = communicationMethod.getMessageMaxLength();
            }
        }
        getViewDelegate().setMaxLength(maxLength);
    }

    public void send() {
        ArchivedMessage message = new ArchivedMessage();
        message.setMessageText(model.getMessage().getValue());
        message.setRecipients(model.getRecipients().getValue().stream().flatMap(recipient -> recipient.toMessageRecipients().stream()).collect(Collectors.toSet()));
        message.setSendAs(model.getCommunicationMethods().getValue());
        try {
            messageSenderService.sendMessage(message);
            getViewDelegate().closeWindow(CloseStatus.SENDING);
        } catch (ValidationFailedException e) {
            getViewDelegate().showValidationErrors(e);
        }
    }

    public void cancel() {
        getViewDelegate().closeWindow(CloseStatus.CANCELLED);
    }

    interface ViewDelegate extends AbstractWindowPresenter.ViewDelegate<CloseStatus> {

        void setAvailableRecipients(Collection<NewMessageModel.Recipient> recipients);

        void setMaxLength(int maxLength);

        void showValidationErrors(ValidationFailedException exception);
    }

    enum CloseStatus {
        CANCELLED, SENDING
    }

    public static class ContactRecipient implements NewMessageModel.Recipient {

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

        @Override
        public Collection<ArchivedMessageRecipient> toMessageRecipients() {
            return Collections.singleton(contactToRecipient(contact));
        }

    }

    public static class GroupRecipient implements NewMessageModel.Recipient {

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

        @Override
        public Collection<ArchivedMessageRecipient> toMessageRecipients() {
            return group.getMembers().stream().map(NewMessagePresenter::contactToRecipient).collect(Collectors.toSet());
        }
    }

    private static ArchivedMessageRecipient contactToRecipient(Contact contact) {
        ArchivedMessageRecipient recipient = new ArchivedMessageRecipient();
        recipient.setName(contact.getDisplayName());
        if (contact.getCommunicationMethods().contains(CommunicationMethod.EMAIL)) {
            recipient.setEmail(contact.getEmail());
        }
        if (contact.getCommunicationMethods().contains(CommunicationMethod.SMS)) {
            recipient.setCellPhoneNumber(contact.getCellPhoneNumber());
        }
        return recipient;
    }
}
