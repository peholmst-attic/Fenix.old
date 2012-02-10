package fenix.base.domain.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The annotated element must not be null and must have a minimum size of 1.
 * This is a composite constraint annotation that combines {@link NotNull} and
 * {@link Size}.
 * 
 * @author Petter Holmström
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@NotNull
@Size(min = 1)
@Documented
public @interface NotEmpty {

	String message() default "{fenix.base.domain.validation.constraints.nonEmpty}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
}
