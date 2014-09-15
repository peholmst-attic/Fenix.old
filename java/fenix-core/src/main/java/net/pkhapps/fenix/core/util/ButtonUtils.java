package net.pkhapps.fenix.core.util;

import com.vaadin.ui.Button;

/**
 * TODO Document me!
 */
public final class ButtonUtils {

    private ButtonUtils() {
    }

    public static Button.ClickListener toClickListener(Runnable runnable) {
        return event -> runnable.run();
    }

}
