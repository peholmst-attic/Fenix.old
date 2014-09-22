package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.TextField;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.AbstractForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;

/**
 * Form component for editing a single {@link net.pkhapps.fenix.communication.entity.Contact}.
 */
@VaadinComponent
@PrototypeScope
class ContactForm extends AbstractForm<Contact> {

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

    @Autowired
    ContactForm(I18N i18n) {
        super(i18n);
    }

    @Override
    protected void createFields() {
        firstName = new TextField(getMessage("firstName.caption"));
        firstName.setWidth("100%");
        addComponent(firstName);

        lastName = new TextField(getMessage("lastName.caption"));
        lastName.setWidth("100%");
        addComponent(lastName);

        singleName = new TextField(getMessage("singleName.caption"));
        singleName.setInputPrompt(getMessage("singleName.description"));
        singleName.setWidth("100%");
        addComponent(singleName);

        email = new TextField(getMessage("email.caption"));
        email.setWidth("100%");
        addComponent(email);

        cellPhone = new TextField(getMessage("cellPhone.caption"));
        cellPhone.setWidth("100%");
        addComponent(cellPhone);

        communicationMethods = new CommunicationMethodField(getMessage("communicationMethods.caption"), getI18N());
        communicationMethods.setMultiSelect(true);
        addComponent(communicationMethods);
    }

    @Override
    public Class<Contact> getBeanClass() {
        return Contact.class;
    }
}
