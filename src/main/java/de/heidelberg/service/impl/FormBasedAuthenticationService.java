package de.heidelberg.service.impl;

import static org.apache.commons.lang3.StringUtils.trimToNull;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginException;

import java.util.Optional;

import de.heidelberg.service.AuthenticationService;
import de.heidelberg.service.UserService;
import de.heidelberg.ui.dto.Credentials;
import de.heidelberg.ui.dto.UserDto;

@SessionScoped
public class FormBasedAuthenticationService implements AuthenticationService {

    @Inject
    private UserService userService;

    @Produces
    @SessionScoped
    @Named("authenticatedUser")
    private UserDto authenticatedUser;
//    private Optional<UserDto> authenticatedUser;

    @Override
    public boolean login(final Credentials userCredentials) throws LoginException
    {
        if (null == trimToNull(userCredentials.getUsername()) || null == trimToNull(userCredentials.getPassword())) {
            throw new LoginException("Missed credentials");
        }
        final Optional<UserDto> unauthenticatedUser = userService.findUserByExactMatchOf(userCredentials.getUsername(), userCredentials.getPassword());
        this.authenticatedUser = unauthenticatedUser.orElse(null);

        return unauthenticatedUser.isPresent();
    }
}
