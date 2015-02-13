package net.pkhapps.fenix.communication.boundary.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.pkhapps.fenix.core.boundary.rest.dto.AbstractEntityDTO;

/**
 * DTO representing a contact group member.
 *
 * @see net.pkhapps.fenix.communication.boundary.rest.dto.GroupDTO
 * @see net.pkhapps.fenix.communication.boundary.rest.dto.ContactDTO
 */
public class GroupMemberDTO extends AbstractEntityDTO {

    @JsonProperty(required = true)
    public Long contactId;
    public String name;
    public String email;
    public String cellPhone;
    @JsonProperty(required = true)
    public boolean contactByEmail;
    @JsonProperty(required = true)
    public boolean contactBySMS;
}
