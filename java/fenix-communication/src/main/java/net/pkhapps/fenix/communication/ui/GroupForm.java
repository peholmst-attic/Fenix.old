package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TwinColSelect;
import net.pkhapps.fenix.communication.boundary.ContactService;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.ContactGroup;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.AbstractForm;
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
    private TwinColSelect members; // TODO Replace with a better member selection component (custom)

    @Autowired
    GroupForm(I18N i18n, ContactService contactService) {
        super(i18n);
        this.contactService = contactService;
    }

    @Override
    protected void createFields() {
        name = new TextField(getMessage("name.caption"));
        addComponent(name);

        members = new TwinColSelect(getMessage("members.caption"));
        addComponent(members);
    }

    @Override
    public void attach() {
        super.attach();
        members.setContainerDataSource(new BeanItemContainer<>(Contact.class, contactService.findAll()));
        members.setItemCaptionPropertyId(Contact.PROP_DISPLAY_NAME);
    }

    @Override
    public Class<ContactGroup> getBeanClass() {
        return ContactGroup.class;
    }
}
