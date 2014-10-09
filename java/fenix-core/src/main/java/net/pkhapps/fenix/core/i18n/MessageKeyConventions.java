package net.pkhapps.fenix.core.i18n;

import java.io.Serializable;

/**
 * Helper class for generating keys for translatable messages. By convention, a key is composed of the canonical name
 * of the owning class, and a suffix which can be anything.
 */
public class MessageKeyConventions implements Serializable {

    private final Class<?> owningClass;

    /**
     * Creates a new {@code MessageKeyConventions} instance.
     *
     * @param owningClass the class that owns the instance.
     */
    public MessageKeyConventions(Class<?> owningClass) {
        this.owningClass = owningClass;
    }

    /**
     * Constructs a message key from the owning class name and the specified suffix.
     *
     * @param suffix the suffix.
     * @return the message key.
     */
    public String key(String suffix) {
        return owningClass.getCanonicalName() + "." + suffix;
    }
}
