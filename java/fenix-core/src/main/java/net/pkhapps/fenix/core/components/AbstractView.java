package net.pkhapps.fenix.core.components;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import net.pkhapps.fenix.core.i18n.MessageKeyGenerator;
import org.vaadin.spring.i18n.I18N;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * TODO Document me!
 */
public abstract class AbstractView<V extends AbstractPresenter.ViewDelegate, P extends AbstractPresenter<V>> extends CustomComponent implements View, AbstractPresenter.ViewDelegate {

    private final MessageKeyGenerator messages = new MessageKeyGenerator(getClass());
    private final P presenter;
    private final I18N i18n;

    protected AbstractView(P presenter, I18N i18n) {
        this.presenter = presenter;
        this.i18n = i18n;
    }

    /**
     * @return
     */
    protected P getPresenter() {
        return presenter;
    }

    /**
     * @return
     */
    protected I18N getI18N() {
        return i18n;
    }

    /**
     * @return
     */
    protected MessageKeyGenerator getMessages() {
        return messages;
    }

    /**
     *
     */
    @PostConstruct
    @SuppressWarnings("unchecked")
    public final void init() {
        doInit();
        presenter.viewDelegateInitialized((V) this);
    }

    /**
     *
     */
    protected abstract void doInit();

    /**
     * @param viewButton
     * @return
     */
    protected Optional<Button> getButton(AbstractCrudPresenter.ViewButton viewButton) {
        return Optional.empty();
    }

    /**
     * @param viewButton
     * @return
     */
    protected Optional<ClickShortcut> getButtonClickShortcut(AbstractCrudPresenter.ViewButton viewButton) {
        return Optional.empty();
    }

    @Override
    public void setButtonsVisible(boolean visible, AbstractPresenter.ViewButton... buttons) {
        for (AbstractPresenter.ViewButton viewButton : buttons) {
            getButton(viewButton).ifPresent(button -> {
                button.setVisible(visible);
                if (visible) {
                    setButtonClickShortcut(viewButton, button);
                } else {
                    button.setClickShortcut(0);
                }
            });
        }
    }

    private void setButtonClickShortcut(AbstractPresenter.ViewButton viewButton, Button button) {
        getButtonClickShortcut(viewButton).ifPresent(shortcut -> button.setClickShortcut(shortcut.keyCode, shortcut.modifiers));
    }

    @Override
    public void setButtonsEnabled(boolean enabled, AbstractPresenter.ViewButton... buttons) {
        for (AbstractPresenter.ViewButton viewButton : buttons) {
            getButton(viewButton).ifPresent(button -> {
                button.setEnabled(enabled);
                if (enabled) {
                    setButtonClickShortcut(viewButton, button);
                } else {
                    button.setClickShortcut(0);
                }
            });
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        getPresenter().viewDelegateEntered();
    }

    /**
     *
     */
    protected static class ClickShortcut {
        protected final int keyCode;
        protected final int[] modifiers;

        protected ClickShortcut(int keyCode, int... modifiers) {
            this.keyCode = keyCode;
            this.modifiers = modifiers;
        }
    }
}
