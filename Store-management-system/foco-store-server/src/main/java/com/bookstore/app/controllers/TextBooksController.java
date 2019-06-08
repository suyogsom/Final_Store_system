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

import com.bookstore.app.models.TextBooks;
import com.bookstore.app.services.TextBooksService;

@RestController
@RequestMapping(value="/books/textbooks",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"})
public class TextBooksController {
	
	@Autowired
	private TextBooksService textBooksService;
	
	@GetMapping(value="/all")
	public List<TextBooks> getAllTextbooks(){	
		return textBooksService.getAllTextbooks(); 
	}	

	@GetMapping(value="/{textId}")
	public ResponseEntity<TextBooks> getTextbook(@PathVariable UUID textId)  {	
		return textBooksService.getTextbook(textId);	
	}

	@PostMapping(value="/add")
	public ResponseEntity<TextBooks> addTextbook(@Valid @RequestBody TextBooks textbook)  {	
		return textBooksService.addTextbook(textbook);   
	}

	@PostMapping(value="/update/{id}")
	public ResponseEntity<TextBooks> updateTextbook(@RequestBody TextBooks textbook, @PathVariable UUID id)  {	
		return textBooksService.updateTextbook(textbook, id);  
	}

	@PostMapping(value="/delete/{id}")
	public ResponseEntity<String> deleteTextbook( @PathVariable UUID id)  {   
		return textBooksService.deleteTextbook(id);	
	}									
}
