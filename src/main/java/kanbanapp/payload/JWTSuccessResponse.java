/*
 *  Response Object sent to client on successful authentication.  Contains boolean success and the
 *  JSON web token that will be used in header of all requests.
 */

package kanbanapp.payload;

public class JWTSuccessResponse {
	private String token;
	private boolean success;
	
	public JWTSuccessResponse(String token, boolean success) {
		this.token = token;
		this.success = success;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "JWTSuccessResponse [token=" + token + ", success=" + success + "]";
	}
}
