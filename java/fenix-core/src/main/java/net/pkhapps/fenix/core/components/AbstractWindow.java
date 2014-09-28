package net.pkhapps.fenix.core.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import net.pkhapps.fenix.core.i18n.MessageKeyConventions;
import org.vaadin.spring.i18n.I18N;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Optional;

/**
 * Base class for windows.
 *
 * @param <S> the type of the status object to return to the caller.
 * @param <V> the type of the view delegate (should be implemented by the concrete class as well).
 * @param <P> the type of the presenter.
 * @see net.pkhapps.fenix.core.components.AbstractWindowPresenter
 */
public abstract class AbstractWindow<S, V extends AbstractWindowPresenter.ViewDelegate<S>, P extends AbstractWindowPresenter<S, V>> extends Window implements AbstractWindowPresenter.ViewDelegate<S> {

    private final MessageKeyConventions messages = new MessageKeyConventions(getClass());
    private final P presenter;
    private final I18N i18n;
    private Optional<Callback<S>> callback;

    protected AbstractWindow(P presenter, I18N i18n) {
        this.presenter = presenter;
        this.i18n = i18n;
    }

    /**
     * Returns the presenter for this window (never {@code null}).
     */
    protected P getPresenter() {
        return presenter;
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

    @Override
    public void closeWindow(S status) {
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
     * Initializes the window after all dependencies have been injected. Subclasses do the initialization in {@link #doInit()}.
     */
    @PostConstruct
    @SuppressWarnings("unchecked")
    public final void init() {
        doInit();
        presenter.viewDelegateInitialized((V) this);
    }

    /**
     * Initializes the window. When this method is called, all dependencies have been injected, including the presenter.
     */
    protected abstract void doInit();

    /**
     * Returns the {@link com.vaadin.ui.Button} component of the specified view button instance.
     *
     * @param viewButton the view button instance (must not be {@code null}).
     * @return an optional containing the component if one was found, otherwise an empty optional.
     */
    protected Optional<Button> getButton(AbstractCrudPresenter.ViewButton viewButton) {
        return Optional.empty();
    }

    @Override
    public void setButtonsVisible(boolean visible, AbstractPresenter.ViewButton... buttons) {
        for (AbstractPresenter.ViewButton button : buttons) {
            getButton(button).ifPresent(btn -> btn.setVisible(visible));
        }
    }

    @Override
    public void setButtonsEnabled(boolean enabled, AbstractPresenter.ViewButton... buttons) {
        for (AbstractPresenter.ViewButton button : buttons) {
            getButton(button).ifPresent(btn -> btn.setEnabled(enabled));
        }
    }

    @Override
    public void attach() {
        super.attach();
        getPresenter().viewAttached();
    }

    @Override
    public void detach() {
        getPresenter().viewDetached();
        super.detach();
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
