package net.pkhapps.fenix.core.annotations;

import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

/**
 * Stereotype annotation for Spring's {@code session} scope.
 */
@Scope("session")
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionScope {
}
