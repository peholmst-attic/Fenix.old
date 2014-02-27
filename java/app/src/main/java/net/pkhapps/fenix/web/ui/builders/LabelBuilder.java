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
package net.pkhapps.fenix.web.ui.builders;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import org.jetbrains.annotations.NotNull;

/**
 * Builder for {@link com.vaadin.ui.Label}s.
 *
 * @author Petter Holmström
 */
public class LabelBuilder extends ComponentBuilder<Label, LabelBuilder> {

    /**
     * Creates and returns an ordinary label builder.
     */
    @NotNull
    public static LabelBuilder label() {
        return new LabelBuilder();
    }

    /**
     * Creates and returns a label builder with the style {@code h1} added to the label.
     */
    @NotNull
    public static LabelBuilder h1() {
        return new LabelBuilder().withStyle("h1");
    }

    /**
     * Creates and returns a label builder with the style {@code h2} added to the label.
     */
    @NotNull
    public static LabelBuilder h2() {
        return new LabelBuilder().withStyle("h2");
    }

    /**
     * Creates and returns a label builder with the style {@code light} added to the label.
     */
    @NotNull
    public static LabelBuilder lightLabel() {
        return new LabelBuilder().withStyle("light");
    }

    protected LabelBuilder() {
    }

    /**
     * Specifies the content mode of the label.
     *
     * @see com.vaadin.ui.Label#setContentMode(com.vaadin.shared.ui.label.ContentMode)
     */
    @NotNull
    public LabelBuilder withContentMode(@NotNull ContentMode contentMode) {
        component.setContentMode(contentMode);
        return this;
    }

    /**
     * Specifies the converter for the label.
     *
     * @see com.vaadin.ui.Label#setConverter(com.vaadin.data.util.converter.Converter)
     */
    @NotNull
    public LabelBuilder withConverter(@NotNull Converter<String, ?> converter) {
        component.setConverter(converter);
        return this;
    }
}
