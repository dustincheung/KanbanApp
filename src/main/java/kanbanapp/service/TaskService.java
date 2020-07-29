/*
 *  Service class for tasks.  Handles application logic and exception handling before performing
 *  operations on the database.  
 */

package kanbanapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kanbanapp.Exception.BacklogNotFoundException;
import kanbanapp.model.Backlog;
import kanbanapp.model.Task;
import kanbanapp.repository.BacklogRepository;
import kanbanapp.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	// indexes all tasks associated to a Backlog projTag
	public Iterable<Task> indexTasks(String projTag, String username){
		
		//check if backlog (contains associated tasks) belongs to the current user, if not do not index tasks
		Backlog backlog = backlogRepository.findByProjTag(projTag);
		
		if(backlog == null || !backlog.getProject().getProjOwner().equals(username)){
			throw new BacklogNotFoundException("Current user can only view their own project's tasks");
		}
		
		return taskRepository.findByProjTagOrderByPriority(projTag);
	}
	
	// creates a new task, takes in new task obj and associated Backlog projTag
	public Task createTask(Task task, String projTag, String username) {
		
		try {
			// find backlog record that you want to add tasks to, and set it to the new created task 
			Backlog backlog = backlogRepository.findByProjTag(projTag);
			
			//if the projOwner of the project assoc. w/ this backlog is not the curr user, do not allow task to be added
			if(backlog == null || !backlog.getProject().getProjOwner().equals(username)){
				throw new BacklogNotFoundException("");
			}
			
			task.setBacklog(backlog);
			Integer taskSeqIncrementor = backlog.getTaskSeqIncrementor();
			
			// taskTag is in format PROJ-1
			task.setTaskTag(projTag + "-" + taskSeqIncrementor);
			task.setProjTag(projTag);
			
			backlog.setTaskSeqIncrementor(++taskSeqIncrementor);
			
			// set low priority if no priority is initially specified
			if(task.getPriority() == null || task.getPriority() == 0) {
				task.setPriority(3);
			}
			
			// set Todo status if no status is initially specified
			if(task.getStatus() == null || task.getStatus().isEmpty()) {
				task.setStatus("Todo");
			}
			
			return taskRepository.save(task);
		}catch(Exception e) {
			throw new BacklogNotFoundException("Backlog with the projTag specified not found");
		}
	}
	
	// shows a unique task by first finding task by unique taskTag
	// ensures backlog exists, task exists, and backlog from path contains task
	public Task showTask(String projTag, String taskTag, String username) {
		
		// checking backlog
		Backlog backlog = backlogRepository.findByProjTag(projTag);
		
		//if the projOwner of the project assoc. w/ this backlog is not the curr user, do not allow show tasks
		if(backlog == null || !backlog.getProject().getProjOwner().equals(username)){
			throw new BacklogNotFoundException("Current user can only view their own project's tasks");
		}
		
		// checking task
		Task task = taskRepository.findByTaskTag(taskTag);
		
		if(task == null) {
			throw new BacklogNotFoundException("Task with taskTag " + taskTag + " does not exist");
		}
		
		// checking if task found belongs to correct backlog
		if(!task.getProjTag().contentEquals(backlog.getProjTag())) {
			throw new BacklogNotFoundException("Task with taskTag " + taskTag + " does not belong in backlog with projTag " + projTag);
		}
		
		return task;
	}
	
	// updates a task and takes in new updated task
	public Task updateTask(Task updatedTask, String username) {
		
		//check if backlog (contains associated tasks) belongs to the current user, if not do not index tasks
		Backlog backlog = backlogRepository.findByProjTag(updatedTask.getProjTag());
				
		if(backlog == null || !backlog.getProject().getProjOwner().equals(username)){
			throw new BacklogNotFoundException("Current user can only update their own project's tasks");
		}
		
		try {
			return taskRepository.save(updatedTask);
		}catch(Exception e) {
			throw new BacklogNotFoundException("Task update failed");
		}
	}
	
	// deletes a task with 
	public void deleteTaskbyTaskTag(String projTag, String taskTag, String username) {
		
		//check if backlog (contains associated tasks) belongs to the current user, if not do not allow delete
		Backlog backlog = backlogRepository.findByProjTag(projTag);
						
		if(backlog == null || !backlog.getProject().getProjOwner().equals(username)){
			throw new BacklogNotFoundException("Current user can only delete their own project's tasks");
		}
		
		Task taskToDelete = taskRepository.findByTaskTag(taskTag);
		
		if(taskToDelete == null) {
			throw new BacklogNotFoundException("Task Tag " + taskTag + " does not exist");
		}
		
		taskRepository.delete(taskToDelete);
	}
}
