/*
 *  This class is the exception response, it contains two string messages. It is returned by an
 *  exception handler in the CustomExceptionResponseHandler class.
 */

package kanbanapp.Exception;

public class BadLoginResponse {
	private String username;
	private String password;
	
	public BadLoginResponse() {
		this.username = "Invalid Username";
		this.password = "Invalid Password";
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
