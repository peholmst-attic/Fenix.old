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
package net.pkhsolutions.fenix.i18n;

import java.io.Serializable;
import java.util.Locale;
import java.util.Set;

/**
 * This interface defines some methods that are useful for internationalization
 * (i18n). It provides localized strings and information about the locales of
 * the application.
 * 
 * @author Petter Holmström
 */
public interface I18N extends Serializable {
	/**
	 * Tries to resolve the message using the locale returned by
	 * {@link #getLocale()}. If the message cannot be found, a standard
	 * implementation specific string is returned instead.
	 * 
	 * @see org.springframework.context.MessageSource#getMessage(String,
	 *      Object[], Locale)
	 * 
	 * @param code
	 *            the code to look up.
	 * @param args
	 *            array of arguments that will be filled in for params within
	 *            the message (params look like "{0}", "{1,date}", "{2,time}"),
	 *            or <code>null</code> if there are none.
	 * @return the resolved message (never <code>null</code>).
	 */
	String getMessage(String code, Object... args);

	/**
	 * Gets all the locales that this application supports. More specifically,
	 * the application's UI can be shown in any of the locales returned by this
	 * method.
	 * 
	 * @see #getLocaleDisplayName(Locale)
	 * @return an unmodifiable set of {@link Locale}s.
	 */
	Set<Locale> getSupportedLocales();

	/**
	 * Gets the name of <code>locale</code> to display in the user interface.
	 * Each locale is shown in its own language. For example, the "en_US" locale
	 * returns "English", the "fi_FI" locale "Suomi", etc.
	 * <p>
	 * If <code>locale</code> is not in the array returned by
	 * {@link #getSupportedLocales()}, an "Unsuppored Locale" string is
	 * returned.
	 * 
	 * @param locale
	 *            the locale whose display name should be returned.
	 * @return the display name of the locale.
	 */
	String getLocaleDisplayName(Locale locale);

	/**
	 * Gets the current locale. The locale is not required to be in the set of
	 * supported locales.
	 * 
	 * @return the current locale (may be <code>null</code>).
	 */
	Locale getLocale();

	/**
	 * Sets the current locale. The locale is not required to be in the set of
	 * supported locales. If it is not, the UI may use a default locale for
	 * localization.
	 * 
	 * @see #addListener(I18NListener)
	 * 
	 * @param locale
	 *            the locale to set.
	 */
	void setLocale(Locale locale);

	/**
	 * Registers a listener to be notified when the locale is changed. A
	 * listener can be registered several times and will be notified once for
	 * each registration. If the listener is <code>null</code>, nothing happens.
	 * 
	 * @param listener
	 *            the listener to add.
	 */
	void addListener(I18NListener listener);

	/**
	 * Unregisters a listener previously registered using
	 * {@link #addListener(I18NListener)}. If the listener was registered
	 * multiple times, it will be notified one time less after this method
	 * invocation. If the listener is <code>null</code> or was never added,
	 * nothing happens.
	 * 
	 * @param listener
	 *            the listener to remove.
	 */
	void removeListener(I18NListener listener);

}
