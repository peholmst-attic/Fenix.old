/*
 * Fenix
 * Copyright (C) 2014 Petter Holmström
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.pkhapps.fenix.core.ui.builders;

import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.Button;
import org.jetbrains.annotations.NotNull;

/**
 * Builder for {@link com.vaadin.ui.Button}s.
 *
 * @author Petter Holmström
 */
public class ButtonBuilder extends ComponentBuilder<Button, ButtonBuilder> {

    /**
     * Creates and returns an ordinary button builder.
     */
    @NotNull
    public static ButtonBuilder button() {
        return new ButtonBuilder();
    }

    /**
     * Creates and returns a button builder with the style names {@code primary} and {@code default} added to the button,
     * and with the ENTER-key bound to the button.
     */
    @NotNull
    public static ButtonBuilder primaryButton() {
        return new ButtonBuilder()
                .withShortcut(ShortcutAction.KeyCode.ENTER)
                .withStyle("primary default");
    }

    /**
     * Creates and returns a button builder with the style name {@code small} added to the button.
     */
    @NotNull
    public static ButtonBuilder smallButton() {
        return new ButtonBuilder()
                .withStyle("small");
    }

    /**
     * Creates and returns a button builder with the style name {@code cancel} added to the button,
     * and with the ESCAPE-key bound to the button.
     */
    @NotNull
    public static ButtonBuilder cancelButton() {
        return new ButtonBuilder()
                .withShortcut(ShortcutAction.KeyCode.ESCAPE)
                .withStyle("cancel");
    }

    protected ButtonBuilder() {
    }

    /**
     * Adds a click listener to the button.
     *
     * @see com.vaadin.ui.Button#addClickListener(com.vaadin.ui.Button.ClickListener)
     */
    @NotNull
    public ButtonBuilder withListener(@NotNull Button.ClickListener listener) {
        component.addClickListener(listener);
        return this;
    }

    /**
     * Adds a click listener to the button that will automatically re-enable the button
     * after the listener has been invoked.
     *
     * @see #withDisableOnClick
     * @see com.vaadin.ui.Button#addClickListener(com.vaadin.ui.Button.ClickListener)
     */
    @NotNull
    public ButtonBuilder withAutoEnablingListener(@NotNull Button.ClickListener listener) {
        component.addClickListener(event -> {
            try {
                listener.buttonClick(event);
            } finally {
                component.setEnabled(true);
            }
        });
        return this;
    }

    /**
     * Specifies that the button should be disabled after click.
     *
     * @see #withAutoEnablingListener(com.vaadin.ui.Button.ClickListener)
     * @see com.vaadin.ui.Button#setDisableOnClick(boolean)
     */
    @NotNull
    public ButtonBuilder withDisableOnClick(boolean disableOnClick) {
        component.setDisableOnClick(disableOnClick);
        return this;
    }

    /**
     * Specifies the click shortcut of the button.
     *
     * @see com.vaadin.ui.Button#setClickShortcut(int, int...)
     */
    @NotNull
    public ButtonBuilder withShortcut(int keyCode, int... modifiers) {
        component.setClickShortcut(keyCode, modifiers);
        return this;
    }
}
