/*
 *  Controller class that handles RESTful routes for projects.  Allows for cross origin requests.
 */

package kanbanapp.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kanbanapp.model.Project;
import kanbanapp.service.ErrorMappingService;
import kanbanapp.service.ProjectService;

@RestController
@CrossOrigin
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired 
	ErrorMappingService errorMappingService;
	
	//Index Route
	@RequestMapping("/projects")
	public Iterable<Project> indexProjects(Principal principal){
		return projectService.indexProjects(principal.getName());
	}
	  
	//Create Route 
	@RequestMapping(method = RequestMethod.POST, value = "/projects")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, Principal principal, BindingResult result){
		
		ResponseEntity<?> mapErrors = errorMappingService.mapErrors(result);
		
		if(mapErrors != null) {
			return mapErrors;
		}
		
		projectService.createProject(project, principal.getName());
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}
	
	//Show Route
	@RequestMapping("/projects/{projTag}")
	public ResponseEntity<?> showProject(@PathVariable String projTag, Principal principal){
		Project projectToShow = projectService.showProject(projTag, principal.getName());
		
		return new ResponseEntity<Project>(projectToShow, HttpStatus.OK);
	}
	
	//Update Route
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{projTag}")
	public ResponseEntity<?> updateProject(@RequestBody Project project, @PathVariable String projTag, Principal principal){
		//not necessary to pass in projTag b/c our project in the body will contain primary key id already
		projectService.updateProject(project, principal.getName());
		return new ResponseEntity<Project>(project, HttpStatus.OK);
	}
	
	//Delete Route
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{projTag}/delete")
	public ResponseEntity<?> deleteProject(@PathVariable String projTag, Principal principal){
		projectService.deleteProjectByProjTag(projTag, principal.getName());
		
		return new ResponseEntity<String>("Project with Tag: " + projTag + " was deleted successfully", HttpStatus.OK);
	}
	
}
