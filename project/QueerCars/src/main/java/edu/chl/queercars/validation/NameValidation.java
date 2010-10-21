package edu.chl.queercars.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Joons
 *
 * Validates that the name is accepteble lenght
 */
@FacesValidator(value = "nameValidator")
public class NameValidation implements Validator {


    private int min = 2;
    private int max = 30;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
	    throws ValidatorException {

	String param = value.toString();
	if (param.length() < min || param.length() > max) {
	    FacesMessage msg = new FacesMessage("Must be  between" + this.min + " and " +this.max + " characters");
	    throw new ValidatorException(msg);
	}
    }
}
