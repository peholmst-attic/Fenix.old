package net.pkhapps.fenix.communication.boundary.rest.dto;

import com.google.common.base.Strings;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.core.boundary.rest.AbstractEntityDTOMapper;
import org.springframework.stereotype.Component;

/**
 * DTO mapper for {@link net.pkhapps.fenix.communication.boundary.rest.dto.ContactDTO} and {@link net.pkhapps.fenix.communication.entity.Contact}.
 */
@Component
public class ContactDTOMapper extends AbstractEntityDTOMapper<ContactDTO, Contact> {

    public ContactDTOMapper() {
        super(ContactDTO.class, Contact.class);
    }

    @Override
    protected void populateDTO(Contact source, ContactDTO destination) {
        if (source.getSingleName().length() > 0) {
            destination.name = source.getSingleName();
        } else {
            destination.firstName = source.getFirstName();
            destination.name = source.getLastName();
        }
        destination.cellPhone = source.getCellPhone();
        destination.email = source.getEmail();
        destination.contactByEmail = source.getCommunicationMethods().contains(CommunicationMethod.EMAIL);
        destination.contactBySMS = source.getCommunicationMethods().contains(CommunicationMethod.SMS);
    }

    @Override
    protected void populateEntity(ContactDTO source, Contact destination) {
        if (source.firstName == null || source.firstName.isEmpty()) {
            destination.setLastName("");
            destination.setFirstName("");
            destination.setSingleName(source.name);
        } else {
            destination.setLastName(source.name);
            destination.setFirstName(Strings.nullToEmpty(source.firstName));
            destination.setSingleName("");
        }
        destination.setCellPhone(Strings.nullToEmpty(source.cellPhone));
        destination.setEmail(Strings.nullToEmpty(source.email));
        destination.getCommunicationMethods().clear();
        if (source.contactBySMS) {
            destination.getCommunicationMethods().add(CommunicationMethod.SMS);
        }
        if (source.contactByEmail) {
            destination.getCommunicationMethods().add(CommunicationMethod.EMAIL);
        }
    }
}
