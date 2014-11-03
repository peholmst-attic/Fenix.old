package net.pkhapps.fenix.app.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import net.pkhapps.fenix.core.components.ConfirmationWindow;
import net.pkhapps.fenix.core.security.SessionInfo;
import net.pkhapps.fenix.core.util.ButtonUtils;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Header component for the Fenix UI.
 */
@VaadinComponent
@UIScope
class Header extends HorizontalLayout {

    @Autowired
    I18N i18n;

    @Autowired
    SessionInfo sessionInfo;

    @Autowired
    ApplicationContext applicationContext;

    @PostConstruct
    void init() {
        setWidth(100, Unit.PERCENTAGE);
        addStyleName(FenixTheme.HEADER_LAYOUT);
//        addStyleName("valo-menu-title");

        final Label applicationTitle = new Label("Fenix");
        applicationTitle.addStyleName(FenixTheme.HEADER_APPLICATION_TITLE);
        applicationTitle.setSizeUndefined();
        addComponent(applicationTitle);
        setComponentAlignment(applicationTitle, Alignment.MIDDLE_LEFT);

        sessionInfo.getCurrentUserDetails().getFireDepartment().ifPresent(fireDepartment -> {
            final Label fireDepartmentName = new Label(fireDepartment.getName());
            fireDepartmentName.addStyleName(FenixTheme.HEADER_FIRE_DEPARTMENT_NAME);
            fireDepartmentName.setSizeUndefined();
            addComponent(fireDepartmentName);
            setComponentAlignment(fireDepartmentName, Alignment.MIDDLE_LEFT);
        });

        final Label userName = new Label(sessionInfo.getCurrentUserDetails().getDisplayName());
        userName.addStyleName(FenixTheme.HEADER_USER_NAME);
        userName.setSizeUndefined();
        addComponent(userName);
        setComponentAlignment(userName, Alignment.MIDDLE_RIGHT);
        setExpandRatio(userName, 1);


        final CssLayout toolbar = new CssLayout();
        toolbar.addStyleName(FenixTheme.LAYOUT_COMPONENT_GROUP);
        addComponent(toolbar);
        setComponentAlignment(toolbar, Alignment.MIDDLE_RIGHT);

        final Button home = new Button(FontAwesome.HOME);
        home.addClickListener(ButtonUtils.toClickListener(this::home));
        home.addStyleName(FenixTheme.BUTTON_FRIENDLY);
        home.setDescription(i18n.get("header.menu.home.description"));
        toolbar.addComponent(home);

        final Button changePassword = new Button(FontAwesome.KEY);
        changePassword.addClickListener(ButtonUtils.toClickListener(this::changePassword));
        changePassword.setDescription(i18n.get("header.menu.changePassword.description"));
        toolbar.addComponent(changePassword);

        final Button about = new Button(FontAwesome.INFO_CIRCLE);
        about.addClickListener(ButtonUtils.toClickListener(this::about));
        about.setDescription(i18n.get("header.menu.about.description"));
        toolbar.addComponent(about);

        final Button logout = new Button(FontAwesome.POWER_OFF);
        logout.addClickListener(ButtonUtils.toClickListener(this::logout));
        logout.setDescription(i18n.get("header.menu.logout.description"));
        toolbar.addComponent(logout);
    }

    void home() {
        getUI().getNavigator().navigateTo(HomeView.VIEW_NAME);
    }

    void logout() {
        ConfirmationWindow confirmationWindow = new ConfirmationWindow(i18n);
        confirmationWindow.setMessage(i18n.get("header.menu.logout.confirm.message"));
        confirmationWindow.setConfirmButtonCaption(i18n.get("header.menu.logout.confirm.button"));
        confirmationWindow.openWindow(getUI(), Optional.of(status -> {
            if (status) {
                Page.getCurrent().setLocation("/logout");
            }
        }));
    }

    void changePassword() {
        applicationContext.getBean(ChangePasswordWindow.class).openWindow(getUI());
    }

    void about() {
        applicationContext.getBean(AboutBox.class).openWindow(getUI());
    }

}
