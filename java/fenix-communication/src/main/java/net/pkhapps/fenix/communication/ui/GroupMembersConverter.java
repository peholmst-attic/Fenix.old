package net.pkhapps.fenix.communication.ui;

import com.vaadin.data.util.converter.Converter;
import net.pkhapps.fenix.communication.entity.Contact;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * TODO Document me!
 */
class GroupMembersConverter implements Converter<String, Collection> {

    @Override
    public Collection convertToModel(String value, Class<? extends Collection> targetType, Locale locale) throws ConversionException {
        throw new ConversionException("This is a one-way converter (from Set of Contacts to String)");
    }

    @Override
    @SuppressWarnings("unchecked")
    public String convertToPresentation(Collection value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        List<String> names = ((Collection<Object>) value).stream().map(o -> ((Contact) o).getDisplayName()).collect(Collectors.toList());
        Collections.sort(names);
        return String.join("; ", names);
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
