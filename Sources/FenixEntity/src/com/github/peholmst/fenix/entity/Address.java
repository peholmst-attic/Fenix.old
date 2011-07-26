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

import javax.persistence.Embeddable;

/**
 * Embeddable type representing a Finnish postal address.
 * 
 * @author Petter Holmström
 */
@Embeddable
public class Address implements java.io.Serializable, Cloneable {

    // TODO Add validation annotations

    private static final long serialVersionUID = 8026939213246328624L;

    private String streetOrBox;

    private String number;

    private String appartment;

    private String postalCode;

    private String postOffice;

    public String getStreetOrBox() {
        return streetOrBox;
    }

    public void setStreetOrBox(String streetOrBox) {
        this.streetOrBox = streetOrBox;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(String postOffice) {
        this.postOffice = postOffice;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((appartment == null) ? 0 : appartment.hashCode());
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result
                + ((postOffice == null) ? 0 : postOffice.hashCode());
        result = prime * result
                + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result
                + ((streetOrBox == null) ? 0 : streetOrBox.hashCode());
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
        Address other = (Address) obj;
        if (appartment == null) {
            if (other.appartment != null)
                return false;
        } else if (!appartment.equals(other.appartment))
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (postOffice == null) {
            if (other.postOffice != null)
                return false;
        } else if (!postOffice.equals(other.postOffice))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (streetOrBox == null) {
            if (other.streetOrBox != null)
                return false;
        } else if (!streetOrBox.equals(other.streetOrBox))
            return false;
        return true;
    }

}
