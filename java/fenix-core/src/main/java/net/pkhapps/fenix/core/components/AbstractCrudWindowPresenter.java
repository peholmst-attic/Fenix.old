package net.pkhapps.fenix.core.components;

import net.pkhapps.fenix.core.boundary.CrudService;
import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.validation.ValidationFailedException;

import java.util.Optional;

/**
 * Base class for presenters that serve {@link com.vaadin.ui.Window}s that are used to edit entities.
 *
 * @param <V> the type of the view delegate.
 * @param <E> the type of the entity to edit.
 * @param <B> the type of the CRUD service (the "backend") that is used to save the entity.
 */
@Deprecated
public abstract class AbstractCrudWindowPresenter<V extends AbstractCrudWindowPresenter.ViewDelegate<E>, E extends AbstractEntity, B extends CrudService<E>>
        extends AbstractWindowPresenter<Optional<E>, V> {

    private final B service;

    protected AbstractCrudWindowPresenter(B service) {
        this.service = service;
    }

    /**
     * Saves the changes and closes the window. The close status is an {@link java.util.Optional} containing
     * the saved entity.
     */
    public void save() {
        getViewDelegate().commit().ifPresent(this::doSave);
    }

    /**
     * Saves the specified entity and closes the window if no exceptions occur.
     *
     * @param entity the entity to save (must not be {@code null}).
     */
    protected void doSave(E entity) {
        try {
            entity = service.save(entity);
            getViewDelegate().closeWindow(Optional.of(entity));
        } catch (ValidationFailedException ex) {
            getViewDelegate().showValidationErrors(ex);
        }
    }

    /**
     * Closes the window without saving any changes. The close status is an empty {@link java.util.Optional}.
     */
    public void cancel() {
        getViewDelegate().closeWindow(Optional.empty());
    }

    /**
     * Base interface for view delegates. The presenter controls the view via this delegate.
     * The delegate can either be a separate object or the view itself.
     *
     * @param <E> the type of the entity being edited in the window.
     */
    public interface ViewDelegate<E extends AbstractEntity> extends AbstractWindowPresenter.ViewDelegate<Optional<E>> {

        /**
         * Populates the form with data from the specified entity.
         */
        void populate(E entity);

        /**
         * Commits the form and returns the updated entity. If the form cannot be committed (e.g. due to
         * validation errors), an empty Optional is returned.
         */
        Optional<E> commit();

        /**
         * Shows the validation errors to the user.
         */
        void showValidationErrors(ValidationFailedException validationErrors);
    }
}
