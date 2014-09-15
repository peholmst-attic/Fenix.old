package net.pkhapps.fenix.communication.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import net.pkhapps.fenix.communication.config.CommunicationModule;
import net.pkhapps.fenix.communication.entity.ContactGroup;
import net.pkhapps.fenix.communication.security.CommunicationAuthorities;
import net.pkhapps.fenix.core.annotations.PrototypeScope;
import net.pkhapps.fenix.core.components.AbstractCrudView;
import net.pkhapps.fenix.core.components.AbstractForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
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
@Secured({CommunicationAuthorities.EDIT_CONTACTS_AND_GROUPS, CommunicationAuthorities.VIEW_CONTACTS_AND_GROUPS})
public class GroupsView extends AbstractCrudView<ContactGroup, GroupsPresenter.ViewDelegate, GroupsPresenter> implements GroupsPresenter.ViewDelegate {

    public static final String VIEW_NAME = "communication/groups";

    @Autowired
    public GroupsView(GroupsPresenter presenter, I18N i18n, ApplicationContext applicationContext) {
        super(presenter, i18n, applicationContext);
    }

    @Override
    protected void setUpTable(Table table) {
        table.addGeneratedColumn("membersCount", (source, itemId, columnId) -> ((ContactGroup) itemId).getMembers().size());
        table.setVisibleColumns(
                ContactGroup.PROP_NAME,
                "membersCount");
        table.setColumnHeader(ContactGroup.PROP_NAME, getI18N().get(getMessages().key("table.name")));
        table.setColumnHeader("membersCount", getI18N().get(getMessages().key("table.membersCount")));
        // TODO Members converter
        table.setSortContainerPropertyId(ContactGroup.PROP_NAME);
    }

    @Override
    protected Component createTableFormLayout(Component table, Component editor) {
        final HorizontalSplitPanel splitPanel = (HorizontalSplitPanel) super.createTableFormLayout(table, editor);
        splitPanel.setSplitPosition(500, Unit.PIXELS, true);
        return splitPanel;
    }

    @Override
    protected Class<? extends AbstractForm<ContactGroup>> getFormClass() {
        return GroupForm.class;
    }

    @Override
    protected Class<ContactGroup> getEntityClass() {
        return ContactGroup.class;
    }
}
