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

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * TODO Document me!
 * 
 * @author petter
 * 
 */
@Entity
public class Section extends AbstractEntity {

	private static final long serialVersionUID = 2952954068488100574L;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private ProducerFD producerFD;
	public static final String PROP_PRODUCER_FD = "producerFD";

	@OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private LocalizedString name = new LocalizedString();
	public static final String PROP_NAME = "name";

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	private Set<ProducerFDMember> members = new HashSet<ProducerFDMember>();
	public static final String PROP_MEMBERS = "members";

	public ProducerFD getProducerFD() {
		return producerFD;
	}

	public void setProducerFD(ProducerFD producerFD) {
		this.producerFD = producerFD;
	}

	public LocalizedString getName() {
		return name;
	}

	public void setName(LocalizedString name) {
		this.name = name;
	}

	public Set<ProducerFDMember> getMembers() {
		return members;
	}

	public void setMembers(Set<ProducerFDMember> members) {
		this.members = members;
	}
}
