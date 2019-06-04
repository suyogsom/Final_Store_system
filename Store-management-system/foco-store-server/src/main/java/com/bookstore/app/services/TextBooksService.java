package com.bookstore.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.app.models.TextBooks;
import com.bookstore.app.repositories.TextBooksRepo;

@Service
public class TextBooksService {

	@Autowired
	private TextBooksRepo textBooksRepo;
	

	public Iterable<TextBooks> getAllTextbooks(){
		return textBooksRepo.findAll();
	}

	public TextBooks getTextbook(Integer id){ 
		return textBooksRepo.findById(id).get();
		
	}

	public void addTextbook(TextBooks textBook){	
		textBooksRepo.save(textBook);
	}

	public void updateTextbook(TextBooks textBook, Integer id) {
		for(int i=0;i<textBooksRepo.count();i++) {
			TextBooks textBookUpdate = textBooksRepo.findById(id).get();
			if(textBookUpdate.getTextBookId().equals(id)) {
				textBookUpdate = textBook;
				textBooksRepo.save(textBookUpdate);
				return;
			}
		}	
	}

	public void deleteTextbook(Integer id){	
		textBooksRepo.deleteById(id);  
	}
}
