package edu.chl.queercars.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author hajo
 */
@FacesValidator(value = "loginValidator")
public class LoginValidation implements Validator {

    private int length = 10;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
	    throws ValidatorException {

	String param = value.toString();
	if (param.length() != length) {
	    FacesMessage msg = new FacesMessage("Must be  " + this.length + " characters");
	    throw new ValidatorException(msg);
	}
    }
}
