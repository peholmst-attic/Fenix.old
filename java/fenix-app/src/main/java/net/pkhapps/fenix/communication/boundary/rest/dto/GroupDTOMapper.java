package net.pkhapps.fenix.communication.boundary.rest.dto;

import net.pkhapps.fenix.communication.entity.Group;
import net.pkhapps.fenix.communication.entity.GroupMember;
import net.pkhapps.fenix.core.boundary.rest.dto.AbstractEntityDTOMapper;
import net.pkhapps.fenix.core.validation.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * DTO mapper for {@link net.pkhapps.fenix.communication.boundary.rest.dto.GroupDTO} and {@link net.pkhapps.fenix.communication.entity.Group}.
 */
@Component
public class GroupDTOMapper extends AbstractEntityDTOMapper<GroupDTO, Group> {

    private final GroupMemberDTOMapper groupMemberDTOMapper;

    @Autowired
    public GroupDTOMapper(GroupMemberDTOMapper groupMemberDTOMapper) {
        super(GroupDTO.class, Group.class);
        this.groupMemberDTOMapper = groupMemberDTOMapper;
    }

    @Override
    protected void populateDTO(Group source, GroupDTO destination) {
        destination.name = source.getName();
        final List<GroupMemberDTO> contacts = source.getMembers().stream().map(groupMemberDTOMapper::toDTO).collect(Collectors.toList());
        Collections.sort(contacts, new ContactComparator());
        destination.contacts = contacts;
    }

    @Override
    protected void populateEntity(GroupDTO source, Group destination) throws ConflictException {
        destination.setName(source.name);
        final Map<Long, GroupMemberDTO> contactIdToMemberMap = source.contacts.stream().collect(Collectors.toMap(dto -> dto.contactId, dto -> dto));
        final Set<GroupMember> newGroupMembers = new HashSet<>();
        for (GroupMember oldGroupMember : destination.getMembers()) {
            final GroupMemberDTO dto = contactIdToMemberMap.remove(oldGroupMember.getId());
            if (dto != null) {
                newGroupMembers.add(groupMemberDTOMapper.toExistingEntity(dto, oldGroupMember));
            }
        }
        newGroupMembers.addAll(contactIdToMemberMap.values().stream().map(groupMemberDTOMapper::toEntity).collect(Collectors.toList()));
        destination.setMembers(newGroupMembers);
    }

    private static class ContactComparator implements Comparator<GroupMemberDTO> {

        private static String nullToEmpty(String s) {
            return s == null ? "" : s;
        }

        @Override
        public int compare(GroupMemberDTO o1, GroupMemberDTO o2) {
            return nullToEmpty(o1.name).compareTo(nullToEmpty(o2.name));
        }
    }
}
