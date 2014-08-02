package net.pkhapps.fenix.core.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;

/**
 * Base class for entities.
 */
@MappedSuperclass
public abstract class AbstractEntity extends AbstractPersistable<Long> {
    // TODO Add auditing support
}
