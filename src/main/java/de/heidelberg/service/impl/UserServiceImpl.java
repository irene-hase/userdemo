package de.heidelberg.service.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.Optional;

import de.heidelberg.service.UserService;
import de.heidelberg.service.dao.UserRepository;
import de.heidelberg.ui.dto.UserDto;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public Optional<UserDto> findUserByUsernameAndPassword(final String username, String password)
    {
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> findUserByMailAndPassword(final String mail, String password)
    {
        return Optional.empty();
    }
}
