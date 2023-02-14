package de.heidelberg.exception;

public class UserRegistrationException extends RuntimeException{

    public UserRegistrationException(final String message, final Throwable exc)
    {
        super(message, exc);
    }
}
