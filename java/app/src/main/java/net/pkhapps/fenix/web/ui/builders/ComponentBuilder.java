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

import com.vaadin.server.Resource;
import com.vaadin.ui.Component;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.ParameterizedType;

/**
 * Base class for {@link com.vaadin.ui.Component} builders. One component builder instance can build exactly one
 * component instance.
 *
 * @param <C> the type of the component being built by this component builder.
 * @param <B> the type of the component builder itself. This will be returned by most of the methods to make it possible to chain multiple method calls together.
 * @author Petter Holmström
 */
public abstract class ComponentBuilder<C extends Component, B extends ComponentBuilder<C, B>> {

    private boolean built = false;

    /**
     * The component being built. The component instance is created when the builder instance is created.
     */
    protected C component;

    /**
     * Constructor is protected to prevent clients from creating new instances of the builder directly. Concrete
     * builder classes should provide static factory methods for creating new builders. Clients can then statically
     * import the factory methods, making the code easier to read.
     */
    protected ComponentBuilder() {
        component = newInstance();
    }

    /**
     * Specifies the caption of the component.
     *
     * @see com.vaadin.ui.Component#setCaption(String)
     */
    @NotNull
    public B withCaption(@NotNull String caption) {
        component.setCaption(caption);
        return (B) this;
    }

    /**
     * Specifies the icon of the component.
     *
     * @see com.vaadin.ui.Component#setIcon(com.vaadin.server.Resource)
     */
    @NotNull
    public B withIcon(@NotNull Resource icon) {
        component.setIcon(icon);
        return (B) this;
    }

    /**
     * Adds the specified style to the component.
     *
     * @see com.vaadin.ui.Component#addStyleName(String)
     */
    @NotNull
    public B withStyle(@NotNull String style) {
        component.addStyleName(style);
        return (B) this;
    }

    /**
     * Specifies the width of the component.
     *
     * @see com.vaadin.ui.Component#setWidth(String)
     */
    @NotNull
    public B withWidth(@NotNull String width) {
        component.setWidth(width);
        return (B) this;
    }

    /**
     * Specifies the height of the component.
     *
     * @see com.vaadin.ui.Component#setHeight(String)
     */
    @NotNull
    public B withHeight(@NotNull String height) {
        component.setHeight(height);
        return (B) this;
    }

    /**
     * Sets the width of the component to {@code null}.
     */
    @NotNull
    public B withUndefinedWidth() {
        component.setWidth(null);
        return (B) this;
    }

    /**
     * Sets the height of the component to {@code null}.
     */
    @NotNull
    public B withUndefinedHeight() {
        component.setHeight(null);
        return (B) this;
    }

    /**
     * Sets the width of the component to 100%.
     */
    @NotNull
    public B withFullWidth() {
        component.setWidth("100%");
        return (B) this;
    }

    /**
     * Sets the height of the component to 100%.
     */
    @NotNull
    public B withFullHeight() {
        component.setHeight("100%");
        return (B) this;
    }

    /**
     * Sets the size of the component to 100% x 100%.
     *
     * @see com.vaadin.ui.Component#setSizeFull()
     */
    @NotNull
    public B withFullSize() {
        component.setSizeFull();
        return (B) this;
    }

    /**
     * Sets the height and width of the component to {@code null}.
     *
     * @see com.vaadin.ui.Component#setSizeUndefined()
     */
    @NotNull
    public B withUndefinedSize() {
        component.setSizeUndefined();
        return (B) this;
    }

    /**
     * Called by the constructor to create a new instance of the component class. The default implementation will try to introspect the
     * generic superclass to get the component class. This only works if the generic superclass is a {@link java.lang.reflect.ParameterizedType}.
     * Subclasses may override.
     *
     * @throws java.lang.UnsupportedOperationException if the component instance could not be created for some reason.
     */
    @NotNull
    protected C newInstance() {
        try {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            Class<C> componentType = (Class<C>) parameterizedType.getActualTypeArguments()[0];
            return componentType.newInstance();
        } catch (Exception ex) {
            throw new UnsupportedOperationException("Could not create new component instance. Please override the newInstance method.", ex);
        }
    }

    /**
     * This method is called by {@link #build()} before the component instance is returned. Default implementation does nothing, subclasses may override.
     */
    protected void doBuild() {
    }

    /**
     * Builds and returns a component instance. This method can only be called once.
     *
     * @throws java.lang.IllegalStateException if the {@code build()} method has already been called.
     */
    @NotNull
    public C build() {
        if (built) {
            throw new IllegalStateException("The component has already been built.");
        }
        doBuild();
        built = true;
        return component;
    }

}
