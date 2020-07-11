package kanbanapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kanbanapp.Exception.ProjectTagException;
import kanbanapp.model.Backlog;
import kanbanapp.model.Project;
import kanbanapp.repository.BacklogRepository;
import kanbanapp.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	// gets all projects
	public Iterable<Project> indexProjects(){
		return projectRepository.findAll();
	}
		
	// creates a new project 
	public Project createProject(Project project) {
		try {
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
	public Project showProject(String projTag) {
		
		Project projectToShow = projectRepository.findByProjTag(projTag);
		
		if(projectToShow == null) {
			throw new ProjectTagException("Project Tag " + projTag + " does not exist");
		}
		
		return projectRepository.findByProjTag(projTag);
	}
	
	// updates project: don't need to pass in projTag b/c entity instance already has it, so it will save
	// over record with matching id as the topic passed
	public Project updateProject(Project project) {
		try {
			//associated backlog will not be passed in req body
			//project's backlog is set with backlog that is found 
			project.setBacklog(backlogRepository.findByProjTag(project.getProjTag()));
			return projectRepository.save(project);
		}catch(Exception e) {
			throw new ProjectTagException("Project Update failed");
		}
	}
	
	// deletes a project based on projTag
	public void deleteProjectByProjTag(String projTag) {
		Project projectToDelete = projectRepository.findByProjTag(projTag.toUpperCase());
		
		if(projectToDelete == null) {
			throw new ProjectTagException("Project Tag " + projTag + " does not exist");
		}
		
		projectRepository.delete(projectToDelete);
	}
}
