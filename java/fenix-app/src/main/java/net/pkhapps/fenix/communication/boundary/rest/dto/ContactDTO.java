package net.pkhapps.fenix.communication.boundary.rest.dto;

import net.pkhapps.fenix.core.boundary.rest.AbstractEntityDTO;

/**
 * DTO representing a contact.
 */
public class ContactDTO extends AbstractEntityDTO {

    public String name;
    public String firstName;
    public String email;
    public String cellPhone;
    public boolean contactByEmail;
    public boolean contactBySMS;
}
