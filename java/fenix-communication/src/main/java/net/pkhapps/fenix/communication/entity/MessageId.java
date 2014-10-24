package net.pkhapps.fenix.communication.entity;

import java.io.Serializable;

/**
 * Value object identifying a specific message.
 */
public class MessageId implements Serializable {

    private final long id;

    public MessageId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageId messageId = (MessageId) o;

        if (id != messageId.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
