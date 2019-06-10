package com.bookstore.app.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.app.models.UserInfo;
import com.bookstore.app.services.UserInfoService;

@RestController
@RequestMapping(value="/user",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"})
public class UserController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@GetMapping(value="/allUsers")
	public List<UserInfo> getAllUsers(){	
		return userInfoService.getAllUsers();
	}	

	@GetMapping(value="/{userId}")  
	public ResponseEntity<UserInfo> getUser(@PathVariable UUID userId)  {	
		return userInfoService.getUser(userId);	
	}

	@PostMapping(value="/add") 
	public ResponseEntity<UserInfo> addUser(@RequestBody UserInfo userInfo)  {	
		return userInfoService.addUser(userInfo);   
	}

	@PostMapping(value="/update/{id}") 
	public ResponseEntity<UserInfo> updateTextbook(@RequestBody UserInfo userInfo, @PathVariable UUID id)  {	
		return userInfoService.updateUser(userInfo, id);  
	}

	@PostMapping(value="/delete/{id}") 
	public ResponseEntity<String> deleteTextbook( @PathVariable UUID id)  {   
		return userInfoService.deleteUser(id);	
	}				
}
