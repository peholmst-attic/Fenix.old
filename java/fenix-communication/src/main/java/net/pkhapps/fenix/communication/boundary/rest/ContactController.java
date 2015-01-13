package net.pkhapps.fenix.communication.boundary.rest;

import net.pkhapps.fenix.communication.boundary.ContactService;
import net.pkhapps.fenix.communication.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by peholmst on 13-01-15.
 */
@RestController
@RequestMapping( value = "/rest/contacts")
public class ContactController {

    private static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    ContactService contactService;

    @RequestMapping(method = RequestMethod.GET)
    Page<Contact> contacts(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        return contactService.findAll(new PageRequest(page.orElse(0), size.orElse(DEFAULT_PAGE_SIZE)));
    }

}
