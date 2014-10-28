package net.pkhapps.fenix.core.sms.boundary;

import net.pkhapps.fenix.core.security.SessionInfo;
import net.pkhapps.fenix.core.sms.entity.SmsProperties;
import net.pkhapps.fenix.core.sms.entity.SmsPropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Default implementation of {@link net.pkhapps.fenix.core.sms.boundary.SmsPropertiesService}.
 */
@Service
class SmsPropertiesServiceBean implements SmsPropertiesService {

    private final SessionInfo sessionInfo;
    private final SmsPropertiesRepository smsPropertiesRepository;

    @Autowired
    SmsPropertiesServiceBean(SessionInfo sessionInfo, SmsPropertiesRepository smsPropertiesRepository) {
        this.sessionInfo = sessionInfo;
        this.smsPropertiesRepository = smsPropertiesRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Optional<SmsProperties> getSmsProperties() {
        return Optional.ofNullable(smsPropertiesRepository.findByFireDepartment(sessionInfo.getCurrentFireDepartment()));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public SmsProperties saveSmsProperties(SmsProperties smsProperties) {
        if (smsProperties.getFireDepartment() == null) {
            smsProperties.setFireDepartment(sessionInfo.getCurrentFireDepartment());
        }
        sessionInfo.checkFireDepartment(smsProperties);
        return smsPropertiesRepository.saveAndFlush(smsProperties);
    }
}
