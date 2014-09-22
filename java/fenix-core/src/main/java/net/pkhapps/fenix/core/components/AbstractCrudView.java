package net.pkhapps.fenix.core.components;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import net.pkhapps.fenix.theme.FenixTheme;
import org.springframework.context.ApplicationContext;
import org.vaadin.spring.i18n.I18N;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static net.pkhapps.fenix.core.util.ButtonUtils.toClickListener;

/**
 * TODO Document me!
 */
public abstract class AbstractCrudView<E extends AbstractEntity,
        V extends AbstractCrudPresenter.ViewDelegate<E>,
        P extends AbstractCrudPresenter<E, ?, V>> extends AbstractView<V, P>
        implements AbstractCrudPresenter.ViewDelegate<E> {

    private final ApplicationContext applicationContext;
    private ViewTitleLabel title;
    private AbstractForm<E> form;
    private Table table;
    private BeanItemContainer<E> container;
    private Optional<Button> add;
    private Optional<Button> edit;
    private Optional<Button> save;
    private Optional<Button> cancel;
    private Optional<Button> delete;
    private Label noSelection;

    protected AbstractCrudView(P presenter, I18N i18n, ApplicationContext applicationContext) {
        super(presenter, i18n);
        this.applicationContext = applicationContext;
    }

    @Override
    protected void doInit() {
        setSizeFull();
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        setCompositionRoot(root);

        title = new ViewTitleLabel(getI18N().get(getMessages().key("title")));
        root.addComponent(title);

        container = createContainer();
        table = new Table();
        table.setContainerDataSource(container);
        table.setSizeFull();
        table.addStyleName(FenixTheme.TABLE_BORDERLESS);
        table.setSelectable(true);
        table.addValueChangeListener(this::select);
        setUpTable(table);

        final VerticalLayout editorLayout = new VerticalLayout();
        editorLayout.setSizeFull();
        editorLayout.setMargin(true);

        noSelection = new Label(getI18N().get(getMessages().key("noSelection")));
        editorLayout.addComponent(noSelection);

        form = createForm();
        form.setSizeFull();
        editorLayout.addComponent(form);
        editorLayout.setExpandRatio(form, 1);
        form.setVisible(false);

        final HorizontalLayout editorButtons = new HorizontalLayout();
        editorButtons.setSpacing(true);
        editorButtons.setWidth("100%");
        editorLayout.addComponent(editorButtons);
        editorLayout.setComponentAlignment(editorButtons, Alignment.BOTTOM_LEFT);

        add = createButton(AbstractCrudPresenter.DefaultViewButton.ADD);
        add.ifPresent(editorButtons::addComponent);

        final Label splitter = new Label();
        editorButtons.addComponent(splitter);
        editorButtons.setExpandRatio(splitter, 1);

        edit = createButton(AbstractCrudPresenter.DefaultViewButton.EDIT);
        edit.ifPresent(editorButtons::addComponent);
        edit.ifPresent(btn -> editorButtons.setComponentAlignment(btn, Alignment.BOTTOM_RIGHT));

        save = createButton(AbstractCrudPresenter.DefaultViewButton.SAVE);
        save.ifPresent(editorButtons::addComponent);
        save.ifPresent(btn -> editorButtons.setComponentAlignment(btn, Alignment.BOTTOM_RIGHT));

        cancel = createButton(AbstractCrudPresenter.DefaultViewButton.CANCEL);
        cancel.ifPresent(editorButtons::addComponent);
        cancel.ifPresent(btn -> editorButtons.setComponentAlignment(btn, Alignment.BOTTOM_RIGHT));

        delete = createButton(AbstractCrudPresenter.DefaultViewButton.DELETE);
        delete.ifPresent(editorButtons::addComponent);
        delete.ifPresent(btn -> editorButtons.setComponentAlignment(btn, Alignment.BOTTOM_RIGHT));

        final Component tableFormLayout = createTableFormLayout(table, editorLayout);
        tableFormLayout.setSizeFull();
        tableFormLayout.addStyleName(FenixTheme.VIEW_CONTENT);
        root.addComponent(tableFormLayout);
        root.setExpandRatio(tableFormLayout, 1);
    }

    /**
     * @return
     */
    protected BeanItemContainer<E> createContainer() {
        return new BeanItemContainer<>(getEntityClass());
    }

    /**
     * @param table
     */
    protected abstract void setUpTable(Table table);

    /**
     * @return
     */
    protected AbstractForm<E> createForm() {
        return applicationContext.getBean(getFormClass());
    }

    /**
     * @return
     */
    protected abstract Class<? extends AbstractForm<E>> getFormClass();

    /**
     * @return
     */
    protected abstract Class<E> getEntityClass();

    /**
     * @param table
     * @param editor
     * @return
     */
    protected Component createTableFormLayout(Component table, Component editor) {
        final HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        splitPanel.setSplitPosition(450, Unit.PIXELS, true);
        splitPanel.setFirstComponent(table);
        splitPanel.setSecondComponent(editor);
        return splitPanel;
    }

    /**
     * @param event
     */
    protected void select(Property.ValueChangeEvent event) {
        final E contact = getEntityClass().cast(table.getValue());
        getPresenter().select(Optional.ofNullable(contact));
    }

    @Override
    public void showForm(E entity) {
        form.setBean(entity);
        form.setVisible(true);
        noSelection.setVisible(false);
    }

    @Override
    public Optional<E> commitForm() {
        return form.commit();
    }

    @Override
    public void showValidationErrors(ValidationFailedException validationErrors) {
        form.showValidationErrors(validationErrors);
    }

    @Override
    public void discardForm() {
        form.discard();
    }

    @Override
    public void hideForm() {
        form.setVisible(false);
        noSelection.setVisible(true);
    }

    @Override
    public void setEntities(List<E> entities) {
        Set<E> itemsToDelete = new HashSet<>(container.getItemIds());
        entities.stream().filter(entity -> !itemsToDelete.remove(entity)).forEach(container::addBean);
        itemsToDelete.forEach(container::removeItem);
    }

    @Override
    public void setSelection(Optional<E> selection) {
        table.setValue(selection.orElse(null));
    }

    @Override
    public void setFormEditMode(boolean editMode) {
        form.setReadOnly(!editMode);
        if (editMode) {
            form.focus();
        }
    }

    /**
     * @param viewButton
     * @return
     */
    protected Optional<Button> createButton(AbstractPresenter.ViewButton viewButton) {
        Button btn = null;
        final P presenter = getPresenter();
        if (viewButton == AbstractCrudPresenter.DefaultViewButton.ADD) {
            btn = new Button(getI18N().get(getMessages().key("add.caption")), toClickListener(presenter::add));
            btn.setDescription(getI18N().get(getMessages().key("add.description")));
        } else if (viewButton == AbstractCrudPresenter.DefaultViewButton.EDIT) {
            btn = new PrimaryButton(getI18N().get(getMessages().key("edit.caption")), toClickListener(presenter::edit));
            btn.setDescription(getI18N().get(getMessages().key("edit.description")));
        } else if (viewButton == AbstractCrudPresenter.DefaultViewButton.SAVE) {
            btn = new FriendlyButton(getI18N().get(getMessages().key("save.caption")), toClickListener(presenter::save));
            btn.setDescription(getI18N().get(getMessages().key("save.description")));
        } else if (viewButton == AbstractCrudPresenter.DefaultViewButton.CANCEL) {
            btn = new Button(getI18N().get(getMessages().key("cancel.caption")), toClickListener(presenter::cancel));
            btn.setDescription(getI18N().get(getMessages().key("cancel.description")));
        } else if (viewButton == AbstractCrudPresenter.DefaultViewButton.DELETE) {
            btn = new DangerousButton(getI18N().get(getMessages().key("delete.caption")), toClickListener(presenter::delete));
            btn.setDescription(getI18N().get(getMessages().key("delete.description")));
        }
        if (btn != null) {
            btn.setVisible(false);
            return Optional.of(btn);
        } else {
            return Optional.empty();
        }
    }

    @Override
    protected Optional<Button> getButton(AbstractPresenter.ViewButton viewButton) {
        if (viewButton == AbstractCrudPresenter.DefaultViewButton.ADD) {
            return add;
        } else if (viewButton == AbstractCrudPresenter.DefaultViewButton.EDIT) {
            return edit;
        } else if (viewButton == AbstractCrudPresenter.DefaultViewButton.SAVE) {
            return save;
        } else if (viewButton == AbstractCrudPresenter.DefaultViewButton.CANCEL) {
            return cancel;
        } else if (viewButton == AbstractCrudPresenter.DefaultViewButton.DELETE) {
            return delete;
        }
        return super.getButton(viewButton);
    }

    @Override
    protected Optional<ClickShortcut> getButtonClickShortcut(AbstractPresenter.ViewButton viewButton) {
        if (viewButton == AbstractCrudPresenter.DefaultViewButton.CANCEL) {
            return Optional.of(new ClickShortcut(ShortcutAction.KeyCode.ESCAPE));
        } else if (viewButton == AbstractCrudPresenter.DefaultViewButton.EDIT) {
            return Optional.of(new ClickShortcut(ShortcutAction.KeyCode.ENTER));
        } else if (viewButton == AbstractCrudPresenter.DefaultViewButton.SAVE) {
            return Optional.of(new ClickShortcut(ShortcutAction.KeyCode.ENTER));
        }
        return super.getButtonClickShortcut(viewButton);
    }
}
