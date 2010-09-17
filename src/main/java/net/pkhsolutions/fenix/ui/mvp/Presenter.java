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
import org.springframework.beans.factory.annotation.Configurable;
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
 * 
 * @see AbstractView
 * @author Petter Holmström
 * 
 * @param <V>
 *            the type of the View.
 */
@Configurable
public abstract class Presenter<V extends View> implements Serializable {

	private static final long serialVersionUID = 2534637487948598474L;

	/**
	 * Static logger for logging stuff.
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(Presenter.class);

	private V view;

	/**
	 * Creates a new <code>Presenter</code> with the specified view. The
	 * presenter is initialized in the {@link #init()} method.
	 * 
	 * @param view
	 *            the view to use (must not be <code>null</code>).
	 */
	public Presenter(V view) {
		Assert.notNull(view, "view must not be null");
		this.view = view;
	}

	/**
	 * Gets the view for this presenter.
	 * 
	 * @return the view instance (never <code>null</code>).
	 */
	protected final V getView() {
		return view;
	}

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
