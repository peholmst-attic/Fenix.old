package net.pkhapps.fenix.core.components;

import com.vaadin.ui.Button;
import net.pkhapps.fenix.theme.FenixTheme;

/**
 * TODO Document me!
 */
public class PrimaryButton extends Button {
    public PrimaryButton(String caption, ClickListener listener) {
        super(caption, listener);
        addStyleName(FenixTheme.BUTTON_PRIMARY);
    }

    public PrimaryButton(String caption) {
        super(caption);
        addStyleName(FenixTheme.BUTTON_PRIMARY);
    }
}
