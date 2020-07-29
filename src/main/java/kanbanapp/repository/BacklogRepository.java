/*
 *  Repository class for Backlog, handles CRUD operations on database. Inherits CRUD methods from 
 *  CrudRepository class.
 */

package kanbanapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kanbanapp.model.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long>{
	
	Backlog findByProjTag(String projTag);
}