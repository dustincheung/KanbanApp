/*
 *  This class is the exception response, it contains a single string message. It is returned by an
 *  exception handler in the CustomExceptionResponseHandler class.
 */

package kanbanapp.Exception;

public class UserExceptionResponse {
	
	private String username;

	public UserExceptionResponse(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
