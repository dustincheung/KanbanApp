package kanbanapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kanbanapp.model.Project;
import kanbanapp.service.ErrorMappingService;
import kanbanapp.service.ProjectService;

@RestController
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired ErrorMappingService errorMappingService;
	
	// Index Route
	@RequestMapping("/projects")
	public Iterable<Project> indexProjects(){
		return projectService.indexProjects();
	}
	
	// Create Route 
	@RequestMapping(method=RequestMethod.POST, value="/projects")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result){
		
		ResponseEntity<?> mapErrors = errorMappingService.mapErrors(result);
		
		if(mapErrors != null) {
			return mapErrors;
		}
		
		projectService.createProject(project);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}
	
	// Show Route
	@RequestMapping("/projects/{projTag}")
	public ResponseEntity<?> showProject(@PathVariable String projTag){
		Project projectToShow = projectService.showProject(projTag);
		
		return new ResponseEntity<Project>(projectToShow, HttpStatus.OK);
	}
	
	// Update Route
	@RequestMapping(method=RequestMethod.PUT, value="/projects/{projTag}")
	public ResponseEntity<?> updateProject(@RequestBody Project project, @PathVariable String projTag){
		//not necessary to pass in projTag b/c our project in the body will contain primary key id already
		projectService.updateProject(project);
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	// Delete Route
	@RequestMapping(method=RequestMethod.DELETE, value="/projects/{projTag}/delete")
	public ResponseEntity<?> deleteProject(@PathVariable String projTag){
		projectService.deleteProjectByProjTag(projTag);
		
		return new ResponseEntity<String>("Project with Tag: " + projTag + " was deleted successfully", HttpStatus.OK);
	}
	
}
