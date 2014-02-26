/*
 * Fenix
 * Copyright (C) 2014 Petter Holmström
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.pkhapps.fenix.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enumeration of the roles of an {@link OrganizationUser}. An organization user
 * always holds exactly one role.
 *
 * @author Petter Holmström
 */
public enum OrganizationUserRole implements GrantedAuthority {


    /**
     * The user is allowed to manage the organization information and the
     * organization users.
     */
    SUPERUSER,
    /**
     * Ordinary user.
     */
    USER;

    String SUPERUSER_ROLE_NAME = "ROLE_SUPERUSER";
    String USER_ROLE_NAME = "ROLE_USER";

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
