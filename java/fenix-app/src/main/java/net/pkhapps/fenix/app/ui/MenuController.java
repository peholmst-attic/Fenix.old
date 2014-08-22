package net.pkhapps.fenix.app.ui;

import com.vaadin.server.Page;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;

import java.io.Serializable;

/**
 * Created by peholmst on 2014-06-19.
 */
@VaadinComponent
@UIScope
class MenuController implements Serializable {

    void showDashboard(MenuBar.MenuItem selectedItem) {
        Notification.show("Show Dashboard is not implemented yet");
    }

    void changePassword(MenuBar.MenuItem selectedItem) {
        Notification.show("Change Password is not implemented yet");
    }

    void logout(MenuBar.MenuItem selectedItem) {
        // TODO Confirm logout?
        Page.getCurrent().setLocation("/logout");
    }
}
