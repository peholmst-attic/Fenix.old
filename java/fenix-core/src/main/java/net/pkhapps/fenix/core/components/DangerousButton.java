package net.pkhapps.fenix.core.components;

import com.vaadin.ui.Button;
import net.pkhapps.fenix.theme.FenixTheme;

/**
 * Created by peholmst on 2014-07-10.
 */
public class DangerousButton extends Button {

    public DangerousButton(String caption) {
        super(caption);
        addStyleName(FenixTheme.BUTTON_DANGER);
    }

    public DangerousButton(String caption, ClickListener listener) {
        super(caption, listener);
        addStyleName(FenixTheme.BUTTON_DANGER);
    }
}
