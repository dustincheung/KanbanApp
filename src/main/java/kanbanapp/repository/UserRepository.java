/*
 *  Repository class for User, handles CRUD operations on database. Inherits CRUD methods from 
 *  CrudRepository class.
 */

package kanbanapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kanbanapp.model.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
	
	User getById(Long id);
	User findByUsername(String username);
}
