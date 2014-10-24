package net.pkhapps.fenix.app.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.stuff.sidebar.SideBar;

import javax.annotation.PostConstruct;

/**
 * Created by peholmst on 2014-06-19.
 */
@VaadinComponent
@UIScope
class FenixUIContent extends VerticalLayout implements ViewDisplay {

    @Autowired
    Header header;

    @Autowired
    SideBar sideBar;

    private HorizontalSplitPanel splitPanel;

    @PostConstruct
    void init() {
        setSizeFull();
        addComponent(header);

        splitPanel = new HorizontalSplitPanel();
        splitPanel.setSizeFull();
        splitPanel.addStyleName(FenixTheme.VIEW_SPLIT_PANEL);
        addComponent(splitPanel);
        setExpandRatio(splitPanel, 1f);

        splitPanel.setFirstComponent(sideBar);
        splitPanel.setSplitPosition(150, Unit.PIXELS);
    }

    @Override
    public void showView(View view) {
        splitPanel.setSecondComponent((Component) view);
    }
}
