package kanbanapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kanbanapp.Exception.ProjectTagException;
import kanbanapp.model.Project;
import kanbanapp.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	// gets all projects
	public Iterable<Project> indexProjects(){
		return projectRepository.findAll();
	}
		
	// creates a new project 
	public Project createProject(Project project) {
		try {
			project.setProjTag(project.getProjTag().toUpperCase());
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
	
	// updates project 
	public Project updateProject(Project project) {
		try {
			////don't need to pass in projTag b/c entity instance already has it, so it will save
			//over record with matching id as the topic passed
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
