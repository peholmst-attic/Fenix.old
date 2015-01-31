package net.pkhapps.fenix.communication.boundary.rest.dto;

import net.pkhapps.fenix.core.boundary.rest.AbstractEntityDTO;

import java.util.List;

/**
 * DTO representing a group of contacts.
 */
public class GroupDTO extends AbstractEntityDTO {

    public String name;
    public List<ContactDTO> contacts;
}
