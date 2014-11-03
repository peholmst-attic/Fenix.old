package net.pkhapps.fenix.app.ui;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.core.components.AbstractView;
import net.pkhapps.fenix.core.components.ViewTitleLabel;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.UIScope;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.navigator.VaadinView;

import javax.annotation.PostConstruct;

/**
 * TODO Implement me!
 */
@UIScope
@VaadinView(name = HomeView.VIEW_NAME)
class HomeView extends AbstractView {

    public static final String VIEW_NAME = "";

    private ViewTitleLabel title;

    private Panel content;

    @Autowired
    HomeView(I18N i18n) {
        super(i18n);
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

        content = new Panel();
        content.setSizeFull();
        content.addStyleName(FenixTheme.VIEW_CONTENT);
        content.addStyleName(FenixTheme.PANEL_BORDERLESS);
        root.addComponent(content);
        root.setExpandRatio(content, 1);
    }
}
