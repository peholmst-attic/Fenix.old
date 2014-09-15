package net.pkhapps.fenix.core.components;

import com.vaadin.ui.Button;
import net.pkhapps.fenix.theme.FenixTheme;

/**
 * TODO Document me!
 */
public class FriendlyButton extends Button {

    public FriendlyButton(String caption) {
        super(caption);
        addStyleName(FenixTheme.BUTTON_FRIENDLY);
    }

    public FriendlyButton(String caption, ClickListener listener) {
        super(caption, listener);
        addStyleName(FenixTheme.BUTTON_FRIENDLY);
    }
}
