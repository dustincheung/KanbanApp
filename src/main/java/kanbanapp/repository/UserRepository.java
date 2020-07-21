package kanbanapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kanbanapp.model.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
	
	User getById(Long id);
	User findByUsername(String username);
}
