package com.bookstore.app.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.app.models.UserInfo;
import com.bookstore.app.services.UserService;

@RestController
@RequestMapping(value="/user",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"})
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/allUsers")
	public Iterable<UserInfo> getAllUsers(){	
		return userService.getAllUsers();
	}	

	@GetMapping(value="/{userId}")  
	public ResponseEntity<UserInfo> getUser(@PathVariable UUID userId)  {	
		return userService.getUser(userId);	
	}

	@PostMapping(value="/add") 
	public ResponseEntity<UserInfo> addUser(@Valid @RequestBody UserInfo userInfo)  {	
		return userService.addUser(userInfo);   
	}

	@PostMapping(value="/update/{id}") 
	public ResponseEntity<UserInfo> updateTextbook(@Valid @RequestBody UserInfo userInfo, @PathVariable UUID id)  {	
		return userService.updateUser(userInfo, id);  
	}

	@PostMapping(value="/delete/{id}") 
	public ResponseEntity<String> deleteTextbook( @PathVariable UUID id)  {   
		return userService.deleteUser(id);	
	}				
}
