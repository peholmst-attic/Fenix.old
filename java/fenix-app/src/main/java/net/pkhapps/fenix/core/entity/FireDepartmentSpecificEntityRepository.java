package net.pkhapps.fenix.core.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Base interface for repositories of {@link net.pkhapps.fenix.core.entity.AbstractFireDepartmentSpecificEntity} instances.
 */
@NoRepositoryBean
public interface FireDepartmentSpecificEntityRepository<T extends AbstractFireDepartmentSpecificEntity> extends JpaRepository<T, Long> {

    T findByIdAndFireDepartment(Long id, FireDepartment fireDepartment);

    List<T> findByFireDepartment(FireDepartment fireDepartment);

    Page<T> findByFireDepartment(FireDepartment fireDepartment, Pageable pageable);
}
