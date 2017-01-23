package web.validators;

import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator("web.validators.LoginValidator")
public class LoginValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {

        ResourceBundle bundle = ResourceBundle.getBundle("web.nls.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

        String newValue = value.toString();

        System.out.println(newValue);
        
        try {
            if (newValue.length() < 5) {
                throw new IllegalArgumentException(bundle.getString("username_length_error"));
            }

            if (!Character.isLetter(newValue.charAt(0))) {
                throw new IllegalArgumentException(bundle.getString("username_first_letter"));
            }

            if (getTestArray().contains(newValue)) {
                throw new IllegalArgumentException(bundle.getString("username_reserved"));
            }
        } catch (IllegalArgumentException e) {
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    private ArrayList<String> getTestArray() {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("username");
        arr.add("login");
        return arr;

    }

}
