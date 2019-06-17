package com.bookstore.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.app.exceptions.ResourceNotFoundException;
import com.bookstore.app.models.TextBooks;
import com.bookstore.app.repositories.RentRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class RentService {
	
	@Autowired
	private RentRepo rentRepo;

	public List<String> getAllTextbooks(){
		List<String> allBooks = rentRepo.findAllBooks();
		return allBooks;
	}
	
	public ResponseEntity<String> isBookAvailableForRent(UUID id){	
		String bookAvailable ="BookAvailable : ",userOfBook = "UserID : ",idOfBook = "TextbookID : " ;

		String[] result = new String[3];
		
		boolean bookAvil = false;
		
		TextBooks textBook = rentRepo.findById(id).get();
		
		if(!availabilityCheck(id)) {
			throw new ResourceNotFoundException();
		}
		else {
			if(availabilityCheck(id) && (textBook.getFaculty()==null && textBook.getStudent()==null)) { 
				bookAvil = true; userOfBook += "null";
			}
			else if (textBook.getFaculty()==null) { 
				userOfBook += textBook.getStudent().getUserId(); 
			}
			else if (textBook.getStudent()==null){ 
				userOfBook += textBook.getFaculty().getUserId(); 
			}		
		}			 
	
		result[0] = bookAvailable + bookAvil;
		result[1] = idOfBook + id;
		result[2] = userOfBook;
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(result);
		
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
	public boolean availabilityCheck(UUID id) {
		return rentRepo.existsById(id);
	}
}
