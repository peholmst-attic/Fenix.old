package net.pkhapps.fenix.app.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import net.pkhapps.fenix.core.ldap.FenixLdapUserDetails;
import net.pkhapps.fenix.core.security.SessionInfo;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;

import javax.annotation.PostConstruct;

/**
 * Created by peholmst on 2014-06-19.
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

        final Label applicationTitle = new Label("Fenix");
        applicationTitle.addStyleName(FenixTheme.HEADER_APPLICATION_TITLE);
        applicationTitle.setSizeUndefined();
        addComponent(applicationTitle);
        setExpandRatio(applicationTitle, 1f);

        final MenuBar menuBar = new MenuBar();
        menuBar.addStyleName(FenixTheme.HEADER_MENU_BAR);
        addComponent(menuBar);
        setComponentAlignment(menuBar, Alignment.MIDDLE_RIGHT);
        addMenuItems(menuBar);
    }

    private void addMenuItems(MenuBar menuBar) {
        final MenuBar.MenuItem dashboard = menuBar.addItem(i18n.get("header.menu.dashboard.caption"), menuController::showDashboard);
        dashboard.setIcon(FontAwesome.TACHOMETER);
        dashboard.setDescription(i18n.get("header.menu.dashboard.description"));

        final MenuBar.MenuItem user = menuBar.addItem(i18n.get("header.menu.user.caption",
                sessionInfo.getCurrentUserDetails().getDisplayName()), null);
        user.setIcon(FontAwesome.USER);
    }
}
