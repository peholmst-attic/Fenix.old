package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Component;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.communication.boundary.ContactGroupService;
import net.pkhapps.fenix.communication.boundary.ContactService;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactGroup;
import net.pkhapps.fenix.core.components.AbstractEntityWindow;
import net.pkhapps.fenix.core.components.CustomTwinColSelect;
import net.pkhapps.fenix.core.validation.ValidationI18N;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.PrototypeScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;

/**
 * TODO Document me!
 */
@VaadinComponent
@PrototypeScope
class EditGroupWindow extends AbstractEntityWindow<ContactGroupService, ContactGroup> {

    private final ContactService contactService;
    @PropertyId("name")
    private TextField name;
    @PropertyId("members")
    private CustomTwinColSelect<Contact> members;
    private BeanFieldGroup<ContactGroup> binder;

    @Autowired
    EditGroupWindow(I18N i18n, ValidationI18N validationI18N, ContactGroupService service, ContactService contactService) {
        super(i18n, validationI18N, service);
        this.contactService = contactService;
    }

    @Override
    protected BeanFieldGroup<ContactGroup> getBinder() {
        return binder;
    }

    @Override
    protected Component createForm() {
        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);

        name = new TextField(getI18N().get(getMessages().key("name.caption")));
        name.setWidth("100%");
        layout.addComponent(name);

        members = new CustomTwinColSelect(Contact.class);
        members.setCaption(getI18N().get(getMessages().key("members.caption")));
        members.setFilterInputPrompt(getI18N().get(getMessages().key("members.search.inputPrompt")));
        members.setHeight("300px");
        members.setWidth("450px");
        members.setItems(contactService.findAll());
        members.setItemCaptionPropertyId(Contact.PROP_DISPLAY_NAME);

        layout.addComponent(members);

        binder = new BeanFieldGroup<>(ContactGroup.class);
        binder.bindMemberFields(this);

        name.focus();

        return layout;
    }
}
