package net.pkhapps.fenix.core.ui.management;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.core.entity.Organization;
import net.pkhapps.fenix.core.entity.OrganizationRepository;
import net.pkhapps.fenix.core.entity.SysAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.vaadin.maddon.fields.MTable;
import org.vaadin.spring.navigator.VaadinView;

import javax.annotation.PostConstruct;

import static net.pkhapps.fenix.core.ui.builders.CommonButtons.addButton;
import static net.pkhapps.fenix.core.ui.builders.CommonButtons.refreshButton;
import static net.pkhapps.fenix.core.ui.builders.TitlebarBuilder.titlebar;
import static net.pkhapps.fenix.core.ui.builders.ToolbarBuilder.toolbar;

/**
 * @author petter@vaadin.com
 */
@VaadinView(name = "organizations")
@Secured(SysAdmin.ROLE_NAME)
public class OrganizationsView extends VerticalLayout implements View {

    @Autowired
    OrganizationRepository repository;

    private MTable<Organization> organizationTable;
    private Button refresh;
    private Button add;
    private Button goTo;

    @PostConstruct
    void init() {
        setSizeFull();
        setSpacing(true);
        setMargin(true);

        addComponent(titlebar()
                .withTitle("Organizations")
                .withRightComponents(add = addButton(this::onAdd))
                .build());

        organizationTable = new MTable<>();
        organizationTable.setSizeFull();
        addComponent(organizationTable);
        setExpandRatio(organizationTable, 1f);

        addComponent(toolbar()
                .withLeftComponents(refresh = refreshButton(this::onRefresh))
                .build());
    }

    private void onRefresh(Button.ClickEvent clickEvent) {

    }

    private void onAdd(Button.ClickEvent clickEvent) {

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }
}
