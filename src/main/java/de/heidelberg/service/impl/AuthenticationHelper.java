package de.heidelberg.service.impl;

import java.security.MessageDigest;
import java.util.Base64;

import de.heidelberg.exception.UserRegistrationException;

final class AuthenticationHelper {

    private AuthenticationHelper()    {}

    static String digestPassword(final String plainTextPassword) throws UserRegistrationException
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainTextPassword.getBytes("UTF-8"));

            return Base64.getEncoder().encodeToString(md.digest());
        }
        catch (Exception exc)
        {
            throw new UserRegistrationException("Password encoding failed", exc);
        }
    }
}
