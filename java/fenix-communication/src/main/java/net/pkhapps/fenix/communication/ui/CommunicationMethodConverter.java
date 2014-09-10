package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.util.converter.Converter;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import org.vaadin.spring.i18n.I18N;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Converter that converts a collection of {@link net.pkhapps.fenix.communication.entity.CommunicationMethod} to a localized string.
 */
class CommunicationMethodConverter implements Converter<String, Collection> {

    private final I18N i18n;

    CommunicationMethodConverter(I18N i18n) {
        this.i18n = i18n;
    }

    @Override
    public Collection convertToModel(String s, Class<? extends Collection> aClass, Locale locale) throws ConversionException {
        throw new ConversionException("This is a one-way converter (from CommunicationMethod to String)");
    }

    @Override
    public String convertToPresentation(Collection communicationMethods, Class<? extends String> aClass, Locale locale) throws ConversionException {
        StringBuilder sb = new StringBuilder();
        List<CommunicationMethod> communicationMethodList = new ArrayList<CommunicationMethod>(communicationMethods);
        Collections.sort(communicationMethodList);
        for (Iterator<CommunicationMethod> it = communicationMethodList.iterator(); it.hasNext(); ) {
            CommunicationMethod cm = it.next();
            sb.append(i18n.get(String.format("%s.%s.caption",
                    CommunicationMethod.class.getCanonicalName(), cm.name())));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public Class<Collection> getModelType() {
        return Collection.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
