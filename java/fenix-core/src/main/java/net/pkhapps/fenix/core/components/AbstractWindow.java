package net.pkhapps.fenix.core.components;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import net.pkhapps.fenix.core.i18n.MessageKeyConventions;
import org.vaadin.spring.i18n.I18N;

import java.io.Serializable;
import java.util.Optional;

/**
 * Base class for windows.
 *
 * @param <S> the type of the status object to return to the caller.
 */
public abstract class AbstractWindow<S> extends Window {

    private final MessageKeyConventions messages = new MessageKeyConventions(getClass());
    private final I18N i18n;
    private Optional<Callback<S>> callback;

    protected AbstractWindow(I18N i18n) {
        this.i18n = i18n;
    }

    /**
     * Returns the I18N instance for this window (never {@code null}).
     */
    protected I18N getI18N() {
        return i18n;
    }

    /**
     * Returns the message key conventions to be used when constructing keys for the I18N instance (never {@code null}).
     */
    protected MessageKeyConventions getMessages() {
        return messages;
    }

    /**
     * Closes the window.
     *
     * @param status the status object to pass to the caller that opened the window.
     */
    protected void closeWindow(S status) {
        close();
        callback.ifPresent(callback -> callback.windowClosed(status));
    }

    /**
     * Opens the window in the current UI. This method is protected, the concrete subclass may choose to make the method
     * public if necessary.
     *
     * @param callback an optional callback to invoke when the window is closed (must not be {@code null} but may be empty).
     */
    protected void openWindow(Optional<Callback<S>> callback) {
        openWindow(UI.getCurrent(), callback);
    }

    /**
     * Opens the window in the specified UI. This method is protected, the concrete subclass may choose to make the method
     * public if necessary.
     *
     * @param ui       the UI to add the window to (must not be {@code null}).
     * @param callback an optional callback to invoke when the window is closed (must not be {@code null} but may be empty).
     */
    protected void openWindow(UI ui, Optional<Callback<S>> callback) {
        this.callback = callback;
        ui.addWindow(this);
    }

    /**
     * Callback to be invoked when a window is closed.
     *
     * @param <S> the type of the status object to return to the caller.
     */
    @FunctionalInterface
    public interface Callback<S> extends Serializable {
        /**
         * Called when the window has been closed.
         *
         * @param status a status object reported by the window.
         */
        void windowClosed(S status);
    }
}
