package kanbanapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kanbanapp.Exception.ProjectTagException;
import kanbanapp.model.Project;
import kanbanapp.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project createProject(Project project) {
		try {
			project.setProjTag(project.getProjTag().toUpperCase());
			return projectRepository.save(project);
			
		}catch(Exception e) {
			throw new ProjectTagException("Project Tag " + project.getProjTag().toUpperCase() + " is already used");
		}
	}
	
	public Project showProject(String projTag) {
		
		Project projectToShow = projectRepository.findByProjTag(projTag);
		
		if(projectToShow == null) {
			throw new ProjectTagException("Project Tag " + projTag + " does not exist");
		}
		
		return projectRepository.findByProjTag(projTag);
	}
}
