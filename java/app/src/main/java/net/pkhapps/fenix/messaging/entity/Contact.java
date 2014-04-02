package net.pkhapps.fenix.messaging.entity;

import net.pkhapps.fenix.core.entity.AbstractEntity;
import net.pkhapps.fenix.core.entity.Organization;
import net.pkhapps.fenix.core.validation.Phone;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author petter@vaadin.com
 */
@Entity
@Table(name = "contacts")
public class Contact extends AbstractEntity {

    private Organization organization;
    @Column(name = "cname")
    private String name;
    @Column(name = "email")
    @Email
    private String email;
    @Column(name = "mobile")
    @Phone
    private String mobilePhone;

}
