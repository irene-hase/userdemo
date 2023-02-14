package de.heidelberg.service.dao;

import javax.enterprise.context.ApplicationScoped;

import de.heidelberg.model.User;

@ApplicationScoped
public class UserRepository extends AbstractRepository<User>{

    public UserRepository()
    {
        super(User.class);
    }
}
