package de.heidelberg.ui.page;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginException;

import java.io.Serializable;

import de.heidelberg.service.AuthenticationService;
import de.heidelberg.ui.dto.Credentials;
import lombok.EqualsAndHashCode;

@Named("loginPageBean")
@RequestScoped
@EqualsAndHashCode
public class LoginPageBean extends AbstractPageBean {

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

    public String login() throws LoginException
    {
        credentials = new Credentials(username, password);
        final boolean loginResult = authenticationService.login(credentials);
        if (!loginResult) {
            addWarnMessage("page.login.warn.login.failed");
            return null;
        }
        return "content";
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

    public void clear()
    {
         credentials.clear();
    }
}
