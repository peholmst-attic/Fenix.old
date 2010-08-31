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

/**
 * This is a listener interface that allows implementors to be notified when the
 * current locale of an {@link I18N} instance is changed.
 * 
 * @see I18N#getLocale()
 * @see I18N#setLocale(Locale)
 * @see I18N#addListener(I18NListener)
 * @see I18N#removeListener(I18NListener)
 * 
 * @author Petter Holmström
 */
public interface I18NListener extends Serializable {

	/**
	 * Called by an {@link I18N} instance when the current locale has been
	 * changed.
	 * 
	 * @param source
	 *            the source of the event.
	 * @param oldLocale
	 *            the old locale.
	 * @param newLocale
	 *            the new locale.
	 */
	void localeChanged(I18N source, Locale oldLocale, Locale newLocale);

}
