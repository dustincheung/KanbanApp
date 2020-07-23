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
	
	// Index Route
	@RequestMapping("/projects/{projTag}/tasks")
	public Iterable<Task> indexTasks (@PathVariable String projTag, Principal principal){
		return taskService.indexTasks(projTag, principal.getName());
	}
	
	// Create Route
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{projTag}/tasks")
	public ResponseEntity<?> createTask(@Valid @RequestBody Task task, @PathVariable String projTag, Principal principal, BindingResult result){
		
		ResponseEntity<?> mapErrors = errorMappingService.mapErrors(result);
		
		if(mapErrors != null) {
			return mapErrors;
		}
		
		Task newTask = taskService.createTask(task, projTag, principal.getName());
		
		return new ResponseEntity<Task>(newTask, HttpStatus.CREATED);
	}
	
	// Show Route
	@RequestMapping("/projects/{projTag}/tasks/{taskTag}")
	public ResponseEntity<?> showTask(@PathVariable String projTag, @PathVariable String taskTag, Principal principal){
		Task task = taskService.showTask(projTag, taskTag, principal.getName());
		
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
	
	// Update Route is passed in updated task obj in request body
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{projTag}/tasks/{taskTag}")
	public ResponseEntity<?> updateTask(@Valid @RequestBody Task task, @PathVariable String projTag, @PathVariable String taskTag, BindingResult result){
		
		ResponseEntity<?> mapErrors = errorMappingService.mapErrors(result);
		
		if(mapErrors != null) {
			return mapErrors;
		}
		
		Task updatedTask = taskService.updateTask(task);
		
		return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
	}
	
	// Delete Route
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{projTag}/tasks/{taskTag}/delete")
	public ResponseEntity<?> deleteTask(@PathVariable String taskTag){
		taskService.deleteTaskbyTaskTag(taskTag);
		
		return new ResponseEntity<String>("Task with task tag: " + taskTag + " was deleted successfully", HttpStatus.OK);
	}
}
