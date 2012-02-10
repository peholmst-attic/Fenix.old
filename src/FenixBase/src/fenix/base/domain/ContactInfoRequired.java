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
package fenix.base.domain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Some (or all) of the {@link ContactInfo} properties of the annotated element
 * may not be null nor empty.
 * 
 * @author Petter Holmström
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContactInfoRequiredValidator.class)
@Documented
public @interface ContactInfoRequired {

	String message() default "{fenix.base.domain.constraints.contactInfoRequired}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * Enumeration of the {@link ContactInfo} properties.
	 * 
	 * @author Petter Holmström
	 */
	enum Property {
		PHONE, FAX, EMAIL, WEBSITE
	}

	/**
	 * The properties that are required (all by default).
	 */
	Property[] value() default { Property.PHONE, Property.FAX, Property.EMAIL,
			Property.WEBSITE };

}
