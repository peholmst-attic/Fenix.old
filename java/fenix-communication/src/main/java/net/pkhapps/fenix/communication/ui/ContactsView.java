package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.security.CommunicationAuthorities;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.DangerousButton;
import net.pkhapps.fenix.core.components.FriendlyButton;
import net.pkhapps.fenix.core.components.PrimaryButton;
import net.pkhapps.fenix.core.components.ViewTitleLabel;
import net.pkhapps.fenix.core.i18n.MessageKeyGenerator;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.FontAwesomeIcon;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

import static net.pkhapps.fenix.core.util.ButtonUtils.toClickListener;

/**
 * View for managing contacts.
 */
@VaadinView(name = ContactsView.VIEW_NAME)
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "net.pkhapps.fenix.communication.sidebar.contacts.caption", order = 20)
@FontAwesomeIcon(FontAwesome.USER)
@PrototypeScope
@Secured({CommunicationAuthorities.EDIT_CONTACTS_AND_GROUPS, CommunicationAuthorities.VIEW_CONTACTS_AND_GROUPS})
public class ContactsView extends VerticalLayout implements View, ContactsPresenter.ViewDelegate {

    public static final String VIEW_NAME = "communication/contacts";

    private static final MessageKeyGenerator messages = new MessageKeyGenerator(ContactsView.class);

    @Autowired
    I18N i18n;

    @Autowired
    ContactsPresenter presenter;

    private ViewTitleLabel title;
    private Table contactsTable;
    private BeanItemContainer<Contact> contactsContainer;
    private Button add;
    private PrimaryButton edit;
    private FriendlyButton save;
    private Button cancel;
    private DangerousButton delete;
    private ContactForm contactForm;
    private Label noContactSelected;

    @PostConstruct
    void init() {
        setSizeFull();

        title = new ViewTitleLabel(i18n.get(messages.key("title")));
        addComponent(title);

        final HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        splitPanel.setSizeFull();
        splitPanel.setSplitPosition(450, Unit.PIXELS, true);
        addComponent(splitPanel);
        setExpandRatio(splitPanel, 1);

        contactsContainer = new BeanItemContainer<>(Contact.class);
        contactsTable = new Table();
        contactsTable.setContainerDataSource(contactsContainer);
        contactsTable.setVisibleColumns(
                Contact.PROP_DISPLAY_NAME,
                Contact.PROP_CELL_PHONE_NUMBER,
                Contact.PROP_EMAIL,
                Contact.PROP_COMMUNICATION_METHODS);
        contactsTable.setColumnHeader(Contact.PROP_DISPLAY_NAME, i18n.get(messages.key("table.name")));
        contactsTable.setColumnHeader(Contact.PROP_CELL_PHONE_NUMBER, i18n.get(messages.key("table.cellPhone")));
        contactsTable.setColumnHeader(Contact.PROP_EMAIL, i18n.get(messages.key("table.email")));
        contactsTable.setColumnHeader(Contact.PROP_COMMUNICATION_METHODS, i18n.get(messages.key("table.communicationMethods")));
        contactsTable.setConverter(Contact.PROP_COMMUNICATION_METHODS, new CommunicationMethodConverter(i18n));
        contactsTable.setSizeFull();
        contactsTable.addStyleName(FenixTheme.TABLE_BORDERLESS);
        contactsTable.setSelectable(true);
        contactsTable.addValueChangeListener(this::select);
        contactsTable.setSortContainerPropertyId(Contact.PROP_DISPLAY_NAME);
        splitPanel.setFirstComponent(contactsTable);

        final VerticalLayout editorLayout = new VerticalLayout();
        editorLayout.setSizeFull();
        editorLayout.setMargin(true);
        splitPanel.setSecondComponent(editorLayout);

        noContactSelected = new Label(i18n.get(messages.key("noContactSelected")));
        editorLayout.addComponent(noContactSelected);

        contactForm = new ContactForm(i18n);
        contactForm.setSizeFull();
        editorLayout.addComponent(contactForm);
        editorLayout.setExpandRatio(contactForm, 1);
        contactForm.setVisible(false);

        final HorizontalLayout editorButtons = new HorizontalLayout();
        editorButtons.setSpacing(true);
        editorButtons.setWidth("100%");
        editorLayout.addComponent(editorButtons);
        editorLayout.setComponentAlignment(editorButtons, Alignment.BOTTOM_LEFT);

        add = new Button(i18n.get(messages.key("add.caption")), toClickListener(presenter::add));
        add.setDescription(i18n.get(messages.key("add.description")));
        add.setVisible(false);
        editorButtons.addComponent(add);

        final Label splitter = new Label();
        editorButtons.addComponent(splitter);
        editorButtons.setExpandRatio(splitter, 1);

        edit = new PrimaryButton(i18n.get(messages.key("edit.caption")), toClickListener(presenter::edit));
        edit.setDescription(i18n.get(messages.key("edit.description")));
        edit.setVisible(false);
        editorButtons.addComponent(edit);
        editorButtons.setComponentAlignment(edit, Alignment.BOTTOM_RIGHT);

        save = new FriendlyButton(i18n.get(messages.key("save.caption")), toClickListener(presenter::save));
        save.setDescription(i18n.get(messages.key("save.description")));
        save.setVisible(false);
        editorButtons.addComponent(save);
        editorButtons.setComponentAlignment(save, Alignment.BOTTOM_RIGHT);

        cancel = new Button(i18n.get(messages.key("cancel.caption")), toClickListener(presenter::cancel));
        cancel.setDescription(i18n.get(messages.key("cancel.description")));
        cancel.setVisible(false);
        editorButtons.addComponent(cancel);
        editorButtons.setComponentAlignment(cancel, Alignment.BOTTOM_RIGHT);

        delete = new DangerousButton(i18n.get(messages.key("delete.caption")), toClickListener(presenter::delete));
        delete.setDescription(i18n.get(messages.key("delete.description")));
        delete.setVisible(false);
        editorButtons.addComponent(delete);
        editorButtons.setComponentAlignment(edit, Alignment.BOTTOM_RIGHT);

        presenter.viewDelegateInitialized(this);
    }

    private void select(Property.ValueChangeEvent event) {
        final Contact contact = (Contact) contactsTable.getValue();
        presenter.select(Optional.ofNullable(contact));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        presenter.refresh();
    }

    @Override
    public void showForm(Contact contact) {
        contactForm.setContact(contact);
        contactForm.setVisible(true);
        noContactSelected.setVisible(false);
    }

    @Override
    public Optional<Contact> commitForm() {
        return contactForm.commit();
    }

    @Override
    public void showValidationErrors(ValidationFailedException validationErrors) {
        // TODO Implement showValidationErrors
        throw new UnsupportedOperationException("showValidationErrors not implemented yet");
    }

    @Override
    public void discardForm() {
        contactForm.discard();
    }

    @Override
    public void hideForm() {
        contactForm.setVisible(false);
        noContactSelected.setVisible(true);
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        contactsContainer.removeAllItems();
        contactsContainer.addAll(contacts);
        contactsTable.sort();
    }

    @Override
    public void setSelection(Optional<Contact> selection) {
        contactsTable.setValue(selection.orElse(null));
    }

    @Override
    public void setFormEditMode(boolean editMode) {
        contactForm.setReadOnly(!editMode);
        if (editMode) {
            contactForm.focus();
        }
    }

    @Override
    public void setButtonsVisible(boolean visible, ContactsPresenter.ViewButton... buttons) {
        for (ContactsPresenter.ViewButton button : buttons) {
            getButton(button).ifPresent(btn -> {
                btn.setVisible(visible);
                if (visible) {
                    btn.setClickShortcut(getButtonClickShortcut(button));
                } else {
                    btn.setClickShortcut(0);
                }
            });
        }
    }

    private Optional<Button> getButton(ContactsPresenter.ViewButton viewButton) {
        switch (viewButton) {
            case ADD:
                return Optional.of(add);
            case CANCEL:
                return Optional.of(cancel);
            case DELETE:
                return Optional.of(delete);
            case EDIT:
                return Optional.of(edit);
            case SAVE:
                return Optional.of(save);
            default:
                return Optional.empty();
        }
    }

    private int getButtonClickShortcut(ContactsPresenter.ViewButton viewButton) {
        switch (viewButton) {
            case CANCEL:
                return ShortcutAction.KeyCode.ESCAPE;
            case EDIT:
                return ShortcutAction.KeyCode.ENTER;
            case SAVE:
                return ShortcutAction.KeyCode.ENTER;
            default:
                return 0;
        }
    }
}
