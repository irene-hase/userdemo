package de.heidelberg.service.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import de.heidelberg.model.User;
import de.heidelberg.model.VerificationStatus;
import de.heidelberg.service.UserService;
import de.heidelberg.service.dao.UserRepository;
import de.heidelberg.ui.dto.UserDto;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    //    @Inject
    //    private UserRepository userRepository;
    static final List<User> USER_REPO_DUMMY;

    static {
        final User user1 = new User();
        user1.setPassword("user1");
        user1.setId(new Long(1));
        user1.setName("user1");

        USER_REPO_DUMMY = List.of(user1);
    }

    @Inject
    @ConfigProperty(name = "de.heidelberg.maxCountOfAllowedFailedLogins")
    private int maxCountOfAllowedFailedLogins;

    @Inject
    private ToUserDtoMapper toUserDtoMapper;

    @Override
    public Optional<UserDto> findUserByExactMatchOf(final String username, final String password)
    {
//        final User foundUser = userRepository.findByUsername(username);
        final User foundUser =
                USER_REPO_DUMMY.stream().filter(user -> user.getName().equals(username)).findAny().orElse(null);
        if (foundUser == null || VerificationStatus.BLOCKED == foundUser.getVerificationStatus() ||
                VerificationStatus.UNVERIFIED == foundUser.getVerificationStatus()) {
            // logging
            Optional.empty();
        }
        if (Objects.equals(foundUser.getPassword(), password)) {
            resetCountOfFailedLogins(foundUser);

            return Optional.of(toUserDtoMapper.apply(foundUser));
        } else {
            increaseCountOfFailedLogins(foundUser);
        }
        return Optional.empty();
    }

    public void increaseCountOfFailedLogins(User user)
    {
        user.setCountOfFailedLogins(user.getCountOfFailedLogins() + 1);

        if (maxCountOfAllowedFailedLogins == user.getCountOfFailedLogins()) {
            user.setVerificationStatus(VerificationStatus.BLOCKED);
        }
    }

    public void resetCountOfFailedLogins(User user)
    {
        user.setCountOfFailedLogins(0);
    }
}
