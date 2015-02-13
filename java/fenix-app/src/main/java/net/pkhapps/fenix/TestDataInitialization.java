package net.pkhapps.fenix;

import net.pkhapps.fenix.communication.entity.*;
import net.pkhapps.fenix.core.entity.FireDepartment;
import net.pkhapps.fenix.core.entity.FireDepartmentRepository;
import net.pkhapps.fenix.core.entity.SystemUser;
import net.pkhapps.fenix.core.entity.SystemUserRepository;
import net.pkhapps.fenix.core.security.UserRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that populates the database with test data.
 */
// TODO Disable when running in production mode
@Component
class TestDataInitialization {

    static final String[] FIRST_NAMES = {"Alice", "Bob", "Cathy", "Donald", "Eve", "Joe", "John", "Max", "Patricia", "Samantha"};
    static final String[] LAST_NAMES = {"Anderson", "Bond", "Cool", "Doe", "Fox", "Gavin", "McLain", "Payne", "Roberts", "Smith"};
    static final String[] SINGLE_NAMES = {"Station 1", "Station 2", "RVS101", "RVS106", "RVS103", "RVS201", "RVS301", "RVS303", "RVS30", "RVS31"};

    static final String ENCRYPTED_PASSWORD_P = "$2a$04$1msDGF6OLTquNVDDA4FWPegdZrzLMzycrOkTdvmKn3OVFNlfw8q8u";
    static final Logger LOGGER = LoggerFactory.getLogger(TestDataInitialization.class);
    private static Random rnd = new Random();

    @Autowired
    FireDepartmentRepository fireDepartmentRepository;
    @Autowired
    SystemUserRepository systemUserRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    GroupRepository groupRepository;

    private static String generateCellPhone() {
        return String.format("+358%02d%07d", rnd.nextInt(99) + 1, rnd.nextInt(10000000));
    }

    private static String generateEmail(String name) {
        return name.toLowerCase().replace(" ", ".") + "@foo.bar";
    }

    @PostConstruct
    void init() {
        createSystemAdmin();
        createFireDepartmentsAndUsers();
    }

    private void createFireDepartmentsAndUsers() {
        LOGGER.info("Creating fire departments and user accounts");
        FireDepartment acmeFD = new FireDepartment();
        acmeFD.setName("Acme Fire Department");
        acmeFD = fireDepartmentRepository.saveAndFlush(acmeFD);

        FireDepartment goldenFD = new FireDepartment();
        goldenFD.setName("Golden Fire Department");
        goldenFD = fireDepartmentRepository.saveAndFlush(goldenFD);

        final SystemUser joeCool = new SystemUser();
        joeCool.setFirstName("Joe");
        joeCool.setLastName("Cool");
        joeCool.setEncryptedPassword(ENCRYPTED_PASSWORD_P);
        joeCool.setEmail("joe@foo.bar");
        joeCool.getFireDepartmentRoles().put(acmeFD, UserRoles.ROLE_FD_ADMINISTRATOR);
        joeCool.getFireDepartmentRoles().put(goldenFD, UserRoles.ROLE_FD_POWER_USER);
        systemUserRepository.saveAndFlush(joeCool);

        final SystemUser maxwellSmart = new SystemUser();
        maxwellSmart.setFirstName("Maxwell");
        maxwellSmart.setLastName("Smart");
        maxwellSmart.setEncryptedPassword(ENCRYPTED_PASSWORD_P);
        maxwellSmart.setEmail("max@foo.bar");
        maxwellSmart.getFireDepartmentRoles().put(acmeFD, UserRoles.ROLE_FD_POWER_USER);
        maxwellSmart.getFireDepartmentRoles().put(goldenFD, UserRoles.ROLE_FD_USER);
        systemUserRepository.saveAndFlush(maxwellSmart);

        final SystemUser aliceSmith = new SystemUser();
        aliceSmith.setFirstName("Alice");
        aliceSmith.setLastName("Smith");
        aliceSmith.setEncryptedPassword(ENCRYPTED_PASSWORD_P);
        aliceSmith.setEmail("alice@foo.bar");
        aliceSmith.getFireDepartmentRoles().put(acmeFD, UserRoles.ROLE_FD_USER);
        aliceSmith.getFireDepartmentRoles().put(goldenFD, UserRoles.ROLE_FD_ADMINISTRATOR);
        systemUserRepository.saveAndFlush(aliceSmith);

        final SystemUser eveUnauthorized = new SystemUser();
        eveUnauthorized.setFirstName("Eve");
        eveUnauthorized.setLastName("Unauthorized");
        eveUnauthorized.setEncryptedPassword(ENCRYPTED_PASSWORD_P);
        eveUnauthorized.setEmail("eve@foo.bar");
        systemUserRepository.saveAndFlush(eveUnauthorized);

        createDataForFireDepartment(acmeFD);
        createDataForFireDepartment(goldenFD);
    }

    private void createSystemAdmin() {
        LOGGER.info("Creating system administrator user account");
        final SystemUser sysadmin = new SystemUser();
        sysadmin.setFirstName("System");
        sysadmin.setLastName("Administrator");
        sysadmin.setEncryptedPassword(ENCRYPTED_PASSWORD_P);
        sysadmin.setSysadmin(true);
        sysadmin.setEmail("sysadmin@foo.bar");
        systemUserRepository.saveAndFlush(sysadmin);
    }

    private void createDataForFireDepartment(FireDepartment fireDepartment) {
        LOGGER.info("Creating data for {}", fireDepartment);
        createContactsAndGroups(fireDepartment);
    }

    private void createContactsAndGroups(FireDepartment fireDepartment) {
        List<Contact> contacts = new ArrayList<>();
        LOGGER.info("Creating contacts and groups for {}", fireDepartment);
        for (int i = 0; i < 5; i++) {
            Contact contact = new Contact();
            contact.setSingleName(SINGLE_NAMES[rnd.nextInt(SINGLE_NAMES.length)]);
            contact.setCellPhone(generateCellPhone());
            contact.setFireDepartment(fireDepartment);
            contact.getCommunicationMethods().add(CommunicationMethod.SMS);
            contacts.add(contactRepository.save(contact));
        }

        for (int i = 0; i < 50; i++) {
            Contact contact = new Contact();
            contact.setFirstName(FIRST_NAMES[rnd.nextInt(FIRST_NAMES.length)]);
            contact.setLastName(LAST_NAMES[rnd.nextInt(LAST_NAMES.length)]);
            contact.setCellPhone(generateCellPhone());
            contact.setEmail(generateEmail(contact.getFirstName() + "." + contact.getLastName()));
            contact.setFireDepartment(fireDepartment);
            contact.getCommunicationMethods().add(CommunicationMethod.SMS);
            contact.getCommunicationMethods().add(CommunicationMethod.EMAIL);
            contacts.add(contactRepository.save(contact));
        }
        contactRepository.flush();

        for (int i = 0; i < 30; i++) {
            Group group = new Group();
            group.setName("Group " + i);
            for (int j = 0; j < rnd.nextInt(contacts.size() / 2); j++) {
                GroupMember member = new GroupMember(contacts.get(rnd.nextInt(contacts.size())));
                group.getMembers().add(member);
            }
            group.setFireDepartment(fireDepartment);
            groupRepository.save(group);
        }
        groupRepository.flush();
    }
}
