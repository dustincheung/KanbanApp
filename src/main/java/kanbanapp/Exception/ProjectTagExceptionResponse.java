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
