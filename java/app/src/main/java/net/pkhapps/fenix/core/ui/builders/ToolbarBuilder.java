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

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * Builder for creating a toolbar. A toolbar is a {@link com.vaadin.ui.HorizontalLayout} that can have
 * components on the left side and/or the right side. By default, the toolbar is 100% wide and has spacing
 * between the components. The toolbar also has the style {@code toolbar}.
 *
 * @author Petter Holmström
 */
public class ToolbarBuilder extends ComponentBuilder<HorizontalLayout, ToolbarBuilder> {

    private List<Component> leftComponents;
    private List<Component> rightComponents;

    /**
     * Creates and returns a new toolbar builder.
     */
    @NotNull
    public static ToolbarBuilder toolbar() {
        return new ToolbarBuilder();
    }

    protected ToolbarBuilder() {
    }

    @NotNull
    @Override
    protected HorizontalLayout newInstance() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.setWidth("100%");
        horizontalLayout.addStyleName("toolbar");
        return new HorizontalLayout();
    }

    /**
     * Enables the margins on the toolbar (by default they are off).
     */
    @NotNull
    public ToolbarBuilder withMargins() {
        component.setMargin(true);
        return this;
    }

    /**
     * Specifies the components that will show up on the left part of the toolbar.
     */
    @NotNull
    public ToolbarBuilder withLeftComponents(@NotNull Component... components) {
        leftComponents = Arrays.asList(components);
        return this;
    }

    /**
     * Specifies the components that will show up on the right part of the toolbar.
     */
    @NotNull
    public ToolbarBuilder withRightComponents(@NotNull Component... components) {
        rightComponents = Arrays.asList(components);
        return this;
    }

    @Override
    protected void doBuild() {
        if (leftComponents != null) {
            leftComponents.forEach(leftComponent -> {
                component.addComponent(leftComponent);
                component.setComponentAlignment(leftComponent, Alignment.MIDDLE_LEFT);
            });
        }
        Label separator = new Label();
        separator.setSizeUndefined();
        component.addComponent(separator);
        component.setExpandRatio(separator, 1f);
        if (rightComponents != null) {
            rightComponents.forEach(rightComponent -> {
                component.addComponent(rightComponent);
                component.setComponentAlignment(rightComponent, Alignment.MIDDLE_RIGHT);
            });
        }
    }
}
