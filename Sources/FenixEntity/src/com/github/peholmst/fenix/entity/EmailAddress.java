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

import com.github.peholmst.fenix.entity.types.EmailAddressType;

/**
 * Embeddable type representing an e-mail address.
 * 
 * @author Petter Holmström
 */
@Embeddable
public class EmailAddress implements java.io.Serializable, Cloneable {

    // TODO Add validation annotations

    private static final long serialVersionUID = -5522662147935215486L;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmailAddressType type;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean publicAddress;

    public EmailAddressType getType() {
        return type;
    }

    public void setType(EmailAddressType type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPublicAddress() {
        return publicAddress;
    }

    public void setPublicAddress(boolean publicAddress) {
        this.publicAddress = publicAddress;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + (publicAddress ? 1231 : 1237);
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
        EmailAddress other = (EmailAddress) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (publicAddress != other.publicAddress)
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
