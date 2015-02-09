package net.pkhapps.fenix.core.control;

import net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity;
import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.entity.FireDepartmentSpecificEntityRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

/**
 * Abstract base class for {@link net.pkhapps.fenix.core.control.FireDepartmentSpecificCrud} implementations.
 */
public abstract class AbstractFireDepartmentSpecificCrud<E extends AbstractFireDepartmentSpecificEntity, R extends FireDepartmentSpecificEntityRepository<E>> extends AbstractCrud<E, R> implements FireDepartmentSpecificCrud<E> {

    protected AbstractFireDepartmentSpecificCrud(Validator validator, R repository, ApplicationContext applicationContext) {
        super(validator, repository, applicationContext);
    }

    @Override
    public Optional<E> findOne(FireDepartment fireDepartment, Long id) {
        return Optional.ofNullable(getRepository().findByIdAndFireDepartment(id, fireDepartment));
    }

    @Override
    public List<E> findAll(FireDepartment fireDepartment, Sort sort) {
        return getRepository().findByFireDepartment(fireDepartment, sort);
    }

    @Override
    public Page<E> findAll(FireDepartment fireDepartment, Pageable pageable) {
        return getRepository().findByFireDepartment(fireDepartment, pageable);
    }
}
