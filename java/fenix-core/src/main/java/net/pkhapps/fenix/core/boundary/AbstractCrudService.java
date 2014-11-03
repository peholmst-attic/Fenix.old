package net.pkhapps.fenix.core.boundary;

import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.validation.ValidationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Abstract base class for {@link net.pkhapps.fenix.core.boundary.CrudService} implementations.
 */
@SuppressWarnings("unchecked")
public abstract class AbstractCrudService<E extends AbstractEntity, R extends JpaRepository<E, Long>> implements CrudService<E> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private final Validator validator;
    private final R repository;
    private final ApplicationContext applicationContext;

    protected AbstractCrudService(Validator validator, R repository, ApplicationContext applicationContext) {
        this.validator = validator;
        this.repository = repository;
        this.applicationContext = applicationContext;
    }

    protected R getRepository() {
        return repository;
    }

    protected Validator getValidator() {
        return validator;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public E save(E entity) throws ValidationFailedException, OptimisticLockingFailureException {
        logger.debug("Saving {}", entity);
        ValidationFailedException.throwIfNotEmpty(validator.validate(entity));
        getCallbacks(CrudServiceCallback.SaveCallback.class, entity).forEach(callback -> callback.beforeSave(entity));
        final E savedEntity = getRepository().saveAndFlush(entity);
        getCallbacks(CrudServiceCallback.SaveCallback.class, entity).forEach(callback -> callback.afterSave(savedEntity));
        return savedEntity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(E entity) {
        logger.debug("Deleting {}", entity);
        getCallbacks(CrudServiceCallback.DeleteCallback.class, entity).forEach(callback -> callback.beforeDelete(entity));
        getRepository().delete(entity.getId());
        getCallbacks(CrudServiceCallback.DeleteCallback.class, entity).forEach(callback -> callback.afterDelete(entity));
    }

    protected <CB extends CrudServiceCallback> Stream<CB> getCallbacks(Class<CB> callbackClass, E entity) {
        logger.trace("Looking up callbacks of type {} for {}", callbackClass, entity);
        return applicationContext.getBeansOfType(callbackClass).values().stream().filter(callback -> callback.supports(entity));
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<E> findAll() {
        return getRepository().findAll();
    }
}
