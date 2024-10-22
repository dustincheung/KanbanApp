/*
 *  Repository class for Project, handles CRUD operations on database. Inherits CRUD methods from 
 *  CrudRepository class.
 */

package kanbanapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kanbanapp.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

		//used for show route, instead of the default find by primary key id we are finding by projTag
		Project findByProjTag(String projTag);
		
		@Override
		Iterable<Project> findAll();
		
		Iterable<Project> findAllByProjOwner(String username);
}
