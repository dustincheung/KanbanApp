/*
 *  This class is the exception controller, handles all custom exceptions. Determines which exception 
 *  response is to be returned when the application throws a specific custom exception
 *  
 *  The exception flow is:
 *  	- service class upon error that should be handled, will throw new exception obj with a string
 *  	- this class (custom exception handler) determines which exception response to create
 *  	- exception response (with string msg) is returned
 */

package kanbanapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice // all controllers will come to this class for "advice" on how to handle diff types of exceptions
@RestController
public class CustomExceptionResponseHandler extends ResponseEntityExceptionHandler{
	
	//ProjTag Exception
	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectTagException(ProjectTagException exception, WebRequest request){
		
		//creates a new custom response obj that holds the message from custom exception
		ProjectTagExceptionResponse exceptionResponse = new ProjectTagExceptionResponse(exception.getMessage());
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//BacklogNotFound Exception
	@ExceptionHandler
	public final ResponseEntity<Object> handleBacklogNotFoundException(BacklogNotFoundException exception, WebRequest request){
		
		//creates a new custom response obj that holds the message from custom exception
		BacklogNotFoundExceptionResponse exceptionResponse = new BacklogNotFoundExceptionResponse(exception.getMessage());
		
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//User  Exception
	@ExceptionHandler
	public final ResponseEntity<Object> handleUserException(UserException exception, WebRequest request){
			
		//creates a new custom response obj that holds the message from custom exception
		UserExceptionResponse exceptionResponse = new UserExceptionResponse(exception.getMessage());
			
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
}
