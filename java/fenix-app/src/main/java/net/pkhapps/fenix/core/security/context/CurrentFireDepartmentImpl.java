package net.pkhapps.fenix.core.security.context;

import net.pkhapps.fenix.core.entity.FireDepartment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Default implementation of {@link net.pkhapps.fenix.core.security.context.CurrentFireDepartment}.
 */
@Component
class CurrentFireDepartmentImpl implements CurrentFireDepartment {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentFireDepartment.class);
    private final InheritableThreadLocal<FireDepartment> CURRENT = new InheritableThreadLocal<>();

    @Override
    public Optional<FireDepartment> getFireDepartment() {
        return Optional.ofNullable(CURRENT.get());
    }

    @Override
    public FireDepartment requireFireDepartment() throws NoSuchFireDepartmentException {
        return getFireDepartment().orElseThrow(() -> new NoSuchFireDepartmentException());
    }

    @Override
    public void set(FireDepartment fireDepartment) {
        LOGGER.debug("Setting current fire department to {}", fireDepartment);
        CURRENT.set(fireDepartment);
    }

    @Override
    public void reset() {
        LOGGER.debug("Resetting current fire department");
        CURRENT.remove();
    }
}
