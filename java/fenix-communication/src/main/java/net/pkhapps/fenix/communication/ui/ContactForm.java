package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.core.i18n.MessageKeyGenerator;
import net.pkhapps.fenix.theme.FenixTheme;
import org.vaadin.spring.i18n.I18N;

/**
 * Created by petterwork on 21/08/14.
 */
class ContactForm extends FormLayout {

    private final I18N i18n;
    private static final MessageKeyGenerator messages = new MessageKeyGenerator(ContactForm.class);

    private TextField firstName;
    private TextField lastName;
    private TextField singleName;
    private TextField email;
    private TextField cellPhone;

    private BeanFieldGroup<Contact.Builder> binder;

    ContactForm(I18N i18n) {
        this.i18n = i18n;

        firstName = new TextField(i18n.get(messages.key("firstName.caption")));
        addComponent(firstName);

        lastName = new TextField(i18n.get(messages.key("lastName.caption")));
        addComponent(lastName);

        singleName = new TextField(i18n.get(messages.key("singleName.caption")));
        addComponent(singleName);

        final Label singleNameDescription = new Label(i18n.get(messages.key("singleName.description")));
        singleNameDescription.addStyleName(FenixTheme.LABEL_SMALL);
        addComponent(singleNameDescription);

        email = new TextField(i18n.get(messages.key("email.caption")));
        addComponent(email);

        cellPhone = new TextField(i18n.get(messages.key("cellPhone.caption")));
        addComponent(cellPhone);
    }

}
