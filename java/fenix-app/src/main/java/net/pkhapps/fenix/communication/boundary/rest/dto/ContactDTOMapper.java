package net.pkhapps.fenix.communication.boundary.rest.dto;

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
}
