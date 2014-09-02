package net.pkhapps.fenix.core.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Base class for entities.
 */
@MappedSuperclass
public abstract class AbstractEntity extends AbstractPersistable<Long> implements Cloneable {

    @Column(name = "rev", nullable = false)
    @Version
    private Long optLockVersion;

    protected AbstractEntity() {
    }

    protected Long getOptLockVersion() {
        return optLockVersion;
    }

    protected void setOptLockVersion(Long optLockVersion) {
        this.optLockVersion = optLockVersion;
    }

    /**
     * Clears the persistence ID information from the entity.
     */
    public void setNew() {
        setId(null);
        setOptLockVersion(null);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Wrapper for the {@link #clone()} method that does not throw any checked exceptions.
     */
    public AbstractEntity copy() {
        try {
            return (AbstractEntity) clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException("The clone() method has not been implemented properly", ex);
        }
    }
}
