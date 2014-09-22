package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.TextField;
import net.pkhapps.fenix.communication.boundary.ContactService;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactGroup;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.AbstractForm;
import net.pkhapps.fenix.core.components.CustomTwinColSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;

/**
 * Form for editing a single {@link net.pkhapps.fenix.communication.entity.ContactGroup}.
 */
@VaadinComponent
@PrototypeScope
class GroupForm extends AbstractForm<ContactGroup> {

    private final ContactService contactService;
    @PropertyId("name")
    private TextField name;
    @PropertyId("members")
    private CustomTwinColSelect<Contact> members;

    @Autowired
    GroupForm(I18N i18n, ContactService contactService) {
        super(i18n);
        this.contactService = contactService;
    }

    @Override
    protected void createFields() {
        name = new TextField(getMessage("name.caption"));
        name.setWidth("100%");
        addComponent(name);

        members = new CustomTwinColSelect(CustomTwinColSelect.Direction.VERTICAL, Contact.class);
        members.setCaption(getMessage("members.caption"));
        members.setFilterInputPrompt(getMessage("members.search.inputPrompt"));
        members.setHeight("400px");
        addComponent(members);
    }

    @Override
    public void attach() {
        super.attach();
        members.setItems(contactService.findAll());
        members.setItemCaptionPropertyId(Contact.PROP_DISPLAY_NAME);
    }

    @Override
    public Class<ContactGroup> getBeanClass() {
        return ContactGroup.class;
    }
}
