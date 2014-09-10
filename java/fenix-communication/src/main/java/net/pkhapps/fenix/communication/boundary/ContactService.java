package net.pkhapps.fenix.communication.boundary;

import net.pkhapps.fenix.communication.entity.Contact;

import java.util.List;

/**
 * Service for working with {@link net.pkhapps.fenix.communication.entity.Contact}s.
 */
public interface ContactService {

    /**
     * Saves the specified contact and returns it. The returned instance may, but is not required to,
     * be the same instance as the one passed in.
     */
    Contact save(Contact contact);

    /**
     * Deletes the specified contact. If it does not exist, nothing happens.
     */
    void delete(Contact contact);

    /**
     * Returns all contacts.
     */
    List<Contact> findAll();

}
