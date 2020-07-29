/*
 *  This class is a subclass of RuntimeException, it is an exception that contains a single message.
 *  It is thrown by a service class when an error that should be handled occurs.
 */

package kanbanapp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserException extends RuntimeException{
	
	public UserException(String message) {
		super(message);
	}
}
