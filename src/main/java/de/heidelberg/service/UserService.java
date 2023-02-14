package de.heidelberg.service;

import java.io.Serializable;
import java.util.Optional;

import de.heidelberg.ui.dto.UserDto;

public interface UserService extends Serializable {

    Optional<UserDto> findUserByUsernameAndPassword(final String username, final String password);

    Optional<UserDto> findUserByMailAndPassword(final String mail, final String password);

}
