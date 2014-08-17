package net.pkhapps.fenix.communication.entity;

import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of {@link net.pkhapps.fenix.communication.entity.SmsProperties} instances.
 */
public interface SmsPropertiesRepository extends JpaRepository<SmsProperties, Long> {

    SmsProperties findByFireDepartment(FireDepartment fireDepartment);

}
