package net.pkhapps.fenix.core.components;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import net.pkhapps.fenix.core.i18n.MessageKeyGenerator;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import net.pkhapps.fenix.theme.FenixTheme;
import org.vaadin.spring.i18n.I18N;

import java.util.Optional;

/**
 * TODO Document me!
 */
public abstract class AbstractForm<E> extends FormLayout {

    private final MessageKeyGenerator messages = new MessageKeyGenerator(getClass());
    private final I18N i18n;
    private BeanFieldGroup<E> binder;

    /**
     * @param i18n
     */
    protected AbstractForm(I18N i18n) {
        this.i18n = i18n;
        addStyleName(FenixTheme.FORMLAYOUT_LIGHT);
        createFields();
        binder = createBinder();
    }

    protected MessageKeyGenerator getMessages() {
        return messages;
    }

    protected I18N getI18N() {
        return i18n;
    }

    protected BeanFieldGroup<E> getBinder() {
        return binder;
    }

    /**
     *
     */
    protected abstract void createFields();

    /**
     * @return
     */
    protected BeanFieldGroup<E> createBinder() {
        final BeanFieldGroup<E> fieldGroup = new BeanFieldGroup<>(getBeanClass());
        fieldGroup.bindMemberFields(this);
        return fieldGroup;
    }

    /**
     * @param bean
     */
    public void setBean(E bean) {
        binder.setItemDataSource(bean);
    }

    /**
     * @return
     */
    public abstract Class<E> getBeanClass();

    /**
     * @return
     */
    public Optional<E> commit() {
        try {
            binder.commit();
            return Optional.of(binder.getItemDataSource().getBean());
        } catch (FieldGroup.CommitException e) {
            return Optional.empty();
        }
    }

    /**
     *
     */
    public void discard() {
        binder.discard();
    }

    /**
     * @param validationErrors
     */
    public void showValidationErrors(ValidationFailedException validationErrors) {
        // TODO Implement me!
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void focus() {
        if (getComponentCount() > 0) {
            Component firstComponent = getComponent(0);
            if (firstComponent instanceof Focusable) {
                ((Focusable) firstComponent).focus();
            }
        }
    }

    @Override
    public boolean isReadOnly() {
        return binder.isReadOnly();
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        binder.setReadOnly(readOnly);
    }

    /**
     * @param keySuffix
     * @param arguments
     * @return
     */
    protected String getMessage(String keySuffix, Object... arguments) {
        return getI18N().get(getMessages().key(keySuffix), arguments);
    }
}
