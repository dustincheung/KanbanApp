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
			
			//ensure username is unique
			
			//ensure password matches confirmPassword
			
			//only persist password, confirmPassword should not be saved in db 
			
			return userRepository.save(newUser);
		}catch(Exception e) {
			throw new UserException("The username " + newUser.getUsername() + " already exists");
		}
	}
}
