package net.pkhapps.fenix.core.boundary.rest.dto;

/**
 * DTO representing the role a user has within a specific fire department. The fire department's name is included
 * to make it easier to e.g. show a drop down list of available fire departments in the UI.
 */
public class UserFireDepartmentRoleDTO {
    public Long id;
    public String name;
    public String role;

    public UserFireDepartmentRoleDTO() {
    }

    public UserFireDepartmentRoleDTO(Long id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }
}
