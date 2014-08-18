package net.pkhapps.fenix.core.validation;

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Exception thrown when validation of data fails.
 */
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

    public Set<ConstraintViolation<?>> getConstraintViolations() {
        return constraintViolations;
    }
}
