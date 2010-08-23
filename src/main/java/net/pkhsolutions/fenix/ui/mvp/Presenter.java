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
 * application context. It uses autowiring by type to find its corresponding
 * View. That is, if the application context contains exactly one bean of type
 * <code>V</code>, it will automatically be injected into this presenter.
 * Otherwise, the initialization of the application context will fail.
 * <p>
 * If <a href=
 * "http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-classpath-scanning"
 * >classpath scanning</a> is used to find beans for the application context,
 * the presenter and view classes could look something like this:
 * <p>
 * 
 * <pre>
 * <code>
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
 *     ...
 * }
 * </pre>
 * 
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

	/**
	 * The view instance is injected by autowiring. If the application context
	 * does not contain one and only one instance of type V, this will fail.
	 */
	@Autowired
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
	protected ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * Gets the view for this presenter. The view instance will be automatically
	 * injected by the Spring application context using autowiring. Thus, at
	 * least when {@link #init()} is called (and after that), this method will
	 * return a non-<code>null</code> instance.
	 * 
	 * @return the view instance.
	 */
	protected final V getView() {
		return view;
	}

	/*
	 * An alternative way of initializing the presenter would be to annotate the
	 * init() method below with PostConstruct, and configure the presenter to
	 * depend on the view, e.g. by using the DependsOn annotation. However, to
	 * avoid strange behavior that might occur if the developer forgets the
	 * DependsOn annotation, the presenter is explicitly initialized by the
	 * view.
	 */

	/**
	 * This method is called by {@link AbstractView#init()} to initialize the
	 * presenter. When this method is called, the view will already have been
	 * initialized.
	 * <p>
	 * This implementation does nothing, subclasses may override.
	 */
	public void init() {
	}

}
