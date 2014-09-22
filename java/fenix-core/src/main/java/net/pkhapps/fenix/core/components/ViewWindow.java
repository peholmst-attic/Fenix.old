package net.pkhapps.fenix.core.components;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

/**
 * Window that makes it possible to open a navigator {@link com.vaadin.navigator.View} inside
 * a modal window. Please note that the view's {@link View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent) enter} method
 * will be called with a {@code null} parameter.
 */
public class ViewWindow<V extends View & Component> extends Window {

    public ViewWindow(String caption, V view, String width, String height) {
        super(caption, view);
        setWidth(width);
        setHeight(height);
        setModal(true);
        setResizable(false);
        center();
        view.enter(null);
    }
}
