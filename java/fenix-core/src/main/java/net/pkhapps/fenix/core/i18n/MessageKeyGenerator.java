package net.pkhapps.fenix.core.i18n;

import java.io.Serializable;

/**
 * Created by peholmst on 2014-07-10.
 */
public class MessageKeyGenerator implements Serializable {

    private final Class<?> owningClass;

    public MessageKeyGenerator(Class<?> owningClass) {
        this.owningClass = owningClass;
    }

    public String key(String suffix) {
        return owningClass.getCanonicalName() + "." + suffix;
    }
}
