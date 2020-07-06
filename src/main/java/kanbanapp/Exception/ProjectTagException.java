package kanbanapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectTagException extends RuntimeException{
	
	// utilize parent class constructor
	public ProjectTagException(String message) {
		super(message);
	}
}
