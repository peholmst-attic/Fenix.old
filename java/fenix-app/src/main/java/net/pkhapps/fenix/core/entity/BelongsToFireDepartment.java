package net.pkhapps.fenix.core.entity;

/**
 * Interface to be implemented by classes whose instances belong to exactly one {@link net.pkhapps.fenix.core.entity.FireDepartment}.
 */
public interface BelongsToFireDepartment {

    /**
     * Returns the fire department that this object belongs to. Once the object has been properly initialized,
     * this method must never return {@code null}.
     */
    FireDepartment getFireDepartment();
}
