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

    public ValidationFailedException(Set<ConstraintViolation<?>> constraintViolations) {
        this.constraintViolations = Collections.unmodifiableSet(Objects.requireNonNull(constraintViolations));
    }

    public Set<ConstraintViolation<?>> getConstraintViolations() {
        return constraintViolations;
    }

}
