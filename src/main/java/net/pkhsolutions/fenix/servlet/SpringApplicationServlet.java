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
package net.pkhsolutions.fenix.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

/**
 * This Vaadin application servlet assumes that each Vaadin application instance
 * runs inside its own Spring application context, called a <em>GUI Context</em>
 * . Every GUI Context is a child of the main {@link WebApplicationContext}
 * returned by
 * {@link WebApplicationContextUtils#getWebApplicationContext(javax.servlet.ServletContext)
 * WebApplicationContextUtils} . Thus, any service beans defined in the main web
 * application context are available for injection into any beans defined in the
 * GUI Context.
 * <p>
 * In order to work, this servlet requires the name of the {@link Application}
 * class. This name is specified in the <code>application</code> servlet
 * parameters. The GUI Context is then constructed by <a href=
 * "http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/beans.html#beans-classpath-scanning"
 * >scanning</a> the package of the application class (and all its subpackages)
 * for beans that are annotated with {@link Component @Component} or any other
 * of the Spring stereotype annotations. Even the application class itself
 * should be annotated with {@link Component @Component}.
 * <p>
 * The base packages to scan can be overriden by using the optional servlet
 * parameter <code>guiBasePackages</code>. If this parameter is set, only the
 * packages specified are scanned. Multiple package names must be separated by
 * spaces.
 * <p>
 * Here is an example of how the servlet could be configured in the web
 * application descriptor (<em>web.xml</em>): <code>
 * <pre>
 * &lt;servlet&gt;
 *   &lt;servlet-name&gt;MyAppServlet&lt;/servlet-name&gt;
 *   &lt;servlet-class>net.pkhsolutions.fenix.servlet.SpringApplicationServlet&lt;/servlet-class&gt;
 *   &lt;init-param&gt;
 *     &lt;param-name&gt;application&lt;/param-name&gt;
 *     &lt;param-value&gt;com.foo.ui.MyApp&lt;/param-value&gt;
 *   &lt;/init-param&gt;
 * &lt;/servlet&gt; 
 * </pre>
 * </code> In this example, the <code>com.foo.ui</code> package and all its
 * subpackages would be scanned for potential beans. The <code>MyApp</code>
 * class would be defined like this: <code>
 * <pre>
 * {@link Component @Component}
 * public class MyApp extends {@link Application} {
 * 
 *     public void {@link Application#init() init}() {
 *         ...
 *     }
 * }
 * </pre>
 * </code>
 * <p>
 * This servlet also resolves the locale using a Spring locale resolver and
 * updates the requests accordingly.
 * <p>
 * This class is roughly based on the <a
 * href="http://dev.vaadin.com/browser/incubator/SpringApplication"
 * >SpringApplication example</a> by Petri Hakala, with several modifications
 * here and there by yours truly.
 * 
 * @author Petter Holmström
 */
public class SpringApplicationServlet extends AbstractApplicationServlet {

	private static final long serialVersionUID = 9119643883315827366L;

	/**
	 * Protected SLF4J log for logging stuff.
	 */
	protected static final Logger logger = LoggerFactory
			.getLogger(SpringApplicationServlet.class);

	private transient WebApplicationContext applicationContext;

	private Class<? extends Application> applicationClass;

	private String[] guiBasePackages;

	private transient LocaleResolver localeResolver;

	@SuppressWarnings("unchecked")
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		/*
		 * Look up the application class name
		 */
		final String applicationClassName = servletConfig
				.getInitParameter("application");

		if (applicationClassName == null) {
			if (logger.isErrorEnabled()) {
				logger.error("Application not specified in servlet parameters");
			}
			throw new ServletException(
					"Application not specified in servlet parameters");
		}

		/*
		 * Try to load the class
		 */
		try {
			applicationClass = (Class<? extends Application>) getClassLoader()
					.loadClass(applicationClassName);
		} catch (final ClassNotFoundException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Failed to load application class ["
						+ applicationClassName + "]");
			}
			throw new ServletException("Failed to load application class");
		}

		if (logger.isInfoEnabled()) {
			logger.info("Using application class [" + applicationClass + "]");
		}

		/*
		 * Look up the GUI base package names
		 */
		String guiBasePackageNames = servletConfig
				.getInitParameter("guiBasePackages");
		if (guiBasePackageNames == null) {
			if (logger.isInfoEnabled()) {
				logger.info("No base packages specified, determining package from application class");
			}
			guiBasePackageNames = applicationClass.getPackage().getName();
		}

		if (logger.isInfoEnabled()) {
			logger.info("Using GUI base packages [" + guiBasePackageNames + "]");
		}

		// Split the packages names up into an array
		this.guiBasePackages = guiBasePackageNames.split(" ");

		/*
		 * Fetch the Spring web application context
		 */
		applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletConfig.getServletContext());

		/*
		 * Initialize the locale resolver
		 */
		initLocaleResolver(applicationContext);
	}

	private void initLocaleResolver(ApplicationContext context) {
		try {
			/*
			 * Try to look up the locale resolver from the application context
			 */
			localeResolver = context.getBean(
					DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME,
					LocaleResolver.class);
			if (logger.isInfoEnabled()) {
				logger.info("Using LocaleResolver [" + localeResolver + "]");
			}
		} catch (NoSuchBeanDefinitionException e) {
			/*
			 * No locale resolver was defined in the application context, so we
			 * create a default one.
			 */
			localeResolver = new SessionLocaleResolver();
			if (logger.isWarnEnabled()) {
				logger.warn("Unable to locate LocaleResolver with name '"
						+ DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME
						+ "', using default [" + localeResolver + "]");
			}
		}
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Resolve the locale from the request
		 */
		final Locale locale = localeResolver.resolveLocale(request);
		if (logger.isDebugEnabled()) {
			logger.debug("Resolved locale [" + locale + "]");
		}

		/*
		 * Store the locale in the LocaleContextHolder, making it available to
		 * Spring.
		 */
		LocaleContextHolder.setLocale(locale);
		ServletRequestAttributes requestAttributes = new ServletRequestAttributes(
				request);
		RequestContextHolder.setRequestAttributes(requestAttributes);
		try {
			/*
			 * We need to override the request to return the locale resolved by
			 * Spring.
			 */
			super.service(new HttpServletRequestWrapper(request) {
				@Override
				public Locale getLocale() {
					return locale;
				}
			}, response);
		} finally {
			if (!locale.equals(LocaleContextHolder.getLocale())) {
				/*
				 * The locale in LocaleContextHolder was changed during the
				 * request, so we have to update the resolver.
				 */
				if (logger.isDebugEnabled()) {
					logger.debug("Locale changed, updating locale resolver");
				}
				localeResolver.setLocale(request, response,
						LocaleContextHolder.getLocale());
			}
			LocaleContextHolder.resetLocaleContext();
			RequestContextHolder.resetRequestAttributes();
		}
	}

	@Override
	protected Class<? extends Application> getApplicationClass()
			throws ClassNotFoundException {
		return applicationClass;
	}

	@Override
	protected Application getNewApplication(HttpServletRequest request)
			throws ServletException {
		if (logger.isDebugEnabled()) {
			logger.debug("Creating a new GUI Context");
		}
		try {
			final AnnotationConfigApplicationContext guiContext = new AnnotationConfigApplicationContext();
			guiContext.setParent(applicationContext);
			guiContext.scan(guiBasePackages);
			guiContext.refresh();

			final Application application = guiContext
					.getBean(applicationClass);
			if (logger.isDebugEnabled()) {
				logger.debug("Fetching application instance [" + application
						+ "] from GUI Context [" + guiContext + "]");
			}
			return application;
		} catch (NoSuchBeanDefinitionException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Context does not contain an Application bean", e);
			}
			throw new ServletException("No Application instance could be found");
		} catch (BeansException e) {
			if (logger.isErrorEnabled()) {
				logger.error("Could not initialize GUI Context", e);
			}
			throw new ServletException("Could not create a new application");
		}
	}

}