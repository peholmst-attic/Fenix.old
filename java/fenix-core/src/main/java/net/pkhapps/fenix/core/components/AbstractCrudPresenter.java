package net.pkhapps.fenix.core.components;

import net.pkhapps.fenix.core.boundary.CrudService;
import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.validation.ValidationFailedException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Base class for CRUD presenters.
 */
public abstract class AbstractCrudPresenter<E extends AbstractEntity,
        S extends CrudService<E>,
        V extends AbstractCrudPresenter.ViewDelegate<E>> extends AbstractPresenter<V> {

    private final S service;
    private List<E> entities = Collections.emptyList();
    private Optional<E> selection = Optional.empty();
    private boolean editing;

    protected AbstractCrudPresenter(S service) {
        this.service = service;
    }

    protected S getService() {
        return service;
    }

    @Override
    protected void viewDelegateEntered() {
        refresh();
    }

    public void add() {
        logger.trace("Add");
        selection = Optional.empty();
        editing = true;
        getViewDelegate().setSelection(selection);
        getViewDelegate().showForm(newEntity());
        getViewDelegate().setFormEditMode(true);
        updateButtonStates();
    }

    /**
     * Creates and returns a new, empty entity.
     */
    protected abstract E newEntity();

    public void edit() {
        if (selection.isPresent()) {
            logger.trace("Edit: {}", selection.get());
            editing = true;
            getViewDelegate().setFormEditMode(true);
            updateButtonStates();
        }
    }

    public void delete() {
        if (selection.isPresent()) {
            E entityToDelete = selection.get();
            logger.trace("Delete: {}", entityToDelete);
            service.delete(entityToDelete);
            entities.remove(entityToDelete);
            getViewDelegate().setEntities(entities);
            select(Optional.empty());
        }
    }

    public void save() {
        Optional<E> committedEntity = getViewDelegate().commitForm();
        committedEntity.ifPresent(this::doSave);
    }

    protected void doSave(E entity) {
        logger.trace("Save: {}", entity);
        editing = false;
        boolean isNew = entity.isNew();
        try {
            entities.remove(entity);
            entity = service.save(entity);
            entities.add(entity);
            getViewDelegate().setEntities(entities);
            doSelect(Optional.of(entity));
        } catch (ValidationFailedException ex) {
            getViewDelegate().showValidationErrors(ex);
        }
    }

    public void cancel() {
        logger.trace("Cancel");
        editing = false;
        getViewDelegate().discardForm();
        getViewDelegate().setFormEditMode(false);
        if (!selection.isPresent()) {
            getViewDelegate().hideForm();
        }
        updateButtonStates();
    }

    public void refresh() {
        if (!editing) {
            logger.trace("Refresh");
            entities = service.findAll();
            getViewDelegate().setEntities(entities);
            select(Optional.empty());
        }
    }

    public void select(Optional<E> selection) {
        if (!this.selection.equals(selection)) {
            doSelect(selection);
        }
    }

    protected void doSelect(Optional<E> selection) {
        logger.trace("Select: {}", selection);
        this.selection = selection;
        getViewDelegate().setSelection(selection);
        if (selection.isPresent()) {
            getViewDelegate().showForm(selection.get());
            getViewDelegate().setFormEditMode(false);
        } else {
            getViewDelegate().hideForm();
        }
        updateButtonStates();
    }

    @Override
    protected void updateButtonStates() {
        logger.trace("Update button states (editing = {}, selection = {})", editing, selection);
        getViewDelegate().setButtonsVisible(editing, DefaultViewButton.SAVE, DefaultViewButton.CANCEL);
        getViewDelegate().setButtonsVisible(selection.isPresent() && !editing, DefaultViewButton.EDIT, DefaultViewButton.DELETE);
        getViewDelegate().setButtonsVisible(!editing, DefaultViewButton.ADD);
    }

    /**
     * Default buttons that are always present in a CRUD view. Additional buttons
     * may be specified in a separate enumeration.
     */
    public enum DefaultViewButton implements ViewButton {
        REFRESH, ADD, EDIT, SAVE, CANCEL, DELETE
    }

    public interface ViewDelegate<E extends AbstractEntity> extends AbstractPresenter.ViewDelegate {

        /**
         * Shows the form with the specified entity. By default, the form is hidden.
         */
        void showForm(E entity);

        /**
         * Commits the form and returns the updated entity. If the form cannot be committed (e.g. due to
         * validation errors), an empty Optional is returned.
         */
        Optional<E> commitForm();

        /**
         * Shows the validation errors to the user.
         */
        void showValidationErrors(ValidationFailedException validationErrors);

        /**
         * Discards the form.
         */
        void discardForm();

        /**
         * Hides the form.
         */
        void hideForm();

        /**
         * Shows the specified entities in the table.
         */
        void setEntities(List<E> entities);

        /**
         * Sets the currently selected entity in the table (can also be empty, which clears the selection).
         */
        void setSelection(Optional<E> selection);

        /**
         * Turns edit mode on or off.
         */
        void setFormEditMode(boolean editMode);
    }
}
