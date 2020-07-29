/*
 *  This class is the exception response, it contains a single string message. It is returned by an
 *  exception handler in the CustomExceptionResponseHandler class.
 */

package kanbanapp.Exception;

public class ProjectTagExceptionResponse {
	 
	private String projTag;
	
	public ProjectTagExceptionResponse(String projTag) {
		this.projTag = projTag;
	}

	public String getProjTag() {
		return projTag;
	}

	public void setProjTag(String projTag) {
		this.projTag = projTag;
	}
}
