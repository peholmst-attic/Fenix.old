/*
 * Copyright (c) 2010 The original author(s)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.pkhsolutions.fenix.ui.mvp;

import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import net.pkhsolutions.fenix.i18n.I18N;
import net.pkhsolutions.fenix.i18n.I18NListener;
import net.pkhsolutions.fenix.util.VisitableList;
import net.pkhsolutions.fenix.util.VisitableList.Visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.Assert;

/**
 * This is an abstract base class for {@link View}-implementations and is to be
 * used together with concrete {@link Presenter}-implementations.
 * <p>
 * This view base class is designed to be configured by a Spring application
 * context using the {@link Configurable @Configurable} AspectJ aspect. In order
 * for this to work, either compile-time weaving or runtime weaving must be
 * enabled. Please see the Spring reference guide for more information on this.
 * It is possible to use the view as-is, but then {@link #init()} and
 * {@link #destroy()} must be called manually.
 * <p>
 * When a view is created, an {@link I18N} instance is always specified as a
 * constructor parameter, making it easy to create localized view
 * implementations. When the current locale is changed, the view is informed
 * (see {@link #localeChanged(I18N, Locale, Locale)}), allowing it to update
 * labels and other visible text in the UI.
 * 
 * @see <a
 *      href="http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/aop.html#aop-atconfigurable">Spring
 *      Reference</a>
 * 
 * @author Petter Holmström
 * 
 * @param <V>
 *            the type of the View.
 * @param <P>
 *            the type of the Presenter.
 */
@Configurable
public abstract class AbstractView<V extends View, P extends Presenter<V>>
		implements View, I18NListener {

	private static final long serialVersionUID = 3301504661035990426L;

	/**
	 * Static logger for logging stuff.
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(AbstractView.class);

	private P presenter;

	private VisitableList<ViewListener> listenerList = new VisitableList<ViewListener>();

	private I18N i18n;

	/**
	 * Creates a new <code>AbstractView</code>, with the specified I18N
	 * instance. Once the I18N has been set, {@link #createPresenter()} is
	 * called to create the presenter.
	 * 
	 * @param i18n
	 *            the I18N instance to use (must not be <code>null</code>).
	 */
	public AbstractView(I18N i18n) {
		Assert.notNull(i18n, "i18n must not be null");
		this.i18n = i18n;
		this.presenter = createPresenter();
	}

	/**
	 * This method is called by the constructor to create the presenter. The
	 * presenter will be initialized by the {@link #init()} method.
	 * 
	 * @return a new presenter instance (never <code>null</code>).
	 */
	protected abstract P createPresenter();

	/**
	 * Gets the <code>I18N</code> instance that can be used to retrieve
	 * localized messages, etc.
	 * 
	 * @return the I18N instance.
	 */
	protected final I18N getI18n() {
		return i18n;
	}

	/**
	 * Gets the presenter for this view.
	 * 
	 * @return the presenter instance (never <code>null</code>).
	 */
	public final P getPresenter() {
		return presenter;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This method starts by calling {@link #initView()} and then calls
	 * {@link Presenter#init(View))} on the injected presenter instance. As it
	 * has been annotated with the {@link PostConstruct @PostConstruct}
	 * annotation, the Spring application context will automatically invoke this
	 * method once all the dependencies have been injected. Thus, a client
	 * normally never has to invoke this method manually.
	 * <p>
	 * This method also registers this view as a listener with the
	 * <code>I18N</code> instance <em>after</em> the view and the presenter have
	 * been initialized.
	 * 
	 * @see #getI18n()
	 * @see #getPresenter()
	 */
	@PostConstruct
	@Override
	public void init() {
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing view [" + this + "]");
		}
		initView();
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing presenter [" + presenter + "]");
		}
		getPresenter().init();
		if (logger.isDebugEnabled()) {
			logger.debug("View and presenter initialized");
			logger.debug("Registering I18NListener with [" + getI18n() + "]");
		}
		getI18n().addListener(this);
	}

	/**
	 * This method unregisters itself from the <code>I18N</code> instance.
	 * Subclasses that need to do any other cleaning operations before being
	 * destroyed may override, but must remember to call
	 * <code>super.destroy()</code>.
	 * 
	 * @see #getI18n()
	 * @see #init()
	 */
	@PreDestroy
	public void destroy() {
		if (logger.isDebugEnabled()) {
			logger.debug("Unregistering I18NListener from [" + getI18n() + "]");
		}
		getI18n().removeListener(this);
	}

	/**
	 * Initializes the view, e.g. creates and configures visual components,
	 * event listeners, etc. When this method is called, {@link #getPresenter()}
	 * will return a presenter instance, but it will not have been initialized
	 * yet. In other words, if the presenter requires initialization (not all
	 * presenters do), it must not be invoked by this method.
	 */
	protected abstract void initView();

	/**
	 * {@inheritDoc}
	 * <p>
	 * This implementation does nothing. Subclasses may override.
	 */
	@Override
	public void localeChanged(I18N source, Locale oldLocale, Locale newLocale) {
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This implementation always returns true. Subclasses may override.
	 */
	@Override
	public boolean okToClose() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This implementation does nothing. Subclasses may override.
	 */
	@Override
	public void showView(ViewController viewController,
			Map<String, Object> userData) {
	}

	@Override
	public void addListener(ViewListener listener) {
		listenerList.add(listener);
	}

	@Override
	public void removeListener(ViewListener listener) {
		listenerList.remove(listener);
	}

	@Override
	public void fireViewEvent(final ViewEvent event) {
		if (logger.isDebugEnabled()) {
			logger.debug("Firing event [" + event + "]");
		}
		listenerList.visitList(new Visitor<ViewListener>() {
			@Override
			public void visit(ViewListener item) {
				item.onViewEvent(event);
			}
		});
	}
}
