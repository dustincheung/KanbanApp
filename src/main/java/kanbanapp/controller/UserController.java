package kanbanapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kanbanapp.model.User;
import kanbanapp.payload.JWTSuccessResponse;
import kanbanapp.payload.LoginRequest;
import kanbanapp.security.JwtTokenProvider;
import kanbanapp.service.ErrorMappingService;
import kanbanapp.service.UserService;
import kanbanapp.validator.UserValidator;
import static kanbanapp.security.SecurityConstants.JWT_PREFIX;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ErrorMappingService errorMappingService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
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
	
	//takes in loginRequest payload, authenticates the returns JWTSuccessResponse with JWT
	@RequestMapping(method = RequestMethod.POST, value = "/users/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
		ResponseEntity<?> mapErrors = errorMappingService.mapErrors(result);
		
		if(mapErrors != null) {
			return mapErrors;
		}
		
		System.out.println("AUTHENTICATING");
		System.out.println(loginRequest.getUsername());
		System.out.println(loginRequest.getPassword());
		//authenticate using user info from LoginRequest payload
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(),
						loginRequest.getPassword()
				)
		);
		
		
		System.out.println("HERE");
		//create JWT
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = JWT_PREFIX + tokenProvider.generateToken(authentication);
		
		//return JWTSuccessResponse with jwt
		return ResponseEntity.ok(new JWTSuccessResponse(jwt, true));
	}
}
