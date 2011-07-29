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
package com.github.peholmst.fenix.entity.util;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This entity is used to implement sequences using tables instead of native
 * database sequences. Appropriate transaction locking must be used when the
 * sequence values are incremented.
 * 
 * @see SequenceType
 * 
 * @author Petter Holmström
 */
@Entity
@Table(name = "SEQUENCETABLE")
public class Sequence implements java.io.Serializable {

    private static final long serialVersionUID = -8080081503160548472L;

    @Id
    @Enumerated(EnumType.STRING)
    private SequenceType sequenceType;

    @Column(nullable = false)
    private long sequenceValue = 0;

    public SequenceType getSequenceType() {
        return sequenceType;
    }

    public void setSequenceType(SequenceType sequenceType) {
        this.sequenceType = sequenceType;
    }

    public long getSequenceValue() {
        return sequenceValue;
    }

    public void setSequenceValue(long sequenceValue) {
        this.sequenceValue = sequenceValue;
    }

    public long incrementAndReturnSequenceValue() {
        return ++sequenceValue;
    }
}
