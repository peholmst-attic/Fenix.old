package net.pkhapps.fenix.communication.boundary.rest.dto;

import net.pkhapps.fenix.communication.control.ContactCrud;
import net.pkhapps.fenix.communication.entity.CommunicationMethod;
import net.pkhapps.fenix.communication.entity.Contact;
import net.pkhapps.fenix.communication.entity.GroupMember;
import net.pkhapps.fenix.core.boundary.rest.dto.AbstractEntityDTOMapper;
import net.pkhapps.fenix.core.boundary.rest.support.InvalidReferenceException;
import net.pkhapps.fenix.core.control.NameFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO mapper for {@link net.pkhapps.fenix.communication.boundary.rest.dto.GroupMemberDTO} and {@link net.pkhapps.fenix.communication.entity.GroupMember}.
 */
@Component
public class GroupMemberDTOMapper extends AbstractEntityDTOMapper<GroupMemberDTO, GroupMember> {

    private final NameFormatter nameFormatter;
    private final ContactCrud contactCrud;

    @Autowired
    public GroupMemberDTOMapper(NameFormatter nameFormatter, ContactCrud contactCrud) {
        super(GroupMemberDTO.class, GroupMember.class);
        this.nameFormatter = nameFormatter;
        this.contactCrud = contactCrud;
    }

    @Override
    protected void populateDTO(GroupMember source, GroupMemberDTO destination) {
        final Contact contact = source.getContact();
        if (contact.getSingleName().length() > 0) {
            destination.name = contact.getSingleName();
        } else {
            destination.name = nameFormatter.getFullName(contact.getFirstName(), contact.getLastName());
        }
        destination.contactId = contact.getId();
        destination.cellPhone = contact.getCellPhone();
        destination.email = contact.getEmail();
        final Collection<CommunicationMethod> communicationMethods = source.getCommunicationMethods();
        destination.contactByEmail = communicationMethods.contains(CommunicationMethod.EMAIL);
        destination.contactBySMS = communicationMethods.contains(CommunicationMethod.SMS);
    }

    @Override
    protected void populateEntity(GroupMemberDTO source, GroupMember destination) {
        final Contact contact = contactCrud.findOne(source.contactId).orElseThrow(() -> new InvalidReferenceException("No contact with ID " + source.contactId + " was found"));
        destination.setContact(contact);
        final Set<CommunicationMethod> communicationMethods = new HashSet<>();
        if (source.contactBySMS) {
            communicationMethods.add(CommunicationMethod.SMS);
        }
        if (source.contactByEmail) {
            communicationMethods.add(CommunicationMethod.EMAIL);
        }
        if (contact.getCommunicationMethods().equals(communicationMethods)) {
            destination.getCustomCommunicationMethods().clear();
        } else {
            destination.setCustomCommunicationMethods(communicationMethods);
        }
    }
}
