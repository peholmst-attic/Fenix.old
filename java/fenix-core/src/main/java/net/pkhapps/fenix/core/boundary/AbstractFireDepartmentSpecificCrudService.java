package net.pkhapps.fenix.core.boundary;

import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;
import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.entity.FireDepartmentSpecificEntityRepository;
import net.pkhapps.fenix.core.security.SessionInfo;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.OptimisticLockingFailureException;

import javax.validation.Validator;
import java.util.List;

/**
 * Abstract base class for {@link net.pkhapps.fenix.core.boundary.CrudService} implementations that work with
 * {@link net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity} instances.
 */
public abstract class AbstractFireDepartmentSpecificCrudService<E extends AbstractFireDepartmentSpecificEntity, R extends FireDepartmentSpecificEntityRepository<E>> extends AbstractCrudService<E, R> {

    private final SessionInfo sessionInfo;

    protected AbstractFireDepartmentSpecificCrudService(Validator validator, R repository, ApplicationContext applicationContext, SessionInfo sessionInfo) {
        super(validator, repository, applicationContext);
        this.sessionInfo = sessionInfo;
    }

    protected SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    @Override
    public E save(E entity) throws ValidationFailedException, OptimisticLockingFailureException {
        final FireDepartment currentFireDepartment = getSessionInfo().getCurrentFireDepartment();
        if (entity.getFireDepartment() == null) {
            logger.trace("Assigning {} to fire department {}", entity, currentFireDepartment);
            entity.setFireDepartment(currentFireDepartment);
        }
        sessionInfo.checkFireDepartment(entity);
        return super.save(entity);
    }

    @Override
    public void delete(E entity) {
        getSessionInfo().checkFireDepartment(entity);
        super.delete(entity);
    }

    @Override
    public List<E> findAll() {
        return getRepository().findByFireDepartment(getSessionInfo().getCurrentFireDepartment());
    }
}
