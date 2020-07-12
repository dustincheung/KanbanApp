package kanbanapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kanbanapp.model.Backlog;
import kanbanapp.model.Task;
import kanbanapp.repository.BacklogRepository;
import kanbanapp.repository.TaskRepository;
import kanbanapp.util.TaskStatus;

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
		
		// set initial status as todo
		task.setStatus(TaskStatus.TODO);
		
		return taskRepository.save(task);
	}
	
	
}
