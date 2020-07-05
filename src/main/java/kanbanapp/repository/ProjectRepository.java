package kanbanapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kanbanapp.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

}
