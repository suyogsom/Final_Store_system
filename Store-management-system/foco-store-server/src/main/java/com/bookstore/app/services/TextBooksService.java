package com.bookstore.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.app.exceptions.EmptyBlankFieldsException;
import com.bookstore.app.exceptions.FieldValueNullException;
import com.bookstore.app.exceptions.NullFieldsException;
import com.bookstore.app.exceptions.ResourceNotFoundException;
import com.bookstore.app.exceptions.TextBooksISBNFormatException;
import com.bookstore.app.exceptions.UUIDAdditionException;
import com.bookstore.app.exceptions.UUIDUpdateException;
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
			throw new ResourceNotFoundException();
		}
		else {
			return new ResponseEntity<>(textBooksRepo.findById(id).get(), HttpStatus.OK);
		}			
	}

	public ResponseEntity<TextBooks> addTextbook(TextBooks textBook){	
		int count = 0 ;
		if(textBook.getTextBookId().toString().length()>0) {
			count++; throw new UUIDAdditionException(); 
		}
		if(textBook.getDepartment()==null||textBook.getDescription()==null||textBook.getIsbn()==null||textBook.getName()==null||textBook.getUnitPrice()==null) {
			count++; throw new NullFieldsException(); 
		}
		if(textBook.getDescription().trim().length()==0||textBook.getIsbn().trim().length()==0||textBook.getName().trim().length()==0) {
			count++; throw new EmptyBlankFieldsException(); 
		}
		if(textBook.getDescription().equals("null")||textBook.getIsbn().trim().equals("null")||textBook.getName().trim().equals("null")) {
			count++; throw new FieldValueNullException(); 
		}
		if(!textBook.getIsbn().trim().matches("[0-9]+")) {
			count++; throw new TextBooksISBNFormatException(); 
		}
		if(count > 0) {
			return new ResponseEntity<>(textBook, HttpStatus.BAD_REQUEST);
		}
		else {
			textBooksRepo.save(textBook);
			return new ResponseEntity<>(textBook, HttpStatus.OK);
		}	
	}

	public ResponseEntity<TextBooks> updateTextbook(TextBooks textBook, UUID id) {
		if(!availabilityCheck(id)) {
			throw new ResourceNotFoundException();
		}	
		else {
			if(textBook.getDepartment()==null||textBook.getDescription()==null||textBook.getIsbn()==null||textBook.getName()==null||textBook.getUnitPrice()==null) {
				throw new NullFieldsException(); 
			}
			if(textBook.getDescription().trim().length()==0||textBook.getIsbn().trim().length()==0||textBook.getName().trim().length()==0) {
				throw new EmptyBlankFieldsException(); 
			}
			if(textBook.getDescription().equals("null")||textBook.getIsbn().trim().equals("null")||textBook.getName().trim().equals("null")) {
				throw new FieldValueNullException(); 
			}
			if(!textBook.getIsbn().trim().matches("[0-9]+")) {
				throw new TextBooksISBNFormatException(); 
			}
			
			if(!availabilityCheck(textBook.getTextBookId())) { 
				throw new UUIDUpdateException(); 
			}
			 			
			TextBooks textToUpdate = textBooksRepo.findById(id).get();
			textToUpdate = textBook;
			textBooksRepo.save(textToUpdate);
		    return new ResponseEntity<>(textBooksRepo.findById(id).get(), HttpStatus.OK);
		}	
	}

	public ResponseEntity<String> deleteTextbook(UUID id){			 
		if(!availabilityCheck(id)) {
			throw new ResourceNotFoundException();
		}
		else {
			textBooksRepo.deleteById(id);
			return new ResponseEntity<>("[Book id " + id + " deleted]", HttpStatus.OK);
		}
	}
	
	public boolean availabilityCheck(UUID id) {
		return textBooksRepo.existsById(id);
	}
}
