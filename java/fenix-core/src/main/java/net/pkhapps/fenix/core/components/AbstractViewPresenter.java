package net.pkhapps.fenix.core.components;

import com.vaadin.navigator.ViewChangeListener;

/**
 * Base class for presenters that serve {@link com.vaadin.navigator.View}s.
 */
@Deprecated
public abstract class AbstractViewPresenter<V extends AbstractPresenter.ViewDelegate> extends AbstractPresenter<V> {

    /**
     * Called by the view delegate when the user has entered the view.
     *
     * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
     */
    protected abstract void viewDelegateEntered(ViewChangeListener.ViewChangeEvent viewChangeEvent);

}
