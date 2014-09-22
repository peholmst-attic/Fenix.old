package net.pkhapps.fenix.app.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import net.pkhapps.fenix.core.security.SessionInfo;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;

import javax.annotation.PostConstruct;

/**
 * Header component for the Fenix UI.
 */
@VaadinComponent
@UIScope
class Header extends HorizontalLayout {

    @Autowired
    I18N i18n;

    @Autowired
    MenuController menuController;

    @Autowired
    SessionInfo sessionInfo;

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

        final MenuBar menuBar = new MenuBar();
        menuBar.addStyleName(FenixTheme.MENUBAR_SMALL);
        menuBar.addStyleName(FenixTheme.HEADER_MENU_BAR);
        addComponent(menuBar);
        setComponentAlignment(menuBar, Alignment.MIDDLE_RIGHT);
        setExpandRatio(menuBar, 1f);
        addMenuItems(menuBar);
    }

    private void addMenuItems(MenuBar menuBar) {
        final MenuBar.MenuItem dashboard = menuBar.addItem(i18n.get("header.menu.dashboard.caption"), menuController::showDashboard);
        dashboard.setIcon(FontAwesome.TACHOMETER);
        dashboard.setDescription(i18n.get("header.menu.dashboard.description"));

        final MenuBar.MenuItem user = menuBar.addItem(i18n.get("header.menu.user.caption",
                sessionInfo.getCurrentUserDetails().getDisplayName()), null);
        user.setIcon(FontAwesome.USER);
        user.addItem(i18n.get("header.menu.user.changePassword.caption"), menuController::changePassword);
        user.addItem(i18n.get("header.menu.user.logout.caption"), menuController::logout);
    }
}
