/*
 *  Service class for users.  Handles application logic and exception handling before performing
 *  operations on the database.  
 */

package kanbanapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kanbanapp.Exception.UserException;
import kanbanapp.model.User;
import kanbanapp.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User createUser(User newUser) {
		try {
			//encrypt password before saving
			String encryptPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
			newUser.setPassword(encryptPassword);
			
			//after confirm password passes controller meaning it matches, set it to blank so that it does not save in db
			newUser.setConfirmPassword("");
			
			return userRepository.save(newUser);
		}catch(Exception e) {
			throw new UserException("The username " + newUser.getUsername() + " already exists");
		}
	}
}
