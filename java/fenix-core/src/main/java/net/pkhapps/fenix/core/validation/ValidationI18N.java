package net.pkhapps.fenix.core.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.i18n.I18N;

import javax.validation.ConstraintViolation;
import javax.validation.MessageInterpolator;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Locale;

/**
 * TODO Document me
 */
@Component
public class ValidationI18N {

    private final MessageInterpolator messageInterpolator;
    private final I18N i18n;

    /**
     * @param messageInterpolator
     * @param i18n
     */
    @Autowired
    public ValidationI18N(MessageInterpolator messageInterpolator, I18N i18n) {
        this.messageInterpolator = messageInterpolator;
        this.i18n = i18n;
    }

    /**
     * @param violation
     * @param locale
     * @return
     */
    public String getMessage(ConstraintViolation<?> violation, Locale locale) {
        return messageInterpolator.interpolate(violation.getMessageTemplate(), new MessageInterpolator.Context() {
            @Override
            public ConstraintDescriptor<?> getConstraintDescriptor() {
                return violation.getConstraintDescriptor();
            }

            @Override
            public Object getValidatedValue() {
                return violation.getInvalidValue();
            }

            @Override
            public <T> T unwrap(Class<T> tClass) {
                return violation.unwrap(tClass);
            }
        }, locale);
    }

    /**
     * @param violation
     * @return
     */
    public String getMessage(ConstraintViolation<?> violation) {
        return getMessage(violation, i18n.getLocale());
    }
}
