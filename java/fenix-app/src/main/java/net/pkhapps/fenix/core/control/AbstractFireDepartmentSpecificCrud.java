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

/**
 * Abstract base class for {@link net.pkhapps.fenix.core.control.FireDepartmentSpecificCrud} implementations.
 */
public class AbstractFireDepartmentSpecificCrud<E extends AbstractFireDepartmentSpecificEntity, R extends FireDepartmentSpecificEntityRepository<E>> extends AbstractCrud<E, R> implements FireDepartmentSpecificCrud<E> {

    protected AbstractFireDepartmentSpecificCrud(Validator validator, R repository, ApplicationContext applicationContext) {
        super(validator, repository, applicationContext);
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
