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

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity representing an organization. Most of the data and operations of the
 * system are associated with an organization. In most cases, the organization
 * is a fire department.
 *
 * @author Petter Holmström
 * @see OrganizationUser
 */
@Entity
@Table(name = "organizations")
public class Organization extends AbstractEntity {

    @Column(name = "orgname", nullable = false, unique = true)
    @NotBlank
    private String name;

    @ElementCollection
    @CollectionTable(name = "organization_parameters", joinColumns = @JoinColumn(name = "organization_id"))
    @MapKeyColumn(name = "param_name")
    @Column(name = "param_value")
    private Map<String, String> parameters = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        if (parameters == null) {
            parameters = Collections.emptyMap();
        }
        this.parameters = parameters;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Organization clone = (Organization) super.clone();
        clone.parameters = new HashMap<>(parameters);
        return clone;
    }

}
