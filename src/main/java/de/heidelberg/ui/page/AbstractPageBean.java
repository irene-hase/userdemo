package de.heidelberg.ui.page;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;
import static javax.faces.application.FacesMessage.SEVERITY_WARN;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

abstract class AbstractPageBean implements Serializable {
    protected void addInfoMessage(final String msgKey, final Object... args)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(SEVERITY_INFO, getMessage(context, msgKey, args), null));
    }

    protected void addWarnMessage(final String msgKey, final Object... args)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(SEVERITY_WARN, getMessage(context, msgKey, args), null));
    }

    protected void addErrorMessage(final String msgKey, final Object... args)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(SEVERITY_ERROR, getMessage(context, msgKey, args), null));
    }

    private String getMessage(FacesContext facesContext, String msgKey, Object... args)
    {
        Locale locale = facesContext.getViewRoot().getLocale();
        ResourceBundle bundle = ResourceBundle.getBundle("de.heidelberg.userdemo.i18n.messages", locale, Thread.currentThread().getContextClassLoader());
        String msgValue = bundle.getString(msgKey);
        return String.format(msgValue, args);
    }
}
