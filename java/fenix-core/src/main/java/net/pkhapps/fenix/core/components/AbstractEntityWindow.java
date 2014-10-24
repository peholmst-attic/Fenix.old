package net.pkhapps.fenix.core.components;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.UserError;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.core.boundary.CrudService;
import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.util.ButtonUtils;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import net.pkhapps.fenix.core.validation.ValidationI18N;
import net.pkhapps.fenix.theme.FenixTheme;
import org.vaadin.spring.i18n.I18N;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import java.util.Optional;
import java.util.Set;

/**
 * Base class for windows that are used to edit entities.
 */
public abstract class AbstractEntityWindow<S extends CrudService<E>, E extends AbstractEntity>
        extends AbstractWindow<E> {

    private final S service;
    private final ValidationI18N validationI18N;

    private Component header;
    private Button saveAndClose;
    private Button discardAndClose;
    private VerticalLayout validationErrors;

    protected AbstractEntityWindow(I18N i18n, ValidationI18N validationI18N, S service) {
        super(i18n);
        this.service = service;
        this.validationI18N = validationI18N;
    }

    /**
     * @return
     */
    protected abstract BeanFieldGroup<E> getBinder();

    /**
     *
     */
    @PostConstruct
    protected void init() {
        final VerticalLayout content = new VerticalLayout();
        setContent(content);
        content.setMargin(true);
        content.setSpacing(true);
        content.addComponent(header = createHeader());
        Component form = createForm();
        content.addComponent(form);
        content.setExpandRatio(form, 1);
        validationErrors = new VerticalLayout();
        validationErrors.setSpacing(true);
        validationErrors.setVisible(false);
        content.addComponent(validationErrors);
        content.addComponent(createFooter());
        center();
        setModal(true);
        setResizable(false);
    }

    /**
     * @return
     */
    protected Component createHeader() {
        Label label = new Label();
        label.addStyleName(FenixTheme.LABEL_H2);
        return label;
    }

    /**
     *
     * @param creatingNew
     */
    protected void setCreatingNew(boolean creatingNew) {
        if (header instanceof Label) {
            final Label label = (Label) header;
            if (creatingNew) {
                label.setValue(getI18N().get(getMessages().key("title.new")));
            } else {
                label.setValue(getI18N().get(getMessages().key("title.edit")));
            }
        }
    }

    /**
     * @return
     */
    protected abstract Component createForm();

    /**
     * @return
     */
    protected Component createFooter() {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setWidth("100%");

        saveAndClose = createSaveAndCloseButton();
        saveAndClose.addClickListener(ButtonUtils.toClickListener(this::saveAndClose));
        layout.addComponent(saveAndClose);
        layout.setComponentAlignment(saveAndClose, Alignment.MIDDLE_RIGHT);
        layout.setExpandRatio(saveAndClose, 1);

        discardAndClose = createDiscardAndCloseButton();
        discardAndClose.addClickListener(ButtonUtils.toClickListener(this::discardAndClose));
        layout.addComponent(discardAndClose);
        layout.setComponentAlignment(discardAndClose, Alignment.MIDDLE_RIGHT);

        return layout;
    }

    /**
     */
    protected void saveAndClose() {
        try {
            getBinder().commit();
            E entity = getBinder().getItemDataSource().getBean();
            entity = service.save(entity);
            closeWindow(entity);
        } catch (FieldGroup.CommitException ex) {
            // Let the field group handle it
        } catch (ValidationFailedException ex) {
            showValidationErrors(ex.getConstraintViolations());
        }
    }

    /**
     * @param errors
     */
    protected void showValidationErrors(Set<ConstraintViolation<?>> errors) {
        getBinder().getFields().forEach(field -> ((AbstractComponent) field).setComponentError(null));
        validationErrors.removeAllComponents();
        validationErrors.setVisible(false);
        errors.forEach(this::showConstraintViolation);
    }

    /**
     * @param violation
     */
    protected void showConstraintViolation(ConstraintViolation<?> violation) {
        Field<?> field = getBinder().getField(violation.getPropertyPath().toString());
        if (field != null) {
            ((AbstractComponent) field).setComponentError(new UserError(validationI18N.getMessage(violation)));
        } else {
            Label label = new Label(validationI18N.getMessage(violation));
            label.addStyleName(FenixTheme.LABEL_FAILURE);
            validationErrors.addComponent(label);
            validationErrors.setVisible(true);
        }
    }

    /**
     */
    protected void discardAndClose() {
        close();
    }

    /**
     * @return
     */
    protected Button createSaveAndCloseButton() {
        Button button = new PrimaryButton(getI18N().get(getMessages().key("saveAndClose.caption")));
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        return button;
    }

    /**
     * @return
     */
    protected Button createDiscardAndCloseButton() {
        Button button = new Button(getI18N().get(getMessages().key("discardAndClose.caption")));
        button.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
        return button;
    }

    /**
     * @return
     */
    protected ValidationI18N getValidationI18N() {
        return validationI18N;
    }

    /**
     * @param callback
     * @param entity
     */
    @SuppressWarnings("unchecked")
    public void openWindow(Optional<Callback<E>> callback, E entity) {
        super.openWindow(callback);
        getBinder().setItemDataSource((E) entity.copy());
        setCreatingNew(entity.isNew());
    }

    /**
     * @param ui
     * @param callback
     * @param entity
     */
    @SuppressWarnings("unchecked")
    public void openWindow(UI ui, Optional<Callback<E>> callback, E entity) {
        super.openWindow(ui, callback);
        getBinder().setItemDataSource((E) entity.copy());
        setCreatingNew(entity.isNew());
    }
}
