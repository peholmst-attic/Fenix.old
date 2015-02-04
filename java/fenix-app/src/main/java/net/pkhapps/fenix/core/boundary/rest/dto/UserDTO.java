package net.pkhapps.fenix.core.boundary.rest.dto;

import net.pkhapps.fenix.core.boundary.rest.AbstractEntityDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO representing a system user.
 */
public class UserDTO extends AbstractEntityDTO {

    public String email;
    public String firstName;
    public String lastName;
    public boolean enabled;
    public boolean locked;
    public boolean sysadmin;
    // TODO JSON date formats
    public Date passwordExpires;
    public Date accountExpires;
    public Set<UserFireDepartmentRoleDTO> fireDepartmentRoles = new HashSet<>();
}
