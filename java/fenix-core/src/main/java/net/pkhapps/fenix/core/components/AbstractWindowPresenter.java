package net.pkhapps.fenix.core.components;

/**
 * Base class for presenters that serve {@link com.vaadin.ui.Window}s.
 *
 * @param <S> the type of the status object to return to the caller.
 * @param <V> the type of the view delegate.
 */
@Deprecated
public abstract class AbstractWindowPresenter<S, V extends AbstractWindowPresenter.ViewDelegate<S>> extends AbstractPresenter<V> {

    /**
     * Base interface for view delegates. The presenter controls the view via this delegate.
     * The delegate can either be a separate object or the view itself.
     *
     * @param <S> the type of the status object to return to the caller.
     */
    public interface ViewDelegate<S> extends AbstractPresenter.ViewDelegate {
        /**
         * Closes the window.
         *
         * @param status the status object to pass to the caller that opened the window.
         */
        void closeWindow(S status);
    }
}
