package kanbanapp.controller;

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
import kanbanapp.model.Task;
import kanbanapp.service.ErrorMappingService;
import kanbanapp.service.TaskService;

@RestController
@CrossOrigin
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	ErrorMappingService errorMappingService;
	
	@RequestMapping(method = RequestMethod.POST, value="/projects/{projTag}/tasks")
	public ResponseEntity<?> createTask(@Valid @RequestBody Task task, @PathVariable String projTag, BindingResult result){
		
		ResponseEntity<?> mapErrors = errorMappingService.mapErrors(result);
		
		if(mapErrors != null) {
			return mapErrors;
		}
		
		Task newTask = taskService.createTask(task, projTag);
		
		return new ResponseEntity<Task>(newTask, HttpStatus.CREATED);
	}
}
