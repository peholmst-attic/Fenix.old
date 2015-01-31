package net.pkhapps.fenix.communication.boundary.rest.dto;

import net.pkhapps.fenix.communication.entity.Group;
import net.pkhapps.fenix.core.boundary.rest.AbstractEntityDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DTO mapper for {@link net.pkhapps.fenix.communication.boundary.rest.dto.GroupDTO} and {@link net.pkhapps.fenix.communication.entity.Group}.
 */
@Component
public class GroupDTOMapper extends AbstractEntityDTOMapper<GroupDTO, Group> {

    private final ContactDTOMapper contactDTOMapper;

    @Autowired
    public GroupDTOMapper(ContactDTOMapper contactDTOMapper) {
        super(GroupDTO.class, Group.class);
        this.contactDTOMapper = contactDTOMapper;
    }

    @Override
    protected void populateDTO(Group source, GroupDTO destination) {
        destination.name = source.getName();
        List<ContactDTO> contacts = source.getMembers().stream().map(contactDTOMapper::toDTO).collect(Collectors.toList());
        Collections.sort(contacts, new ContactComparator());
        destination.contacts = contacts;
    }

    private static class ContactComparator implements Comparator<ContactDTO> {

        private static String nullToEmpty(String s) {
            return s == null ? "" : s;
        }

        @Override
        public int compare(ContactDTO o1, ContactDTO o2) {
            int i = nullToEmpty(o1.name).compareTo(nullToEmpty(o2.name));
            if (i == 0) {
                i = nullToEmpty(o1.firstName).compareTo(nullToEmpty(o2.firstName));
            }
            return i;
        }
    }
}
