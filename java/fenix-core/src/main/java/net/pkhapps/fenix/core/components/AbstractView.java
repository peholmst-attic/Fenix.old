package net.pkhapps.fenix.core.components;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import net.pkhapps.fenix.core.i18n.MessageKeyConventions;
import org.vaadin.spring.i18n.I18N;

/**
 * TODO Document me!
 */
public abstract class AbstractView extends CustomComponent implements View {

    private final MessageKeyConventions messages = new MessageKeyConventions(getClass());
    private final I18N i18n;

    protected AbstractView(I18N i18n) {
        this.i18n = i18n;
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
    protected MessageKeyConventions getMessages() {
        return messages;
    }

    /**
     * {@inheritDoc}
     * <p/>
     * This implementation does nothing. Subclasses may override.
     */
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
