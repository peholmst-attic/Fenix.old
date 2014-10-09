package net.pkhapps.fenix.core.components;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.core.boundary.CrudService;
import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.util.ButtonUtils;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.context.ApplicationContext;
import org.vaadin.spring.i18n.I18N;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * TODO Document me!
 */
public abstract class AbstractCrudView<S extends CrudService<E>, E extends AbstractEntity> extends AbstractView {

    private final ApplicationContext applicationContext;
    private final S service;
    private ViewTitleLabel title;
    private Component entityListing;
    private BeanItemContainer<E> container;
    private Optional<Button> add;
    private Optional<Button> edit;
    private Optional<Button> delete;
    private Optional<Button> refresh;

    protected AbstractCrudView(I18N i18n, ApplicationContext applicationContext, S service) {
        super(i18n);
        this.applicationContext = applicationContext;
        this.service = service;
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

        final CssLayout buttons = new CssLayout();
        buttons.addStyleName(FenixTheme.LAYOUT_COMPONENT_GROUP);
        toolbar.addComponent(buttons);
        toolbar.setComponentAlignment(buttons, Alignment.MIDDLE_RIGHT);

        add = createAddButton();
        add.ifPresent(buttons::addComponent);

        edit = createEditButton();
        edit.ifPresent(buttons::addComponent);

        delete = createDeleteButton();
        delete.ifPresent(buttons::addComponent);

        refresh = createRefreshButton();
        refresh.ifPresent(buttons::addComponent);

        container = createContainer();
        entityListing = createEntityListing(container);
        entityListing.addStyleName(FenixTheme.VIEW_CONTENT);
        entityListing.setSizeFull();
        root.addComponent(entityListing);
        root.setExpandRatio(entityListing, 1);

        selectionChanged();
    }

    /**
     * @return
     */
    protected Optional<E> getSelection() {
        return Optional.empty();
    }

    /**
     *
     */
    protected void clearSelection() {
        setSelection(Optional.empty());
    }

    /**
     *
     * @param selection
     */
    protected void setSelection(Optional<E> selection) {
    }

    /**
     *
     */
    protected void selectionChanged() {
        boolean hasSelection = getSelection().isPresent();
        edit.ifPresent(button -> button.setEnabled(hasSelection));
        delete.ifPresent(button -> button.setEnabled(hasSelection));
    }

    /**
     * @return
     */
    protected Optional<Button> createEditButton() {
        final Button button = new Button(FontAwesome.PENCIL);
        button.addClickListener(ButtonUtils.toClickListener(this::editSelected));
        button.setClickShortcut(ShortcutAction.KeyCode.F2);
        button.setDescription(getI18N().get(getMessages().key("edit.description")));
        return Optional.of(button);
    }

    /**
     * @return
     */
    protected Optional<Button> createDeleteButton() {
        final Button button = new Button(FontAwesome.TIMES);
        button.addClickListener(ButtonUtils.toClickListener(this::deleteSelected));
        button.setClickShortcut(ShortcutAction.KeyCode.DELETE);
        button.setDescription(getI18N().get(getMessages().key("delete.description")));
        return Optional.of(button);
    }

    /**
     * @return
     */
    protected Optional<Button> createAddButton() {
        final Button button = new Button(FontAwesome.PLUS);
        button.addClickListener(ButtonUtils.toClickListener(this::add));
        button.setDescription(getI18N().get(getMessages().key("add.description")));
        return Optional.of(button);
    }

    /**
     * @return
     */
    protected Optional<Button> createRefreshButton() {
        final Button button = new Button(FontAwesome.REFRESH);
        button.addClickListener(ButtonUtils.toClickListener(this::refresh));
        button.setClickShortcut(ShortcutAction.KeyCode.F5);
        button.setDescription(getI18N().get(getMessages().key("refresh.description")));
        return Optional.of(button);
    }

    /**
     *
     */
    protected void refresh() {
        Set<E> toDelete = new HashSet<>(container.getItemIds());
        service.findAll().forEach(entity -> {
            if (!toDelete.remove(entity)) {
                container.addBean(entity);
            }
        });
        toDelete.forEach(container::removeItem);
        sortEntityListing();
        clearSelection();
    }

    /**
     *
     */
    protected void add() {
        AbstractEntityWindow<S, E> entityWindow = applicationContext.getBean(getEntityWindowClass());
        entityWindow.openWindow(getUI(), Optional.of(status -> {
            container.addBean(status);
            setSelection(Optional.of(status));
            sortEntityListing();
        }), createEntity());
    }

    /**
     * @param entity
     */
    protected void delete(E entity) {
        confirmDelete(entity, status -> {
            if (status) {
                service.delete(entity);
                container.removeItem(entity);
                clearSelection();
            }
        });
    }

    /**
     * @param callback
     */
    protected void confirmDelete(E entity, AbstractWindow.Callback<Boolean> callback) {
        ConfirmationWindow confirmationWindow = createConfirmDeleteWindow(entity);
        confirmationWindow.openWindow(getUI(), Optional.of(callback));
    }

    /**
     * @return
     */
    protected ConfirmationWindow createConfirmDeleteWindow(E entity) {
        ConfirmationWindow window = new ConfirmationWindow(getI18N());
        window.setMessage(getI18N().get(getMessages().key("confirmDelete.message")));
        return window;
    }


    /**
     *
     */
    protected void deleteSelected() {
        getSelection().ifPresent(this::delete);
    }

    /**
     * @param entity
     */
    protected void edit(E entity) {
        AbstractEntityWindow<S, E> entityWindow = applicationContext.getBean(getEntityWindowClass());
        entityWindow.openWindow(getUI(), Optional.of(status -> {
            container.removeItem(entity); // Remove old entity
            container.addBean(status); // Add new entity
            sortEntityListing();
            setSelection(Optional.of(status));
        }), entity);
    }

    /**
     *
     */
    protected void editSelected() {
        getSelection().ifPresent(this::edit);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        refresh();
    }

    /**
     * @return
     */
    protected BeanItemContainer<E> createContainer() {
        return new BeanItemContainer<>(getEntityClass());
    }

    /**
     * @param container
     * @return
     */
    protected abstract Component createEntityListing(BeanItemContainer<E> container);

    /**
     * @return
     */
    protected abstract Class<E> getEntityClass();

    /**
     *
     */
    protected abstract void sortEntityListing();

    /**
     * @return
     */
    protected E createEntity() {
        try {
            return getEntityClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create instance of entity class", e);
        }
    }

    /**
     * @return
     */
    protected abstract Class<? extends AbstractEntityWindow<S, E>> getEntityWindowClass();

    /**
     * @return
     */
    protected ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @return
     */
    protected S getService() {
        return service;
    }
}
