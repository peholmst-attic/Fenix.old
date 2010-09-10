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
package net.pkhsolutions.fenix.domain;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

/**
 * TODO Document me!
 * 
 * @author petter
 * 
 */
@Entity
public class LocalizedString extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2140438923967148648L;

	@ElementCollection
	private Map<String, String> valueMap = new HashMap<String, String>();

	/**
	 * 
	 * @param locale
	 * @return
	 */
	public String getValue(Locale locale) {
		return valueMap.get(locale.getISO3Language());
	}

	/**
	 * 
	 * @param locale
	 * @param value
	 */
	public void setValue(Locale locale, String value) {
		valueMap.put(locale.getISO3Language(), value);
	}
}
