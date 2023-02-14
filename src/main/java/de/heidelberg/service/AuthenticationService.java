package de.heidelberg.service;

import javax.security.auth.login.LoginException;

import java.io.Serializable;

import de.heidelberg.ui.dto.Credentials;

public interface AuthenticationService extends Serializable {

    boolean login(final Credentials userCredentials) throws LoginException;

}
