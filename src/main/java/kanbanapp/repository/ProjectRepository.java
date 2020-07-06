package kanbanapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kanbanapp.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

		//used for show route, instead of the default find by primary key id we are finding by projTag
		Project findByProjTag(String projTag);
}
