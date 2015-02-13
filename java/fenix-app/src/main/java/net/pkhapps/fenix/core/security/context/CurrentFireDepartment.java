package net.pkhapps.fenix.core.security.context;

import net.pkhapps.fenix.core.entity.BelongsToFireDepartment;
import net.pkhapps.fenix.core.entity.FireDepartment;

import java.util.Optional;

/**
 * Utility interface that is used to bind a {@link net.pkhapps.fenix.core.entity.FireDepartment} to the current thread,
 * and access it in different ways. Use it by requesting an instance of it from
 * the application context.
 *
 * @see net.pkhapps.fenix.core.security.context.FireDepartmentRetriever
 */
public interface CurrentFireDepartment {

    /**
     * Returns the {@link net.pkhapps.fenix.core.entity.FireDepartment} bound to the current thread, if any.
     */
    Optional<FireDepartment> getFireDepartment();

    /**
     * Returns the {@link net.pkhapps.fenix.core.entity.FireDepartment} bound to the current thread, or throws
     * an exception.
     */
    FireDepartment requireFireDepartment() throws NoSuchFireDepartmentException;

    /**
     * Binds the specified {@link net.pkhapps.fenix.core.entity.FireDepartment} to the current thread. Remember
     * to {@link #reset()} it when no longer needed.
     */
    void set(FireDepartment fireDepartment);

    /**
     * Clears the current fire department set by {@link #set(net.pkhapps.fenix.core.entity.FireDepartment)}.
     */
    void reset();

    /**
     * Checks that the specified object belongs to the same fire department as the one bound to the current thread.
     * If the fire departments do not match, or no fire department is bound to the current thread, an exception
     * is thrown.
     */
    void checkFireDepartment(BelongsToFireDepartment belongsToFireDepartment) throws WrongFireDepartmentException;
}
