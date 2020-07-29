/*
 *  Repository class for Task, handles CRUD operations on database. Inherits CRUD methods from 
 *  CrudRepository class.
 */

package kanbanapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kanbanapp.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{
	
	List<Task> findByProjTagOrderByPriority(String projTag);
	
	Task findByTaskTag(String taskTag);
}
