/*
 * Copyright (c) 2010 The original author(s)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.pkhsolutions.fenix.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TODO Document me!
 * 
 * @author petter
 * 
 */
@Entity
public class ProducerFDMember extends AbstractEntity {

	private static final long serialVersionUID = -2970706093671842748L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private ProducerFD producerFD;

	private String lastName;

	private String firstName;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	// TODO Add more information, such as competences, address, membership
	// information, etc.

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public ProducerFD getProducerFD() {
		return producerFD;
	}

	public void setProducerFD(ProducerFD producerFD) {
		this.producerFD = producerFD;
	}

}
