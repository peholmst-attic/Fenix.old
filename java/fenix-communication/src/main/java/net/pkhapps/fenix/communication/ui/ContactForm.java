package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.core.i18n.MessageKeyGenerator;
import net.pkhapps.fenix.theme.FenixTheme;
import org.vaadin.spring.i18n.I18N;

import java.util.Optional;

/**
 * Form component for editing a single {@link net.pkhapps.fenix.communication.entity.Contact}.
 */
class ContactForm extends FormLayout {

    private static final MessageKeyGenerator messages = new MessageKeyGenerator(ContactForm.class);
    private final I18N i18n;
    @PropertyId("firstName")
    private TextField firstName;
    @PropertyId("lastName")
    private TextField lastName;
    @PropertyId("singleName")
    private TextField singleName;
    @PropertyId("email")
    private TextField email;
    @PropertyId("cellPhoneNumber")
    private TextField cellPhone;
    @PropertyId("communicationMethods")
    private CommunicationMethodField communicationMethods;

    private BeanFieldGroup<Contact> binder;

    ContactForm(I18N i18n) {
        addStyleName(FenixTheme.FORMLAYOUT_LIGHT);

        this.i18n = i18n;

        firstName = new TextField(i18n.get(messages.key("firstName.caption")));
        addComponent(firstName);

        lastName = new TextField(i18n.get(messages.key("lastName.caption")));
        addComponent(lastName);

        singleName = new TextField(i18n.get(messages.key("singleName.caption")));
        singleName.setInputPrompt(i18n.get(messages.key("singleName.description")));
        addComponent(singleName);

        email = new TextField(i18n.get(messages.key("email.caption")));
        email.setWidth(20, Unit.EM);
        addComponent(email);

        cellPhone = new TextField(i18n.get(messages.key("cellPhone.caption")));
        addComponent(cellPhone);

        communicationMethods = new CommunicationMethodField(i18n.get(messages.key("communicationMethods.caption")), i18n);
        communicationMethods.setMultiSelect(true);
        addComponent(communicationMethods);

        binder = new BeanFieldGroup<>(Contact.class);
        binder.bindMemberFields(this);
    }

    void setContact(Contact contact) {
        binder.setItemDataSource(contact);
    }

    Optional<Contact> commit() {
        try {
            binder.commit();
            return Optional.of(binder.getItemDataSource().getBean());
        } catch (FieldGroup.CommitException e) {
            return Optional.empty();
        }
    }

    void discard() {
        binder.discard();
    }

    @Override
    public void focus() {
        firstName.focus();
    }

    @Override
    public boolean isReadOnly() {
        return binder.isReadOnly();
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        binder.setReadOnly(readOnly);
    }
}
