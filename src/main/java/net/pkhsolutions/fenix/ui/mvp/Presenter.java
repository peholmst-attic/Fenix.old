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

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * This is an abstract base class for Presenters in the Model-View-Presenter
 * (MVP) pattern. It plays particularly well together with Views that extend the
 * {@link AbstractView} base class.
 * <p>
 * The presenter contains the logic that controls the GUI. It reads information
 * from the Model and passes it to the View for showing. It also receives events
 * from the View and decides what to do with them.
 * <p>
 * Normally, the presenter should not contain any code that couples it to a
 * particular View framework, such as Vaadin, JSP or Swing. There are two major
 * advantages with this approach:
 * <ol>
 * <li>It makes it possible to write unit tests for the GUI logic that do not
 * need to simulate user input.</li>
 * <li>It makes it easy to rewrite the GUI using another framework, as only the
 * views need to be reimplemented (well, at least in a perfect world).</li>
 * </ol>
 * <p>
 * This presenter base class is designed to be configured inside a Spring
 * application context. If <a href=
 * "http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-classpath-scanning"
 * >classpath scanning</a> is used to find beans for the application context,
 * the presenter and view classes could look something like this:
 * 
 * <code>
 * <pre>
 * {@link Component @Component}
 * public class MyPresenter extends Presenter&lt;MyView&gt; {
 *     ...
 * }
 * 
 * public interface MyView extends {@link View} {
 *     ...
 * }
 * 
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
 * </pre>
 * </code>
 * 
 * @see AbstractView
 * @author Petter Holmström
 * 
 * @param <V>
 *            the type of the View.
 */
public abstract class Presenter<V extends View> implements Serializable {

	private static final long serialVersionUID = 2534637487948598474L;

	/**
	 * Static logger for logging stuff.
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(Presenter.class);
	/*
	 * We cannot autowire the view, as Spring autowiring does not play well with
	 * generic types. In this case, it would look for a type of class View
	 * instead of V. If our application had only one view, it would work,
	 * otherwise Spring would not know which view to inject and throw an
	 * exception.
	 */
	private V view;

	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Gets the Spring application context that this presenter has been
	 * configured in.
	 * <p>
	 * If the application context is an instance of
	 * {@link ConfigurableApplicationContext}, the presenter may close the
	 * application by invoking {@link ConfigurableApplicationContext#close()}.
	 * 
	 * @return the application context.
	 */
	protected final ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * Gets the view for this presenter. The view instance will be set by the
	 * {@link #init(View)} method.
	 * 
	 * @return the view instance (never <code>null</code> once the presenter has
	 *         been initialized).
	 */
	protected final V getView() {
		return view;
	}

	/**
	 * This method is called by {@link AbstractView#init()} to initialize the
	 * presenter. When this method is called, the view will already have been
	 * initialized.
	 * <p>
	 * Apart from setting the <code>view</code> property, this method does
	 * nothing. Subclasses may override, but must remember to start by calling
	 * <code>super.init(view)</code>.
	 * 
	 * @see #getView()
	 * @param view
	 *            the view instance, never <code>null</code>.
	 */
	public void init(V view) {
		Assert.notNull(view, "view must not be null");
		if (logger.isDebugEnabled()) {
			logger.debug("Initializing presenter [" + this
					+ "], setting view to [" + view + "]");
		}
		this.view = view;
	}

}
