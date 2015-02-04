package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.FireDepartment;

/**
 * Class listing the roles a user of the system can hold. Some roles are global, others are specific to a fire
 * department.
 */
public final class UserRoles {

    /**
     * The user is a system administrator.
     */
    public static final String ROLE_SYSTEM_ADMINISTRATOR = "ROLE_ADMIN";
    /**
     * The user is an administrator within the fire department.
     */
    public static final String ROLE_FD_ADMINISTRATOR = "ROLE_FD_ADMIN";
    /**
     * The user is a power user within the fire department.
     */
    public static final String ROLE_FD_POWER_USER = "ROLE_FD_POWER_USER";
    /**
     * The user is an ordinary user within the fire department. This often means read-only access.
     */
    public static final String ROLE_FD_USER = "ROLE_FD_USER";

    private UserRoles() {
    }

    /**
     * Checks whether the specified role is a fire department specific role.
     *
     * @see #makeFireDepartmentSpecificRole(String, net.pkhapps.fenix.core.entity.FireDepartment)
     */
    public static boolean isFireDepartmentSpecificRole(String role) {
        return role.startsWith("ROLE_FD_");
    }

    /**
     * Creates a fire department specific role that is granted to a user. The role consists of a role prefix
     * (one of the {@code ROLE_FD_}-roles defined in this class) and the fire department ID.
     *
     * @see net.pkhapps.fenix.core.security.FireDepartmentAwareRoleVoter
     * @see net.pkhapps.fenix.core.security.FenixUserDetails
     */
    public static String makeFireDepartmentSpecificRole(String role, FireDepartment fireDepartment) {
        return role + fireDepartment.getId();
    }
}
