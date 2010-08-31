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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * This is an abstract base class for {@link View}-implementations and is to be
 * used together with concrete {@link Presenter}-implementations.
 * <p>
 * This view base class is designed to be configured inside a Spring application
 * context. By autowiring the {@link #AbstractView(Presenter) constructor} of
 * the concrete subclass, the corresponding Presenter can be automatically
 * injected. Please see the {@link Presenter} javadocs for an example of how the
 * view and the presenter could be configured.
 * <p>
 * As a convenience, a {@link I18N} instance is also automatically injected,
 * making it easy to create localized view implementations. When the current
 * locale is changed, the view is informed (see
 * {@link #localeChanged(I18N, Locale, Locale)}), allowing it to update labels
 * and other visible text in the UI.
 * 
 * @author Petter Holmström
 * 
 * @param <V>
 *            the type of the View.
 * @param <P>
 *            the type of the Presenter.
 */
public abstract class AbstractView<V extends View, P extends Presenter<V>>
		implements View, I18NListener {

	private static final long serialVersionUID = 3301504661035990426L;

	/**
	 * Static logger for logging stuff.
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(AbstractView.class);

	/*
	 * We cannot autowire the presenter, as Spring autowiring does not play well
	 * with generic types. In this case, it would look for a type of class
	 * Presenter instead of P. If our application had only one presenter, it
	 * would work, otherwise Spring would not know which presenter to inject and
	 * throw an exception.
	 */
	private P presenter;

	/**
	 * The I18N instance is injected by autowiring. If the application context
	 * does not contain one and only one instance of type I18N, this will fail.
	 */
	@Autowired
	private I18N i18n;

	/**
	 * Creates a new <code>AbstractView</code>, with the specified presenter.
	 * <p>
	 * If class path scanning is used in the Spring application context,
	 * subclasses should make this method public and autowire it, e.g. like
	 * this:
	 * 
	 * <code>
	 * <pre>
	 * {@link Component @Component}
	 * public class MyViewImpl extends {@link AbstractView}&lt;MyView, MyPresenter&gt; 
	 *     implements MyView {
	 *     
	 *     {@link Autowired @Autowired}
	 *     public MyViewImpl(MyPresenter presenter) {
	 *         super(presenter);
	 *     }
	 *     
	 *     ...
	 * }
	 * </code> </pre>
	 * 
	 * Otherwise, the constructor parameter has to be manually specified in the
	 * Spring application context.
	 * 
	 * 
	 * @param presenter
	 *            the presenter to use (must not be <code>null</code>).
	 */
	protected AbstractView(P presenter) {
		Assert.notNull(presenter, "presenter must not be null");
		this.presenter = presenter;
	}

	/**
	 * Gets the <code>I18N</code> instance that can be used to retrieve
	 * localized messages, etc. It will be automatically injected by the Spring
	 * application context using autowiring. Thus, at least when {@link #init()}
	 * is called (and after that), this method will return a non-
	 * <code>null</code> instance.
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
	protected final P getPresenter() {
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
	@SuppressWarnings("unchecked")
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
		getPresenter().init((V) this);
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
	 * <p>
	 * As this method is annotated with {@link PreDestroy @PreDestroy}, the
	 * Spring application context will automatically invoke this method when the
	 * application context is closing. Thus, a client normally never has to
	 * invoke this method manually.
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
	public void showView(Map<String, Object> userData) {
	}
}
