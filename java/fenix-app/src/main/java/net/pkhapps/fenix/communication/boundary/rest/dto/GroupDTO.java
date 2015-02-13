package net.pkhapps.fenix.communication.boundary.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.pkhapps.fenix.core.boundary.rest.dto.AbstractEntityDTO;

import java.util.List;

/**
 * DTO representing a group of contacts.
 *
 * @see net.pkhapps.fenix.communication.boundary.rest.dto.GroupMemberDTO
 * @see net.pkhapps.fenix.communication.boundary.rest.dto.ContactDTO
 */
public class GroupDTO extends AbstractEntityDTO {

    @JsonProperty(required = true)
    public String name;
    @JsonProperty(required = true)
    public List<GroupMemberDTO> contacts;
}
