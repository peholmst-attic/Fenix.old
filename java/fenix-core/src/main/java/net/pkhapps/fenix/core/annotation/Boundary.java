package net.pkhapps.fenix.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Spring {@link org.springframework.stereotype.Component} annotation for Boundaries.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Boundary {
    String value() default "";
}
