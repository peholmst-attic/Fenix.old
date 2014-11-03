package net.pkhapps.fenix.app.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.core.components.AbstractWindow;
import net.pkhapps.fenix.core.components.PrimaryButton;
import net.pkhapps.fenix.core.security.SessionInfo;
import net.pkhapps.fenix.core.util.ButtonUtils;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.vaadin.spring.PrototypeScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Window for changing the current user's password.
 */
@VaadinComponent
@PrototypeScope
class ChangePasswordWindow extends AbstractWindow<Object> {

    private final SessionInfo sessionInfo;

    private PasswordField oldPassword;
    private PasswordField newPassword;
    private PasswordField confirmPassword;
    private PrimaryButton changePassword;
    private Label newPasswordsDontMatch;

    @Autowired
    ChangePasswordWindow(I18N i18n, SessionInfo sessionInfo) {
        super(i18n);
        this.sessionInfo = sessionInfo;
    }

    @PostConstruct
    protected void init() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeUndefined();
        layout.setSpacing(true);
        layout.setMargin(true);

        Label title = new Label(getI18N().get(getMessages().key("title")));
        title.addStyleName(FenixTheme.LABEL_H2);
        layout.addComponent(title);

        oldPassword = new PasswordField(getI18N().get(getMessages().key("oldPassword.caption")));
        oldPassword.setWidth(20, Unit.EM);
        layout.addComponent(oldPassword);

        newPassword = new PasswordField(getI18N().get(getMessages().key("newPassword.caption")));
        newPassword.setWidth(20, Unit.EM);
        newPassword.setImmediate(true);
        newPassword.addTextChangeListener(event -> checkNewPasswords(event.getText(), confirmPassword.getValue()));
        layout.addComponent(newPassword);

        confirmPassword = new PasswordField(getI18N().get(getMessages().key("confirmPassword.caption")));
        confirmPassword.setWidth(20, Unit.EM);
        confirmPassword.setImmediate(true);
        confirmPassword.addTextChangeListener(event -> checkNewPasswords(newPassword.getValue(), event.getText()));
        layout.addComponent(confirmPassword);

        newPasswordsDontMatch = new Label(getI18N().get(getMessages().key("newPasswordsDontMatch")));
        newPasswordsDontMatch.addStyleName(FenixTheme.LABEL_FAILURE);
        newPasswordsDontMatch.setVisible(false);
        layout.addComponent(newPasswordsDontMatch);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSpacing(true);
        layout.addComponent(buttons);
        layout.setComponentAlignment(buttons, Alignment.BOTTOM_RIGHT);

        changePassword = new PrimaryButton(getI18N().get(getMessages().key("changePassword.caption")), ButtonUtils.toClickListener(this::changePassword));
        changePassword.setEnabled(false);
        changePassword.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        buttons.addComponent(changePassword);

        Button cancel = new Button(getI18N().get(getMessages().key("cancel.caption")), ButtonUtils.toClickListener(this::cancel));
        cancel.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        buttons.addComponent(cancel);

        setContent(layout);
        setSizeUndefined();
        setResizable(false);
        center();
        oldPassword.focus();
    }

    private void checkNewPasswords(String newPassword, String confirmPassword) {
        boolean passwordsMatch = newPassword.length() > 0 && newPassword.equals(confirmPassword);
        newPasswordsDontMatch.setVisible(!passwordsMatch);
        changePassword.setEnabled(passwordsMatch);
    }

    private void changePassword() {
        try {
            sessionInfo.changePasswordOfCurrentUser(oldPassword.getValue(), newPassword.getValue());
            Notification.show(getI18N().get(getMessages().key("passwordChanged")));
            close();
        } catch (BadCredentialsException ex) {
            Notification.show(getI18N().get(getMessages().key("oldPasswordInvalid")), Notification.Type.WARNING_MESSAGE);
        }
    }

    private void cancel() {
        close();
    }

    public void openWindow(UI ui) {
        super.openWindow(ui, Optional.empty());
    }

}
