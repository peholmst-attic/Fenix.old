package net.pkhapps.fenix.communication.ui;

import net.pkhapps.fenix.communication.boundary.ContactService;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Presenter that contains the logic of the {@link net.pkhapps.fenix.communication.ui.ContactsView}.
 */
@Component
@PrototypeScope
class ContactsPresenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactsPresenter.class);

    private final ContactService contactService;
    private List<Contact> contacts = Collections.emptyList();
    private Optional<Contact> selection = Optional.empty();
    private ViewDelegate viewDelegate;
    private boolean editing;

    @Autowired
    ContactsPresenter(ContactService contactService) {
        this.contactService = contactService;
    }

    void viewDelegateInitialized(ViewDelegate viewDelegate) {
        this.viewDelegate = viewDelegate;
        updateButtonStates();
    }

    ViewDelegate getViewDelegate() {
        return Objects.requireNonNull(viewDelegate);
    }

    void add() {
        LOGGER.trace("Add");
        selection = Optional.empty();
        editing = true;
        getViewDelegate().setSelection(selection);
        getViewDelegate().showForm(new Contact());
        getViewDelegate().setFormEditMode(true);
        updateButtonStates();
    }

    void edit() {
        if (selection.isPresent()) {
            LOGGER.trace("Edit: {}", selection.get());
            editing = true;
            getViewDelegate().setFormEditMode(true);
            updateButtonStates();
        }
    }

    void delete() {
        if (selection.isPresent()) {
            Contact contactToDelete = selection.get();
            LOGGER.trace("Delete: {}", contactToDelete);
            contactService.delete(contactToDelete);
            contacts.remove(contactToDelete);
            getViewDelegate().setContacts(contacts);
            select(Optional.empty());
        }
    }

    void save() {
        Optional<Contact> contactOptional = getViewDelegate().commitForm();
        contactOptional.ifPresent(this::doSave);
    }

    private void doSave(Contact contact) {
        LOGGER.trace("Save: {}", contact);
        editing = false;
        boolean isNew = contact.isNew();
        try {
            contact = contactService.save(contact);
            if (isNew) {
                contacts.add(contact);
            } else {
                contacts.remove(contact);
                contacts.add(contact);
            }
            getViewDelegate().setContacts(contacts);
            doSelect(Optional.of(contact));
        } catch (ValidationFailedException ex) {
            getViewDelegate().showValidationErrors(ex);
        }
    }

    void cancel() {
        LOGGER.trace("Cancel");
        editing = false;
        getViewDelegate().discardForm();
        getViewDelegate().setFormEditMode(false);
        if (!selection.isPresent()) {
            getViewDelegate().hideForm();
        }
        updateButtonStates();
    }

    void refresh() {
        if (!editing) {
            LOGGER.trace("Refresh");
            contacts = contactService.findAll();
            getViewDelegate().setContacts(contacts);
            select(Optional.empty());
        }
    }

    void select(Optional<Contact> selection) {
        if (!this.selection.equals(selection)) {
            doSelect(selection);
        }
    }

    private void doSelect(Optional<Contact> selection) {
        LOGGER.trace("Select: {}", selection);
        this.selection = selection;
        getViewDelegate().setSelection(selection);
        if (selection.isPresent()) {
            getViewDelegate().showForm(selection.get());
            getViewDelegate().setFormEditMode(false);
        } else {
            getViewDelegate().hideForm();
        }
        updateButtonStates();
    }

    private void updateButtonStates() {
        LOGGER.trace("Update button states (editing = {}, selection = {})", editing, selection);
        getViewDelegate().setButtonsVisible(editing, ViewButton.SAVE, ViewButton.CANCEL);
        getViewDelegate().setButtonsVisible(selection.isPresent() && !editing, ViewButton.EDIT, ViewButton.DELETE);
        getViewDelegate().setButtonsVisible(!editing, ViewButton.ADD);
    }

    enum ViewButton {
        REFRESH, ADD, EDIT, SAVE, CANCEL, DELETE
    }

    interface ViewDelegate {

        /**
         * Shows the form with the specified contact. By default, the form is hidden.
         */
        void showForm(Contact contact);

        /**
         * Commits the form and returns the updated contact. If the form cannot be committed (e.g. due to
         * validation errors), an empty Optional is returned.
         */
        Optional<Contact> commitForm();

        /**
         * Shows the validation users to the user.
         */
        void showValidationErrors(ValidationFailedException validationErrors);

        /**
         * Discards the form.
         */
        void discardForm();

        /**
         * Hides the form.
         */
        void hideForm();

        /**
         * Shows the specified contacts in the table.
         */
        void setContacts(List<Contact> contacts);

        /**
         * Sets the currently selected contact in the table (can also be empty, which clears the selection).
         */
        void setSelection(Optional<Contact> selection);

        /**
         * Turns edit mode on or off.
         */
        void setFormEditMode(boolean editMode);

        /**
         * Shows or hides the specified buttons. By default, all buttons are hidden.
         */
        void setButtonsVisible(boolean visible, ViewButton... buttons);
    }

}
