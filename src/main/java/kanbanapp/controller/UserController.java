package kanbanapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kanbanapp.model.User;
import kanbanapp.service.ErrorMappingService;
import kanbanapp.service.UserService;
import kanbanapp.validator.UserValidator;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ErrorMappingService errorMappingService;
	
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping(method = RequestMethod.POST, value = "/users/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result){
		
		//validate password (length and matching passwords)
		userValidator.validate(user, result);
		
		//handles col conditions 
		ResponseEntity<?> mapErrors = errorMappingService.mapErrors(result);
		
		if(mapErrors != null) {
			return mapErrors;
		}
		
		User newUser = userService.createUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
}
