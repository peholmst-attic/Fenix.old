/*
 * Fenix
 * Copyright (C) 2012 Petter Holmström
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
package fenix.base.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import fenix.base.domain.annotation.AggregateRoot;
import fenix.base.domain.annotation.NeverReturnsNull;
import fenix.base.domain.localization.Language;
import fenix.base.domain.localization.LocalizedString;
import fenix.base.domain.localization.LocalizedStringRequired;

/**
 * Entity representing a Finnish municipality. The municipality has to have its
 * name stored in both Finnish and Swedish. If it only has a name in one
 * language, that name is used for the other language as well.
 * 
 * @author Petter Holmström
 */
@AggregateRoot
@Entity
public class Municipality extends BaseEntity implements SoftDeletable {

	private static final long serialVersionUID = -3893071221875766279L;

	@Embedded
	@LocalizedStringRequired(requiredLanguages = { Language.SWEDISH,
			Language.FINNISH })
	protected LocalizedString name = LocalizedString.empty();

	protected boolean deleted = false;

	@Override
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * INTERNAL: This method is for internal use only and should never be
	 * invoked by clients.
	 */
	void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@NeverReturnsNull
	public LocalizedString getName() {
		return LocalizedString.copy(name);
	}

	public void setName(LocalizedString name) {
		this.name = LocalizedString.copy(name);
	}
}
