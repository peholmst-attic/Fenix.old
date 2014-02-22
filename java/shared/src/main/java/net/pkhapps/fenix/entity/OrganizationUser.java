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

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;

/**
 * Entity class for system users that belong to an {@link Organization}.
 *
 * @author Petter Holmström
 */
@Entity
@DiscriminatorValue("ORGUSER")
public class OrganizationUser extends User {

    @ManyToOne
    @JoinColumn(name = "organization_id")
    @NotNull
    private Organization organization;

    @Enumerated(EnumType.STRING)
    @Column(name = "urole")
    @NotNull
    private OrganizationUserRole role;

    @ElementCollection
    @CollectionTable(name = "user_permissions", joinColumns = @JoinColumn(name = "user_id"))
    private Set<OrganizationUserPermission> permissions = new HashSet<>();

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public OrganizationUserRole getRole() {
        return role;
    }

    public void setRole(OrganizationUserRole role) {
        this.role = role;
    }

    public Set<OrganizationUserPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<OrganizationUserPermission> permissions) {
        if (permissions == null) {
            permissions = Collections.emptySet();
        }
        this.permissions = new HashSet<>(permissions);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        OrganizationUser clone = (OrganizationUser) super.clone();
        clone.permissions = new HashSet<>(permissions);
        return clone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        if (role != null) {
            authorities.add(role);
        }
        authorities.addAll(permissions);
        return authorities;
    }
}
