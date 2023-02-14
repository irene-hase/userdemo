package de.heidelberg.ui;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;

import de.heidelberg.service.AuthenticationService;
import de.heidelberg.ui.dto.Credentials;

@Named("loginPageBean")
@RequestScoped
public class LoginPageBean implements Serializable {

    private static final long serialVersionUID = -7910544549542823310L;
    private Credentials credentials;

    private String username;

    private String password;

    @Inject
    private AuthenticationService authenticationService;


    @PostConstruct
    public void init() {

//        authenticationService//
    }

    public Credentials getCredentials()
    {
        return credentials;
    }

    public void setCredentials(Credentials credentials)
    {
        this.credentials = credentials;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
