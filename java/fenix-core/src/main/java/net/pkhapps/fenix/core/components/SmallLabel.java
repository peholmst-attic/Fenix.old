package net.pkhapps.fenix.core.components;

import com.vaadin.ui.Label;
import net.pkhapps.fenix.theme.FenixTheme;

/**
 * TODO Document me
 */
public class SmallLabel extends Label {
    public SmallLabel() {
        addStyleName(FenixTheme.LABEL_SMALL);
    }

    public SmallLabel(String content) {
        super(content);
        addStyleName(FenixTheme.LABEL_SMALL);
    }
}
