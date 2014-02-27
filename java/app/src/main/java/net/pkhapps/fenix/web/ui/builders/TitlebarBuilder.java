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

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import static net.pkhapps.fenix.web.ui.builders.LabelBuilder.h1;

/**
 * Builder for creating a titlebar. A titlebar is a {@link com.vaadin.ui.HorizontalLayout} that has
 * a title label (with style {@code h1}) on the left and optionally a group of components on the right.
 * By default, the titlebar is 100% wide and has a margin around it and spacing
 * between the components. The titlebar also has the style {@code titlebar}.
 *
 * @author Petter Holmström
 */
public class TitlebarBuilder extends ComponentBuilder<HorizontalLayout, TitlebarBuilder> {

    private Label title;
    private List<Component> rightComponents;

    /**
     * Creates and returns a new titlebar builder.
     */
    @NotNull
    public static TitlebarBuilder titlebar() {
        return new TitlebarBuilder();
    }

    protected TitlebarBuilder() {
    }

    @NotNull
    @Override
    protected HorizontalLayout newInstance() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");
        layout.setSpacing(true);
        layout.setMargin(true);
        layout.addStyleName("titlebar");
        title = h1().withUndefinedWidth().build();
        layout.addComponent(layout);
        layout.setExpandRatio(title, 1f);
        return layout;
    }

    /**
     * Specifies the title of the titlebar.
     */
    @NotNull
    public TitlebarBuilder withTitle(@NotNull String title) {
        this.title.setCaption(title);
        return this;
    }

    /**
     * Specifies the components that will show up in the right part of the titlebar.
     */
    @NotNull
    public TitlebarBuilder withRightComponents(@NotNull Component... components) {
        rightComponents = Arrays.asList(components);
        return this;
    }

    /**
     * Disables the margins on the title bar (by default they are on).
     */
    @NotNull
    public TitlebarBuilder withoutMargins() {
        component.setMargin(false);
        return this;
    }

    @Override
    protected void doBuild() {
        if (rightComponents != null) {
            rightComponents.forEach(rightComponent -> {
                component.addComponent(rightComponent);
                component.setComponentAlignment(rightComponent, Alignment.MIDDLE_RIGHT);
            });
        }
    }
}
