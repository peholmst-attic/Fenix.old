package net.pkhapps.fenix.core.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base class for presenters.
 */
public abstract class AbstractPresenter<V extends AbstractPresenter.ViewDelegate> implements Serializable {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private V viewDelegate;

    /**
     * Returns the view delegate once it has been initialized. If the view delegate
     * has not been initialized, this method throws an exception.
     */
    protected V getViewDelegate() {
        return Objects.requireNonNull(viewDelegate);
    }

    /**
     * Called by the view delegate when it has been initialized and is ready for action.
     */
    protected void viewDelegateInitialized(V viewDelegate) {
        this.viewDelegate = viewDelegate;
        updateButtonStates();
    }

    /**
     * Called by the view delegate when the user has entered the view.
     */
    protected abstract void viewDelegateEntered();

    /**
     * Updates the visibility/enablement states of all buttons in the view.
     */
    protected abstract void updateButtonStates();

    /**
     * Marker interface for view buttons, designed to be used with enums.
     */
    public interface ViewButton {
    }

    /**
     * Base interface for view delegates. The presenter controls the view via this delegate.
     * The delegate can either be a separate object or the view itself.
     */
    public interface ViewDelegate {
        /**
         * Shows or hides the specified buttons. By default, all buttons are hidden.
         */
        void setButtonsVisible(boolean visible, ViewButton... buttons);

        /**
         * Enables or disables the specified buttons. By default, all buttons are disabled.
         */
        void setButtonsEnabled(boolean enabled, ViewButton... buttons);
    }
}
