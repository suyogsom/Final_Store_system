package com.bookstore.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.app.models.TextBooks;
import com.bookstore.app.services.TextBooksService;

@RestController
public class TextBooksController {
	
	@Autowired
	private TextBooksService textBooksService;
	
	@GetMapping(value="/books/textbooks",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"})
	public Iterable<TextBooks> getAllTextbooks(){	
		return textBooksService.getAllTextbooks(); 
	}
	

	@GetMapping(value="/books/textbooks/{textId}",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"})  
	public TextBooks getTextbook(@PathVariable Integer textId)  {	
		return textBooksService.getTextbook(textId);	
	}

	@PostMapping(value="/books/textbooks/add",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"}) 
	public void addTextbook(@RequestBody TextBooks textbook)  {	
		textBooksService.addTextbook(textbook);   
	}

	@PostMapping(value="/books/textbooks/update/{id}",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"}) 
	public void updateTextbook(@RequestBody TextBooks textbook, @PathVariable Integer id)  {	
		textBooksService.updateTextbook(textbook, id);  
	}

	@PostMapping(value="/books/textbooks/delete/{id}",consumes = {"application/json", "application/xml"},produces = {"application/json", "application/xml"}) 
	public void deleteTextbook( @PathVariable Integer id)  {   
		textBooksService.deleteTextbook(id);	
	}									
}
