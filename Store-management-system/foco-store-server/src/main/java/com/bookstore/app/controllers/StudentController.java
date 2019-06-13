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

import com.bookstore.app.models.Student;
import com.bookstore.app.services.StudentService;

@RestController
@RequestMapping(value="/student",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"})
public class StudentController {
	
	@Autowired
	private StudentService userService;
	
	@GetMapping(value="/all")
	public List<Student> getAllUsers(){	
		return userService.getAllUsers();
	}	

	@GetMapping(value="/{userId}")  
	public ResponseEntity<Student> getUser(@PathVariable UUID userId)  {	
		return userService.getUser(userId);	
	}

	@PostMapping(value="/add") 
	public ResponseEntity<Student> addUser(@Valid @RequestBody Student userInfo)  {	
		return userService.addUser(userInfo);   
	}

	@PostMapping(value="/update/{id}") 
	public ResponseEntity<Student> updateTextbook(@Valid @RequestBody Student userInfo, @PathVariable UUID id)  {	
		return userService.updateUser(userInfo, id);  
	}

	@PostMapping(value="/delete/{id}") 
	public ResponseEntity<String> deleteTextbook( @PathVariable UUID id)  {   
		return userService.deleteUser(id);	
	}				
}
