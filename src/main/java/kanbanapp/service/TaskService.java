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
	public Iterable<Task> indexTasks(String projTag){
		
		return taskRepository.findByProjTagOrderByPriority(projTag);
	}
	
	// creates a new task, takes in new task obj and associated Backlog projTag
	public Task createTask(Task task, String projTag) {
		
		try {
			// find backlog record that you want to add tasks to, and set it to the new created task 
			Backlog backlog = backlogRepository.findByProjTag(projTag);
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
	public Task showTask(String projTag, String taskTag) {
		
		// checking backlog
		Backlog backlog = backlogRepository.findByProjTag(projTag);
		
		if(backlog == null) {
			throw new BacklogNotFoundException("Backlog with projTag " + projTag + " does not exist");
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
	public Task updateTask(Task updatedTask) {
		try {
			return taskRepository.save(updatedTask);
		}catch(Exception e) {
			throw new BacklogNotFoundException("Task update failed");
		}
	}
	
	// deletes a task with 
	public void deleteTaskbyTaskTag(String taskTag) {
		Task taskToDelete = taskRepository.findByTaskTag(taskTag);
		
		if(taskToDelete == null) {
			throw new BacklogNotFoundException("Task Tag " + taskTag + " does not exist");
		}
		
		taskRepository.delete(taskToDelete);
	}
}
