package com.bookstore.app.controllers;

import java.util.List;
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

import com.bookstore.app.models.Faculty;
import com.bookstore.app.services.FacultyService;

@RestController
@RequestMapping(value="/faculty",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"})
public class FacultyController {
	
	@Autowired
	private FacultyService userService;
	
	@GetMapping(value="/all")
	public List<Faculty> getAllUsers(){	
		return userService.getAllUsers();
	}	

	@GetMapping(value="/{userId}")  
	public ResponseEntity<Faculty> getUser(@PathVariable UUID userId)  {	
		return userService.getUser(userId);	
	}

	@PostMapping(value="/add") 
	public ResponseEntity<Faculty> addUser(@Valid @RequestBody Faculty userInfo)  {	
		return userService.addUser(userInfo);   
	}

	@PostMapping(value="/update/{id}") 
	public ResponseEntity<Faculty> updateUser(@Valid @RequestBody Faculty userInfo, @PathVariable UUID id)  {	
		return userService.updateUser(userInfo, id);  
	}

	@PostMapping(value="/delete/{id}") 
	public ResponseEntity<String> deleteUser( @PathVariable UUID id)  {   
		return userService.deleteUser(id);	
	}				
}
