package net.pkhapps.fenix.app.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.VaadinUI;
import org.vaadin.spring.navigator.SpringViewProvider;

/**
 * Created by peholmst on 2014-06-19.
 */
@VaadinUI
@Theme(FenixTheme.THEME_NAME)
public class FenixUI extends UI {

    @Autowired
    FenixUIContent content;

    @Autowired
    SpringViewProvider viewProvider;

    @Autowired
    ErrorView errorView;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, (ViewDisplay) content);
        navigator.addProvider(viewProvider);
        navigator.setErrorView(errorView);

        setContent(content);
        setPollInterval(300);
    }
}
