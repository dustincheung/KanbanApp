/*
 *  This class is the exception response, it contains a single string message. It is returned by an
 *  exception handler in the CustomExceptionResponseHandler class.
 */

package kanbanapp.Exception;

public class BacklogNotFoundExceptionResponse {
	
	private String backlogNotFound;
	
	public BacklogNotFoundExceptionResponse(String backlogNotFound) {
		this.backlogNotFound = backlogNotFound;
	}

	public String getBacklogNotFound() {
		return this.backlogNotFound;
	}

	public void setBacklogNotFound(String backlogNotFound) {
		this.backlogNotFound = backlogNotFound;
	}
}
