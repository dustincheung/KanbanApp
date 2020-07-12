/*
 *  This class is a subclass of RuntimeException, it is an exception that contains a single message
 */

package kanbanapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BacklogNotFoundException extends RuntimeException {
	
	// utilize parent class constructor, this exception taks a string
	public BacklogNotFoundException(String message) {
		super(message);
	}
}
