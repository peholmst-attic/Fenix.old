package net.pkhapps.fenix.core.sms.boundary;

import net.pkhapps.fenix.core.sms.entity.SmsProperties;

import java.util.Optional;

/**
 * TODO Document me!
 */
public interface SmsPropertiesService {

    /**
     * Returns the SMS properties for the current user's fire department, or an empty
     * {@code Optional} if there are no SMS properties stored.
     */
    Optional<SmsProperties> getSmsProperties();

    /**
     * Saves the specified SMS properties for the current user's fire department.
     *
     * @param smsProperties the properties to save.
     * @return the saved properties.
     */
    SmsProperties saveSmsProperties(SmsProperties smsProperties);
}
