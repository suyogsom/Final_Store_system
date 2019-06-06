package com.bookstore.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.app.models.TextBooks;
import com.bookstore.app.repositories.TextBooksRepo;

@Service
public class TextBooksService {

	@Autowired
	private TextBooksRepo textBooksRepo;
	

	public List<TextBooks> getAllTextbooks(){
		return textBooksRepo.findAll();
	}

	public TextBooks getTextbook(UUID id){ 
		return textBooksRepo.findById(id).get();
		
	}

	public void addTextbook(TextBooks textBook){	
		textBooksRepo.save(textBook);
	}

	public void updateTextbook(TextBooks textBook, UUID id) {
		TextBooks textToUpdate = textBooksRepo.findById(id).get();
		textToUpdate = textBook;
		textBooksRepo.save(textToUpdate);
		return;
	}

	public void deleteTextbook(UUID id){	
		textBooksRepo.deleteById(id);  
	}
}
