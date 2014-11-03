package net.pkhapps.fenix.app.ui;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.core.components.AbstractWindow;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.vaadin.spring.PrototypeScope;
import org.vaadin.spring.VaadinComponent;
import org.vaadin.spring.i18n.I18N;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * About box for showing information about the application.
 */
@VaadinComponent
@PrototypeScope
class AboutBox extends AbstractWindow<Object> {

    private final Environment environment;
    private Label programName;
    private Label version;
    private Label copyright;
    private Label license;

    @Autowired
    AboutBox(I18N i18n, Environment environment) {
        super(i18n);

        this.environment = environment;
    }

    @PostConstruct
    protected void init() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeUndefined();
        layout.setSpacing(true);
        layout.setMargin(true);

        programName = new Label(getI18N().get(getMessages().key("programName")));
        programName.addStyleName(FenixTheme.LABEL_H2);
        layout.addComponent(programName);

        version = new Label(getI18N().get(getMessages().key("version"), environment.getProperty("info.build.version")));
        layout.addComponent(version);

        copyright = new Label(getI18N().get(getMessages().key("copyright")));
        layout.addComponent(copyright);

        license = new Label(getI18N().get(getMessages().key("license")));
        license.addStyleName(FenixTheme.LABEL_SMALL);
        license.setContentMode(ContentMode.HTML);
        layout.addComponent(license);

        setContent(layout);
        setSizeUndefined();
        setResizable(false);
        center();
    }

    public void openWindow(UI ui) {
        super.openWindow(ui, Optional.empty());
    }
}
