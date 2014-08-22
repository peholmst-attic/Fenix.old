package net.pkhapps.fenix.core.components;

import com.vaadin.ui.Label;
import net.pkhapps.fenix.theme.FenixTheme;

/**
 * Created by peholmst on 2014-07-10.
 */
public class TinyLabel extends Label {
    public TinyLabel() {
        addStyleName(FenixTheme.LABEL_SMALL);
    }

    public TinyLabel(String content) {
        super(content);
        addStyleName(FenixTheme.LABEL_SMALL);
    }
}
