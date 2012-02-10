/*
 * Fenix
 * Copyright (C) 2012 Petter Holmström
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fenix.base.domain.localization;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Embeddable;

import fenix.base.domain.annotation.NeverReturnsNull;
import fenix.base.domain.annotation.ValueObject;

/**
 * Value object for storing a text string in multiple languages.
 * 
 * @see Language
 * @see LocalizedStringRequired
 * 
 * @author Petter Holmström
 */
@ValueObject
@Embeddable
public class LocalizedString implements java.io.Serializable {

	private static final long serialVersionUID = -8879106589436183787L;

	protected String valueSv;

	protected String valueFi;

	/**
	 * Default constructor
	 * 
	 * @see #empty()
	 */
	protected LocalizedString() {
	}

	/**
	 * Initializing constructor
	 * 
	 * @see LocalizedStringBuilder#build()
	 */
	protected LocalizedString(Map<Language, String> values) {
		assert values != null : "values must not be null";
		valueSv = values.get(Language.SWEDISH);
		valueFi = values.get(Language.FINNISH);
	}

	/**
	 * Copy constructor
	 * 
	 * @see #copy(LocalizedString)
	 */
	protected LocalizedString(LocalizedString original) {
		assert original != null : "original must not be null";
		this.valueSv = original.valueSv;
		this.valueFi = original.valueFi;
	}

	/**
	 * Creates a new <code>LocalizedStringBuilder</code> with the initial
	 * property values set to the values of this <code>LocalizedString</code>
	 * object.
	 */
	@NeverReturnsNull
	public LocalizedStringBuilder derive() {
		return new LocalizedStringBuilder(this);
	}

	/**
	 * Returns the value in the specified language, or null if no value has been
	 * set.
	 */
	public String get(Language language) {
		switch (language) {
		case SWEDISH:
			return valueSv;
		case FINNISH:
			return valueFi;
		default:
			return null;
		}
	}

	/**
	 * Checks whether a value has been set in the specified language.
	 */
	public boolean isSet(Language language) {
		switch (language) {
		case SWEDISH:
			return valueSv != null;
		case FINNISH:
			return valueFi != null;
		default:
			return false;
		}
	}

	/**
	 * Builder for creating new <code>LocalizedString</code> instances.
	 * 
	 * @author Petter Holmström
	 */
	public static final class LocalizedStringBuilder {

		private Map<Language, String> values = new HashMap<Language, String>();

		private LocalizedStringBuilder() {
		}

		private LocalizedStringBuilder(LocalizedString original) {
			for (Language l : Language.values()) {
				values.put(l, original.get(l));
			}
		}

		@NeverReturnsNull
		public LocalizedStringBuilder set(Language language, String value) {
			values.put(language, value);
			return this;
		}

		/**
		 * Creates and returns a new <code>LocalizedString</code> instance with
		 * the values specified in the builder.
		 */
		@NeverReturnsNull
		public LocalizedString build() {
			return new LocalizedString(values);
		}
	}

	/**
	 * Creates a new <code>LocalizedStringBuilder</code> with the initial values
	 * set to null.
	 */
	public static LocalizedStringBuilder create() {
		return new LocalizedStringBuilder();
	}

	/**
	 * Creates and returns an empty <code>LocalizedString</code>.
	 */
	@NeverReturnsNull
	public static LocalizedString empty() {
		return new LocalizedString();
	}

	/**
	 * Creates and returns a copy of the specified <code>LocalizedString</code>.
	 * If null is specified, this method will return an empty
	 * <code>LocalizedString</code>.
	 */
	@NeverReturnsNull
	public static LocalizedString copy(LocalizedString original) {
		if (original == null) {
			return empty();
		} else {
			return new LocalizedString(original);
		}
	}

	@Override
	public String toString() {
		return String.format("%s[fi=\"%s\", sv=\"%s\", objectIdentity=%s]",
				getClass().getSimpleName(), valueFi, valueSv,
				Integer.toHexString(System.identityHashCode(this)));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valueFi == null) ? 0 : valueFi.hashCode());
		result = prime * result + ((valueSv == null) ? 0 : valueSv.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocalizedString other = (LocalizedString) obj;
		if (valueFi == null) {
			if (other.valueFi != null)
				return false;
		} else if (!valueFi.equals(other.valueFi))
			return false;
		if (valueSv == null) {
			if (other.valueSv != null)
				return false;
		} else if (!valueSv.equals(other.valueSv))
			return false;
		return true;
	}

}
