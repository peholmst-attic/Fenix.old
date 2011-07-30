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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.github.peholmst.fenix.entity.util.HistoricalEntityBase;
import com.github.peholmst.fenix.entity.util.MultilingualString;
import com.github.peholmst.stuff4vaadin.clone.CloneThis;

/**
 * TODO Document me!
 * 
 * @author Petter Holmström
 */
@Entity
public class RFD extends HistoricalEntityBase {

    private static final long serialVersionUID = 7681818001684807952L;

    // TODO Define RFD entity class

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @CloneThis
    private MultilingualString name;

    public MultilingualString getName() {
        return name;
    }

    public void setName(MultilingualString name) {
        this.name = name;
    }

}
