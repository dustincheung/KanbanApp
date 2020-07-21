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

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ErrorMappingService errorMappingService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/users/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result){
		//ensure passwords match
		
		ResponseEntity<?> mapErrors = errorMappingService.mapErrors(result);
		
		if(mapErrors != null) {
			return mapErrors;
		}
		
		User newUser = userService.createUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
}
