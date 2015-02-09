package net.pkhapps.fenix.core.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Exception thrown when validation of data fails.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Validation failed")
public class ValidationFailedException extends Exception {

    private final Set<ConstraintViolation<?>> constraintViolations;

    public ValidationFailedException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        this.constraintViolations = Collections.unmodifiableSet(Objects.requireNonNull(constraintViolations));
    }

    /**
     * Throws a {@link net.pkhapps.fenix.core.validation.ValidationFailedException} if the specified set of
     * constraint violations is not empty.
     */
    public static void throwIfNotEmpty(Set<? extends ConstraintViolation<?>> constraintViolations) throws ValidationFailedException {
        if (!constraintViolations.isEmpty()) {
            throw new ValidationFailedException(constraintViolations);
        }
    }

    /**
     * Returns a set of constraint violations.
     */
    public Set<ConstraintViolation<?>> getConstraintViolations() {
        return constraintViolations;
    }
}
