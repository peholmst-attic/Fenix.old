package net.pkhapps.fenix.communication.boundary.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.pkhapps.fenix.core.boundary.rest.dto.AbstractEntityDTO;

/**
 * DTO representing a contact.
 */
public class ContactDTO extends AbstractEntityDTO {

    @JsonProperty(required = true)
    public String name;
    public String firstName;
    @JsonProperty(required = true)
    public String email;
    @JsonProperty(required = true)
    public String cellPhone;
    @JsonProperty(required = true)
    public boolean contactByEmail;
    @JsonProperty(required = true)
    public boolean contactBySMS;
}