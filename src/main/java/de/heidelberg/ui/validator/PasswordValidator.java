package de.heidelberg.ui.validator;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator<String> {

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
    public void validate(final FacesContext context, final UIComponent component, final String value)
            throws ValidatorException
    {
        if (null != value) {
            Matcher matcher = getCompiledPattern().matcher(value);
            if (!matcher.matches()) {
                throw new ValidatorException(new FacesMessage(SEVERITY_ERROR, INVALID_PASSWORD, INVALID_PASSWORD));
            }
        }
    }
}
