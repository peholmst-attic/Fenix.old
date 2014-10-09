package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import net.pkhapps.fenix.communication.boundary.ContactGroupService;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import net.pkhapps.fenix.communication.entity.ContactGroup;
import net.pkhapps.fenix.core.components.AbstractCrudView;
import net.pkhapps.fenix.core.components.AbstractEntityWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.vaadin.spring.PrototypeScope;
import org.vaadin.spring.i18n.I18N;
import org.vaadin.spring.navigator.VaadinView;
import org.vaadin.spring.stuff.sidebar.FontAwesomeIcon;
import org.vaadin.spring.stuff.sidebar.SideBarItem;

/**
 * View for managing contact groups.
 */
@VaadinView(name = GroupsView.VIEW_NAME)
@SideBarItem(sectionId = CommunicationModule.SECTION_ID, captionCode = "net.pkhapps.fenix.communication.sidebar.groups.caption", order = 30)
@FontAwesomeIcon(FontAwesome.USERS)
@PrototypeScope
class GroupsView extends AbstractCrudView<ContactGroupService, ContactGroup> {

    public static final String VIEW_NAME = "communication/groups";

    @Autowired
    GroupsView(I18N i18n, ApplicationContext applicationContext, ContactGroupService service) {
        super(i18n, applicationContext, service);
    }

    @Override
    protected Component createEntityListing(BeanItemContainer<ContactGroup> container) {
        return new CssLayout();
    }

    @Override
    protected Class<ContactGroup> getEntityClass() {
        return ContactGroup.class;
    }

    @Override
    protected void sortEntityListing() {
    }

    @Override
    protected Class<? extends AbstractEntityWindow<ContactGroupService, ContactGroup>> getEntityWindowClass() {
        return EditGroupWindow.class;
    }
}
