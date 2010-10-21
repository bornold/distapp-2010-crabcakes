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
 * validates terms
 */
@FacesValidator(value = "termsValidator")
public class TermsValidation implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
	    throws ValidatorException {

	if (value.toString().equals("false")) {
	    FacesMessage msg = new FacesMessage("Du måste kryssa i ");
	    throw new ValidatorException(msg);
	}
    }
}
