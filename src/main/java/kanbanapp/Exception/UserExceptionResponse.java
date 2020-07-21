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
