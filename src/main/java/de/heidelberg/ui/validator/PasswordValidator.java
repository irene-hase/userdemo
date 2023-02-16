package de.heidelberg.ui.validator;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.validator.MultiFieldValidator;

@Named
@RequestScoped
public class PasswordValidator implements MultiFieldValidator, Serializable {

    private static final String INVALID_PASSWORD = "Invalid password";
    /**
     * ^                                            Match the beginning of the string
     * (?=.*[0-9])                                  Require that at least one digit appear anywhere in the string
     * (?=.*[a-z])                                  Require that at least one lowercase letter appear anywhere in the string
     * (?=.*[A-Z])                                  Require that at least one uppercase letter appear anywhere in the string
     * (?=.*[*.!@$%^&(){}[]:;<>,.?/~_+-=|\])    Require that at least one special character appear anywhere in the string
     * .{8,32}                                      The password must be at least 8 characters long, but no more than 32
     * $                                            Match the end of the string.
     */
    @Getter
    @Setter
    private String validPwdPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}[]:;<>,.?/~_+-=|\\]).{8,32}$";

    private Pattern compiledPattern;

    private Pattern getCompiledPattern()
    {
        if (compiledPattern == null) {
            compiledPattern = Pattern.compile(validPwdPattern);
        }
        return compiledPattern;
    }

    @Override
    public boolean validateValues(final FacesContext context, final List<UIInput> components, final List<Object> values) {
        if (null == values) {
            return true;
        }
        if (values.size() != 2) {
            return false;
        }
        if (values.get(0) instanceof String && values.get(1) instanceof String) {

            String firstPassword = (String) values.get(1);
            String secondPassword = (String) values.get(0);

            if (Objects.equals(firstPassword, secondPassword)) {
                Matcher matcher = getCompiledPattern().matcher(firstPassword);
                if (matcher.matches()) {
                    return true;
                }
            }
        }
        return false;
    }
}
