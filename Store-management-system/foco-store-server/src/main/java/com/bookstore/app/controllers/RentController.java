package com.bookstore.app.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.app.services.RentService;

@RestController
@RequestMapping(value="/rent",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"})
public class RentController {
	
	@Autowired
	private RentService userService;
	
	@GetMapping(value="/all")
	public List<String> getAllTextbooks(){	
		return userService.getAllTextbooks();
	}
	
	@GetMapping(value="/available/{id}")
	public ResponseEntity<String> isBookAvailableForRent(@PathVariable UUID id){	
		return userService.isBookAvailableForRent(id);
	}
}
