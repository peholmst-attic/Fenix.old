package net.pkhapps.fenix.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Spring {@link org.springframework.stereotype.Component} annotation for Controls.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Control {
    String value() default "";
}
