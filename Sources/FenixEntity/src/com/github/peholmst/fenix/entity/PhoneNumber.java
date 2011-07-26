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
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.github.peholmst.fenix.entity.types.PhoneNumberType;

/**
 * Embeddable type representing a phone number.
 * 
 * @author Petter Holmström
 */
@Embeddable
public class PhoneNumber implements java.io.Serializable, Cloneable {

    private static final long serialVersionUID = -1561190947850897068L;

    // TODO Add validation annotations

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhoneNumberType type;

    @Column(nullable = false)
    private String areaCode;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private boolean publicNumber;

    public PhoneNumberType getType() {
        return type;
    }

    public void setType(PhoneNumberType type) {
        this.type = type;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isPublicNumber() {
        return publicNumber;
    }

    public void setPublicNumber(boolean publicNumber) {
        this.publicNumber = publicNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((areaCode == null) ? 0 : areaCode.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + (publicNumber ? 1231 : 1237);
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PhoneNumber other = (PhoneNumber) obj;
        if (areaCode == null) {
            if (other.areaCode != null)
                return false;
        } else if (!areaCode.equals(other.areaCode))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (publicNumber != other.publicNumber)
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
