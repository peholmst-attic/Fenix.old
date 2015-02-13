package net.pkhapps.fenix.core.control;

/**
 * Control interface for formatting names. The idea is to support multiple formats depending on the context (sometimes
 * you might want to have the first name first, some times the last name first, etc).
 */
public interface NameFormatter {

    /**
     * Returns the full name composed from the specified first and last names.
     */
    String getFullName(String firstName, String lastName);
}
