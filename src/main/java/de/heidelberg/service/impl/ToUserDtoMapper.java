package de.heidelberg.service.impl;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

import javax.enterprise.context.Dependent;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import de.heidelberg.model.User;
import de.heidelberg.ui.dto.UserDto;

@Dependent
public class ToUserDtoMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(final User user)
    {
        final UserDto userDto = new UserDto();
        userDto.setUsername(user.getName());

        return userDto;
    }

    Set<UserDto> mapAll(final Collection<User> source){
        if (source == null) {return emptySet(); }
        return source.stream()
                .map(this)
                .filter(Objects::nonNull)
                .collect(collectingAndThen(toSet(), Collections::unmodifiableSet));
    }
}
