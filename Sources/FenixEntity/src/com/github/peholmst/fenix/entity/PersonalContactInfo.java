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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import com.github.peholmst.fenix.entity.types.EmailAddressType;
import com.github.peholmst.fenix.entity.types.PhoneNumberType;

@Embeddable
public class PersonalContactInfo implements java.io.Serializable, Cloneable {

    private static final long serialVersionUID = 166405740615646932L;

    @ElementCollection
    private Set<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();

    @ElementCollection
    private Set<EmailAddress> emailAddresses = new HashSet<EmailAddress>();

    public Set<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Set<EmailAddress> getEmailAddresses() {
        return emailAddresses;
    }

    public Collection<PhoneNumber> getPhoneNumbersOfType(PhoneNumberType type) {
        final HashSet<PhoneNumber> result = new HashSet<PhoneNumber>();
        for (PhoneNumber number : phoneNumbers) {
            if (number.getType().equals(type)) {
                result.add(number);
            }
        }
        return result;
    }

    public Collection<EmailAddress> getEmailAddressesOfType(
            EmailAddressType type) {
        final HashSet<EmailAddress> result = new HashSet<EmailAddress>();
        for (EmailAddress email : emailAddresses) {
            if (email.getType().equals(type)) {
                result.add(email);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((emailAddresses == null) ? 0 : emailAddresses.hashCode());
        result = prime * result
                + ((phoneNumbers == null) ? 0 : phoneNumbers.hashCode());
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
        PersonalContactInfo other = (PersonalContactInfo) obj;
        if (emailAddresses == null) {
            if (other.emailAddresses != null)
                return false;
        } else if (!emailAddresses.equals(other.emailAddresses))
            return false;
        if (phoneNumbers == null) {
            if (other.phoneNumbers != null)
                return false;
        } else if (!phoneNumbers.equals(other.phoneNumbers))
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
