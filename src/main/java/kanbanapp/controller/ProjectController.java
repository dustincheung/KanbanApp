package kanbanapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
	
	@RequestMapping(method=RequestMethod.POST, value="/projects")
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project, BindingResult result){
		
		ResponseEntity<?> mapErrors = errorMappingService.mapErrors(result);
		
		if(mapErrors != null) {
			return mapErrors;
		}
		
		projectService.createProject(project);
		return new ResponseEntity<Project>(project, HttpStatus.CREATED);
	}
}
