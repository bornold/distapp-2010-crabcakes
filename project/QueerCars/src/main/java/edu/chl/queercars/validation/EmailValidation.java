package edu.chl.queercars.validation;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.RegularExpression;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Joons
 */
@FacesValidator(value = "emailValidator")
public class EmailValidation implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
	    throws ValidatorException {

	String email = value.toString();
	String regex = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+(?:[A-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum|se|nu|eu)\\b";
	RegularExpression re = new RegularExpression(regex);


	if (!re.matches(email)) {
	    FacesMessage msg = new FacesMessage("Not a valid emailadress");
	    throw new ValidatorException(msg);
	}
    }
}
