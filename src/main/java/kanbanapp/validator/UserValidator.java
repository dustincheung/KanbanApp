/*
 *  Validator class that validates attributes of the user.  Specifically ensures password is valid in length
 *  and confirmPassword is the same as password.
 */

package kanbanapp.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kanbanapp.model.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		//class that you want to validate
		return User.class.equals(clazz);  
	}

	@Override
	public void validate(Object target, Errors errors) {
		//cast object to a User obj
		User user = (User) target;
		
		//validate password length
		if(user.getPassword().length() < 8) {
			errors.rejectValue("password", "Length", "Password must be at least 8 characters");
		}
		
		//validate confirmPassword matches password
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Match", "Passwords do not match");
		}
		
	}
}
