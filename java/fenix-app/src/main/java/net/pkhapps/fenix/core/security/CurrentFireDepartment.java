package net.pkhapps.fenix.core.security;

import net.pkhapps.fenix.core.entity.FireDepartment;

import java.util.Optional;

/**
 * Utility class that is used to bind a {@link net.pkhapps.fenix.core.entity.FireDepartment} to the current thread.
 */
public final class CurrentFireDepartment {

    private static final InheritableThreadLocal<FireDepartment> CURRENT = new InheritableThreadLocal<>();

    private CurrentFireDepartment() {
    }

    /**
     * Returns the {@link net.pkhapps.fenix.core.entity.FireDepartment} bound to the current thread, if any.
     */
    public static Optional<FireDepartment> get() {
        return Optional.ofNullable(CURRENT.get());
    }

    /**
     * Binds the specified {@link net.pkhapps.fenix.core.entity.FireDepartment} to the current thread. Remember
     * to {@link #reset()} it when no longer needed.
     */
    public static void set(FireDepartment fireDepartment) {
        CURRENT.set(fireDepartment);
    }

    /**
     * Clears the current fire department set by {@link #set(net.pkhapps.fenix.core.entity.FireDepartment)}.
     */
    public static void reset() {
        CURRENT.remove();
    }
}
