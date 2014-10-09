package net.pkhapps.fenix.core.components;

import com.vaadin.navigator.ViewChangeListener;
import net.pkhapps.fenix.core.boundary.CrudService;
import net.pkhapps.fenix.core.entity.AbstractEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Base class for CRUD presenters.
 */
@Deprecated
public abstract class AbstractCrudPresenter<E extends AbstractEntity,
        S extends CrudService<E>,
        V extends AbstractCrudPresenter.ViewDelegate<E>> extends AbstractViewPresenter<V> {

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
    protected void viewDelegateEntered(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        refresh();
    }

    public void add() {
/*        logger.trace("Add");
        selection = Optional.empty();
        editing = true;
        getViewDelegate().setSelection(selection);
        getViewDelegate().showForm(newEntity());
        getViewDelegate().setFormEditMode(true);
        updateButtonStates();*/
    }

    /**
     * Creates and returns a new, empty entity.
     */
    protected abstract E newEntity();

    public void edit(E entity) {
/*        if (selection.isPresent()) {
            logger.trace("Edit: {}", selection.get());
            editing = true;
            getViewDelegate().setFormEditMode(true);
            updateButtonStates();
        }*/
    }

    public void delete(E entity) {
/*        if (selection.isPresent()) {
            E entityToDelete = selection.get();
            logger.trace("Delete: {}", entityToDelete);
            service.delete(entityToDelete);
            entities.remove(entityToDelete);
            getViewDelegate().setEntities(entities);
            select(Optional.empty());
        }*/
    }

    public void refresh() {
/*        if (!editing) {
            logger.trace("Refresh");
            entities = service.findAll();
            getViewDelegate().setEntities(entities);
            select(Optional.empty());
        }*/
    }

    public void select(Optional<E> selection) {
        if (!this.selection.equals(selection)) {
            doSelect(selection);
        }
    }

    protected void doSelect(Optional<E> selection) {
/*        logger.trace("Select: {}", selection);
        this.selection = selection;
        getViewDelegate().setSelection(selection);
        if (selection.isPresent()) {
            getViewDelegate().showForm(selection.get());
            getViewDelegate().setFormEditMode(false);
        } else {
            getViewDelegate().hideForm();
        }
        updateButtonStates();*/
    }

    @Override
    protected void updateButtonStates() {
        getViewDelegate().setButtonsVisible(true, DefaultViewButton.ADD, DefaultViewButton.EDIT, DefaultViewButton.DELETE, DefaultViewButton.REFRESH);
/*        logger.trace("Update button states (editing = {}, selection = {})", editing, selection);
        getViewDelegate().setButtonsVisible(editing, DefaultViewButton.SAVE, DefaultViewButton.CANCEL);
        getViewDelegate().setButtonsVisible(selection.isPresent() && !editing, DefaultViewButton.EDIT, DefaultViewButton.DELETE);
        getViewDelegate().setButtonsVisible(!editing, DefaultViewButton.ADD);*/
    }

    /**
     * Default buttons that are always present in a CRUD view. Additional buttons
     * may be specified in a separate enumeration.
     */
    public enum DefaultViewButton implements ViewButton {
        REFRESH, ADD, EDIT, DELETE
    }

    public interface ViewDelegate<E extends AbstractEntity> extends AbstractPresenter.ViewDelegate {

        /**
         * Shows the specified entities in the table.
         */
        void setEntities(List<E> entities);
    }
}
