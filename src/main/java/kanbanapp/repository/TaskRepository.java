package kanbanapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kanbanapp.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{

}
