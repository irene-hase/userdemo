package de.heidelberg.service;

import java.security.MessageDigest;
import java.util.Base64;

final class AuthenticationHelper {

    private AuthenticationHelper()    {}

    static String digestPassword(final String plainTextPassword)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainTextPassword.getBytes("UTF-8"));

            return Base64.getEncoder().encodeToString(md.digest());
        }
        catch (Exception e)
        {
            throw new RuntimeException("Exception encoding password", e);
        }
    }
}
