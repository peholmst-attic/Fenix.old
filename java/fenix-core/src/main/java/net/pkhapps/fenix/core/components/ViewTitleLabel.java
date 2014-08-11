package net.pkhapps.fenix.core.components;

import com.vaadin.ui.Label;
import net.pkhapps.fenix.theme.FenixTheme;

/**
 * Label intended to be used for titles of {@link com.vaadin.navigator.View}s. Automatically gets the style
 * {@link net.pkhapps.fenix.theme.FenixTheme#VIEW_TITLE}.
 */
public class ViewTitleLabel extends Label {
    public ViewTitleLabel() {
        addStyleName(FenixTheme.VIEW_TITLE);
    }

    public ViewTitleLabel(String content) {
        super(content);
        addStyleName(FenixTheme.VIEW_TITLE);
    }
}
