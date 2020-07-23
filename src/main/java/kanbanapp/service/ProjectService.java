package kanbanapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kanbanapp.Exception.ProjectTagException;
import kanbanapp.model.Backlog;
import kanbanapp.model.Project;
import kanbanapp.model.User;
import kanbanapp.repository.BacklogRepository;
import kanbanapp.repository.ProjectRepository;
import kanbanapp.repository.UserRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	// gets all projects
	public Iterable<Project> indexProjects(String username){
		
		return projectRepository.findAllByProjOwner(username);
	}
		
	// creates a new project 
	public Project createProject(Project project, String username) {
		try {
			//find user set user field and projOwner field in project obj
			User user = userRepository.findByUsername(username);
			project.setUser(user);
			project.setProjOwner(user.getUsername());
			
			//formatting proj tag
			String projTag = project.getProjTag().toUpperCase();
			project.setProjTag(projTag);
			
			//Creating associated Backlog entity for project and setting relationship mapping for both classes
			//also setting associated projTag in Backlog 
			Backlog backlog = new Backlog();
			project.setBacklog(backlog);
			backlog.setProject(project);
			backlog.setProjTag(projTag);
			
			return projectRepository.save(project);
			
		}catch(Exception e) {
			throw new ProjectTagException("Project Tag " + project.getProjTag().toUpperCase() + " is already used");
		}
	}
	
	// gets project that based on projTag
	public Project showProject(String projTag, String username) {
		
		Project projectToShow = projectRepository.findByProjTag(projTag);
		
		if(projectToShow == null) {
			throw new ProjectTagException("Project Tag " + projTag + " does not exist");
		}
		
		if(!projectToShow.getProjOwner().equals(username)) {
			throw new ProjectTagException("This project does not belong to the current user");
		}
		
		return projectRepository.findByProjTag(projTag);
	}
	
	// updates project: don't need to pass in projTag b/c entity instance already has it, so it will save
	// over record with matching id as the project passed
	public Project updateProject(Project updatedProject, String username) {
		
		//do not allow update if principal is not the projOwner of this project
		if(!updatedProject.getProjOwner().equals(username)){
			throw new ProjectTagException("This project does not belong to the current user");
		}
		
		try {
			//find user set user field and projOwner field in project obj
			User user = userRepository.findByUsername(username);
			
			updatedProject.setUser(user);
			updatedProject.setProjOwner(user.getUsername());
			
			//associated backlog will not be passed in req body
			//project's backlog is set with backlog that is found 
			updatedProject.setBacklog(backlogRepository.findByProjTag(updatedProject.getProjTag()));
			return projectRepository.save(updatedProject);
		}catch(Exception e) {
			throw new ProjectTagException("Project Update failed");
		}
	}
	
	// deletes a project based on projTag
	public void deleteProjectByProjTag(String projTag, String username) {
		Project projectToDelete = projectRepository.findByProjTag(projTag.toUpperCase());
		
		if(projectToDelete == null) {
			throw new ProjectTagException("Project Tag " + projTag + " does not exist");
		}
		
		if(!projectToDelete.getProjOwner().equals(username)) {
			throw new ProjectTagException("This project does not belong to the current user");
		}
		
		projectRepository.delete(projectToDelete);
	}
}
