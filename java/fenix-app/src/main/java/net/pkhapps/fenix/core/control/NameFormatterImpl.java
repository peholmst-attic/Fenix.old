package net.pkhapps.fenix.core.control;

import net.pkhapps.fenix.core.annotation.Control;

/**
 * Default implementation of {@link net.pkhapps.fenix.core.control.NameFormatter} that always uses the format
 * "firstName lastName".
 */
@Control
class NameFormatterImpl implements NameFormatter {

    @Override
    public String getFullName(String firstName, String lastName) {
        return String.format("%s %s", firstName, lastName).trim();
    }
}
