package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import net.pkhapps.fenix.communication.boundary.ContactService;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.core.components.AbstractEntityWindow;
import net.pkhapps.fenix.core.validation.ValidationI18N;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.PrototypeScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;


/**
 * TODO Document me!
 */
@VaadinComponent
@PrototypeScope
class EditContactWindow extends AbstractEntityWindow<ContactService, Contact> {

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

    @Autowired
    EditContactWindow(I18N i18n, ValidationI18N validationI18N, ContactService service) {
        super(i18n, validationI18N, service);
    }

    @Override
    protected BeanFieldGroup<Contact> getBinder() {
        return binder;
    }

    @Override
    protected Component createForm() {
        final GridLayout layout = new GridLayout(2, 4);
        layout.setSpacing(true);

        firstName = new TextField(getI18N().get(getMessages().key("firstName.caption")));
        firstName.setWidth(15, Unit.EM);
        layout.addComponent(firstName, 0, 0);

        lastName = new TextField(getI18N().get(getMessages().key("lastName.caption")));
        lastName.setWidth(15, Unit.EM);
        layout.addComponent(lastName, 1, 0);

        singleName = new TextField(getI18N().get(getMessages().key("singleName.caption")));
        singleName.setInputPrompt(getI18N().get(getMessages().key("singleName.description")));
        singleName.setWidth("100%");
        layout.addComponent(singleName, 0, 1, 1, 1);

        email = new TextField(getI18N().get(getMessages().key("email.caption")));
        email.setWidth(15, Unit.EM);
        layout.addComponent(email, 0, 2);

        cellPhone = new TextField(getI18N().get(getMessages().key("cellPhone.caption")));
        cellPhone.setWidth(15, Unit.EM);
        layout.addComponent(cellPhone, 1, 2);

        communicationMethods = new CommunicationMethodField(getI18N().get(getMessages().key("communicationMethods.caption")), getI18N());
        communicationMethods.setMultiSelect(true);
        communicationMethods.addStyleName(FenixTheme.OPTIONGROUP_HORIZONTAL);
        layout.addComponent(communicationMethods, 0, 3);

        binder = new BeanFieldGroup<>(Contact.class);
        binder.bindMemberFields(this);

        firstName.focus();

        return layout;
    }
}
