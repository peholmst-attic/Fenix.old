package net.pkhapps.fenix.core.sms.entity;

import net.pkhapps.fenix.core.entity.FireDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of {@link SmsProperties} instances.
 */
public interface SmsPropertiesRepository extends JpaRepository<SmsProperties, Long> {

    SmsProperties findByFireDepartment(FireDepartment fireDepartment);

}
