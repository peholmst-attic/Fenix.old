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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import net.pkhsolutions.fenix.i18n.I18N;
import net.pkhsolutions.fenix.i18n.I18NListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is an abstract base class for {@link View}-implementations and is to be
 * used together with concrete {@link Presenter}-implementations.
 * <p>
 * This view base class is designed to be configured inside a Spring application
 * context. It uses autowiring by type to find its corresponding Presenter. That
 * is, if the application context contains exactly one bean of type
 * <code>P</code>, it will automatically be injected into this view. Otherwise,
 * the initialization of the application context will fail. Please see the
 * {@link Presenter} javadocs for an example of how the view and the presenter
 * could be configured.
 * <p>
 * As a convenience, a {@link I18N} instance is also automatically injected,
 * making it easy to create localized view implementations.
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

	/**
	 * The presenter is injected by autowiring. If the application context does
	 * not contain one and only one instance of type P, this will fail.
	 */
	@Autowired
	private P presenter;

	/**
	 * The I18N instance is injected by autowiring. If the application context
	 * does not contain one and only one instance of type I18N, this will fail.
	 */
	@Autowired
	private I18N i18n;

	/**
	 * Gets the <code>I18N</code> instance that can be used to retrieve
	 * localized messages, etc. It will be automatically injected by the Spring
	 * application context using autowiring. Thus, at least when {@link #init()}
	 * is called (and after that), this method will return a non-
	 * <code>null</code> instance.
	 * 
	 * @return the I18N instance.
	 */
	protected I18N getI18n() {
		return i18n;
	}

	/**
	 * Gets the presenter for this view. The actual presenter instance will be
	 * automatically injected by the Spring application context using
	 * autowiring. Thus, at least when {@link #init()} is called (and after
	 * that), this method will return a non-<code>null</code> instance.
	 * 
	 * @return the presenter instance.
	 */
	protected P getPresenter() {
		return presenter;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This method starts by calling {@link #initView()} and then calls
	 * {@link Presenter#init()} on the injected presenter instance. As it has
	 * been annotated with the {@link PostConstruct @PostConstruct} annotation,
	 * the Spring application context will automatically invoke this method once
	 * all the dependencies have been injected. Thus, a client normally never
	 * has to invoke this method manually.
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
		presenter.init();
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
	 * This method does nothing. Subclasses may override.
	 */
	@Override
	public void localeChanged(I18N source, Locale oldLocale, Locale newLocale) {
	}
}
