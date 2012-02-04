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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * There are different user classes in Fenix. Each user class is represented by
 * a concrete entity class that extends this entity class.
 * 
 * @author Petter Holmström
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User implements java.io.Serializable {

	private static final long serialVersionUID = -5325589982640657942L;

	@Id
	@GeneratedValue
	protected Long userId;

	@Column(nullable = false, unique = true)
	protected String username;

	protected String firstName;

	protected String lastName;

	protected String email;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private static final InheritableThreadLocal<User> currentUser = new InheritableThreadLocal<User>();

	/**
	 * Returns the User instance bound to the current thread, or null if no user
	 * has been bound.
	 * 
	 * @see #setCurrent(User)
	 */
	public static User getCurrent() {
		return currentUser.get();
	}

	/**
	 * Bounds the specified User instance to the current thread. The User can
	 * also be unbound by passing in null.
	 */
	public static void setCurrent(User user) {
		if (user == null) {
			currentUser.remove();
		} else {
			currentUser.set(user);
		}
	}
}
