package net.pkhapps.fenix.core.sms.ui;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.core.admin.AdminSection;
import net.pkhapps.fenix.core.components.AbstractView;
import net.pkhapps.fenix.core.components.PrimaryButton;
import net.pkhapps.fenix.core.components.ViewTitleLabel;
import net.pkhapps.fenix.core.sms.boundary.SmsPropertiesService;
import net.pkhapps.fenix.core.sms.entity.SmsProperties;
import net.pkhapps.fenix.core.util.ButtonUtils;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.PrototypeScope;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.FontAwesomeIcon;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

import javax.annotation.PostConstruct;

/**
 * View for modifying the {@link net.pkhapps.fenix.core.sms.entity.SmsProperties} of the current user's
 * fire department.
 */
@VaadinView(name = SmsPropertiesView.VIEW_NAME)
@SideBarItem(sectionId = AdminSection.SECTION_ID, captionCode = "net.pkhapps.fenix.core.admin.sidebar.smsProperties.caption", order = 20)
@FontAwesomeIcon(FontAwesome.MOBILE)
@PrototypeScope
class SmsPropertiesView extends AbstractView {

    public static final String VIEW_NAME = "admin/sms_properties";

    private final SmsPropertiesService smsPropertiesService;

    private Label title;

    private Label instructions;

    @PropertyId(SmsProperties.PROP_USER_KEY)
    private TextField userKey;

    @PropertyId(SmsProperties.PROP_PASSWORD)
    private TextField password;

    @PropertyId(SmsProperties.PROP_ORIGINATOR)
    private TextField originator;

    private Button saveChanges;

    private Button revert;

    private BeanFieldGroup<SmsProperties> binder;

    @Autowired
    SmsPropertiesView(I18N i18n, SmsPropertiesService smsPropertiesService) {
        super(i18n);
        this.smsPropertiesService = smsPropertiesService;
    }

    @PostConstruct
    protected void init() {
        setSizeFull();
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        setCompositionRoot(root);

        final HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.setWidth("100%");
        toolbar.setSpacing(true);
        toolbar.addStyleName(FenixTheme.VIEW_HEADER);
        root.addComponent(toolbar);

        title = new ViewTitleLabel(getI18N().get(getMessages().key("title")));
        toolbar.addComponent(title);
        toolbar.setExpandRatio(title, 1);

        VerticalLayout content = new VerticalLayout();
        content.setSpacing(true);
        content.setMargin(true);
        content.setSizeFull();
        content.addStyleName(FenixTheme.VIEW_CONTENT);

        instructions = new Label(getI18N().get(getMessages().key("instructions")));
        instructions.setContentMode(ContentMode.HTML);
        instructions.addStyleName(FenixTheme.LABEL_SMALL);
        content.addComponent(instructions);

        userKey = new TextField(getI18N().get(getMessages().key("userKey.caption")));
        userKey.setDescription(getI18N().get(getMessages().key("userKey.description")));
        userKey.setWidth(12, Unit.EM);
        content.addComponent(userKey);

        password = new TextField(getI18N().get(getMessages().key("password.caption")));
        password.setDescription(getI18N().get(getMessages().key("password.description")));
        password.setWidth(12, Unit.EM);
        content.addComponent(password);

        originator = new TextField(getI18N().get(getMessages().key("originator.caption")));
        originator.setDescription(getI18N().get(getMessages().key("originator.description")));
        originator.setWidth(12, Unit.EM);
        content.addComponent(originator);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        content.addComponent(buttons);
        content.setExpandRatio(buttons, 1);

        saveChanges = new PrimaryButton(getI18N().get(getMessages().key("saveChanges.caption")), ButtonUtils.toClickListener(this::saveChanges));
        saveChanges.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        buttons.addComponent(saveChanges);

        revert = new Button(getI18N().get(getMessages().key("revert.caption")), ButtonUtils.toClickListener(this::revert));
        buttons.addComponent(revert);

        root.addComponent(content);
        root.setExpandRatio(content, 1);

        binder = new BeanFieldGroup<>(SmsProperties.class);
        binder.setItemDataSource(smsPropertiesService.getSmsProperties().orElse(new SmsProperties()));
        binder.bindMemberFields(this);
    }

    private void saveChanges() {
        try {
            binder.commit();
            binder.setItemDataSource(smsPropertiesService.saveSmsProperties(binder.getItemDataSource().getBean()));
            Notification.show(getI18N().get(getMessages().key("notifications.changesSaved")));
        } catch (FieldGroup.CommitException e) {
            // Should never happen
        }
    }

    private void revert() {
        binder.discard();
    }

}
