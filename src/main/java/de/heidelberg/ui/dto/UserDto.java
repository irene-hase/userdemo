package de.heidelberg.ui.dto;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.trimToNull;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserDto implements Serializable {

    private static final long serialVersionUID = -5280105111996304926L;

    private String username;
    private String email;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(final String username)
    {
        this.username = requireNonNull(trimToNull(username), "Username must not be null");;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = requireNonNull(trimToNull(email), "Email must not be null");
    }
}
