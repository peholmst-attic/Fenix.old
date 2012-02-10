package fenix.base.domain;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * The {@link Address} properties of the annotated element may not be null nor
 * empty.
 * 
 * @author Petter Holmström
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AddressRequiredValidator.class)
@Documented
public @interface AddressRequired {

	String message() default "{fenix.base.domain.constraints.addressRequired}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
