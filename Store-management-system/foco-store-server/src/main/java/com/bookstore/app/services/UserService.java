package com.bookstore.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.app.models.User;
import com.bookstore.app.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers(){		
		return userRepository.getAllUsers();		
	}

	public List<User> getUser(String id){ 
		return userRepository.findByName(id);  
	}

	public void addUser(User user){	
		userRepository.addUser( user);
	}

	public void updateUser(User user, String id) {	
		userRepository.updateUser(user,id);	
	}

	public void deleteUser(String id){	
		userRepository.deleteById(id);	   
	}

}
