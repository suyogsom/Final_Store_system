package com.bookstore.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.app.exceptionHandling.TextBooksNotFoundException;
import com.bookstore.app.models.TextBooks;
import com.bookstore.app.repositories.TextBooksRepo;

@Service
public class TextBooksService {

	@Autowired
	private TextBooksRepo textBooksRepo;
	
	public List<TextBooks> getAllTextbooks(){
		return textBooksRepo.findAll();
	}

	public ResponseEntity<TextBooks> getTextbook(UUID id){ 				
		if(!availabilityCheck(id)) {
			throw new TextBooksNotFoundException();
		}
		else {
			return new ResponseEntity<>(textBooksRepo.findById(id).get(), HttpStatus.OK);
		}			
	}

	public ResponseEntity<TextBooks> addTextbook(TextBooks textBook){	
		textBooksRepo.save(textBook);
		return new ResponseEntity<>(textBook, HttpStatus.OK);
	}

	public ResponseEntity<TextBooks> updateTextbook(TextBooks textBook, UUID id) {
		if(!availabilityCheck(id)) {
			throw new TextBooksNotFoundException();
		}
		else {
			TextBooks textToUpdate = textBooksRepo.findById(id).get();
			textToUpdate = textBook;
			textBooksRepo.save(textToUpdate);
		    return new ResponseEntity<>(textBooksRepo.findById(id).get(), HttpStatus.OK);
		}	
	}

	public ResponseEntity<String> deleteTextbook(UUID id){			 
		if(!availabilityCheck(id)) {
			throw new TextBooksNotFoundException();
		}
		else {
			textBooksRepo.deleteById(id);
			return new ResponseEntity<>("Book with id " + id + " deleted", HttpStatus.OK);
		}
	}
	
	public boolean availabilityCheck(UUID id) {
		return textBooksRepo.existsById(id);
	}
}
