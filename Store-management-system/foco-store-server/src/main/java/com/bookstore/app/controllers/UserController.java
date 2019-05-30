package com.bookstore.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.app.models.User;
import com.bookstore.app.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/user/allUsers",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"})
	public List<User> getAllUsers(){	
		return userService.getAllUsers();
	}	

	@GetMapping(value="/user/allUsers/{userId}",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"})  
	public List<User> getUser(@PathVariable String userId)  {	
		return userService.getUser(userId);	
	}

	@PostMapping(value="/user/add",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"}) 
	public void addUser(@RequestBody User user)  {	
		userService.addUser(user);   
	}

	@PostMapping(value="/user/update/{id}",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"}) 
	public void updateTextbook(@RequestBody User user, @PathVariable String id)  {	
		userService.updateUser(user, id);  
	}

	@PostMapping(value="/user/delete/{id}",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"}) 
	public void deleteTextbook( @PathVariable String id)  {   
		userService.deleteUser(id);	
	}				
}
