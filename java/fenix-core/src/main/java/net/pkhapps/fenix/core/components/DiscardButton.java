package net.pkhapps.fenix.core.components;

import com.vaadin.ui.Button;
import net.pkhapps.fenix.theme.FenixTheme;

/**
 * Created by peholmst on 2014-07-10.
 */
public class DiscardButton extends Button {

    public DiscardButton(String caption) {
        super(caption);
        addStyleName(FenixTheme.DISCARD_BUTTON);
    }

    public DiscardButton(String caption, ClickListener listener) {
        super(caption, listener);
        addStyleName(FenixTheme.DISCARD_BUTTON);
    }
}
