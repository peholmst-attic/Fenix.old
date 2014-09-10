package net.pkhapps.fenix.communication.ui;

import com.vaadin.ui.OptionGroup;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import org.vaadin.spring.i18n.I18N;

/**
 * Option group of {@link net.pkhapps.fenix.communication.entity.CommunicationMethod}s.
 */
class CommunicationMethodField extends OptionGroup {

    private final I18N i18n;

    CommunicationMethodField(String caption, I18N i18n) {
        super(caption);
        this.i18n = i18n;
        for (CommunicationMethod cm : CommunicationMethod.values()) {
            addItem(cm);
            setItemCaption(cm, i18n.get(String.format("%s.%s.caption",
                    CommunicationMethod.class.getCanonicalName(), cm.name())));
        }
    }

    CommunicationMethodField(I18N i18n) {
        this(null, i18n);
    }
}
