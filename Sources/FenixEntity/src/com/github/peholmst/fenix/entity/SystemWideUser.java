/*
 * Copyright (c) 2011 The original developers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.peholmst.fenix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.github.peholmst.fenix.entity.types.SystemWideUserRole;

/**
 * TODO Document me!
 * 
 * @author Petter Holmström
 */
@Entity
public class SystemWideUser extends User {

    private static final long serialVersionUID = 2507754486222735721L;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SystemWideUserRole role;

    public SystemWideUserRole getRole() {
        return role;
    }

    public void setRole(SystemWideUserRole role) {
        this.role = role;
    }

}
