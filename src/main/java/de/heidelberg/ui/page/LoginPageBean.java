package de.heidelberg.ui.page;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginException;

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

    public String login() throws LoginException
    {
        final boolean loginResult = authenticationService.login(new Credentials(username, password));
        if (!loginResult) {
            addErrorMessage("page.login.warn.login.failed");
            return null;
        }
        return "content";
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
