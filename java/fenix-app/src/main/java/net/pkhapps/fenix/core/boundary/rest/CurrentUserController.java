package net.pkhapps.fenix.core.boundary.rest;

import net.pkhapps.fenix.core.boundary.rest.dto.UserDTOMapper;
import net.pkhapps.fenix.core.boundary.rest.support.Constants;
import net.pkhapps.fenix.core.boundary.rest.support.NoSuchResourceException;
import net.pkhapps.fenix.core.entity.SystemUser;
import net.pkhapps.fenix.core.entity.SystemUserRepository;
import net.pkhapps.fenix.core.security.context.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * REST controller that returns information about the current user.
 */
@RestController
@RequestMapping(Constants.REST_URL_PREFIX + "me")
class CurrentUserController {

    private final UserDTOMapper userDTOMapper;
    private final SystemUserRepository systemUserRepository;
    private final CurrentUser currentUser;

    @Autowired
    CurrentUserController(UserDTOMapper userDTOMapper, SystemUserRepository systemUserRepository, CurrentUser currentUser) {
        this.userDTOMapper = userDTOMapper;
        this.systemUserRepository = systemUserRepository;
        this.currentUser = currentUser;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get() {
        final Optional<Authentication> user = currentUser.getUser();
        if (user.isPresent()) {
            SystemUser systemUser = systemUserRepository.findByEmail(user.get().getName());
            if (systemUser != null) {
                return new ResponseEntity<>(userDTOMapper.toDTO(systemUser), HttpStatus.OK);
            }
        }
        throw new NoSuchResourceException();
    }
}
