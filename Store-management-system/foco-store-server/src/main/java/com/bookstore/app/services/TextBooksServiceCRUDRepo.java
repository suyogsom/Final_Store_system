package com.bookstore.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.app.models.TextBooks;
import com.bookstore.app.repositories.TextBooksCRUDRepo;

@Service
public class TextBooksServiceCRUDRepo {

	@Autowired
	private TextBooksCRUDRepo textBooksCRUDRepo;
	

	public Iterable<TextBooks> getAllTextbooks(){
		return textBooksCRUDRepo.findAll();
	}

	public TextBooks getTextbook(Integer id){ 
		return textBooksCRUDRepo.findById(id).get();
		
	}

	public void addTextbook(TextBooks textBook){	
		textBooksCRUDRepo.save(textBook);
	}

	public void updateTextbook(TextBooks textBook, Integer id) {
		for(int i=0;i<textBooksCRUDRepo.count();i++) {
			TextBooks textBookUpdate = textBooksCRUDRepo.findById(id).get();
			if(textBookUpdate.getId().equals(id)) {
				textBookUpdate = textBook;
				textBooksCRUDRepo.save(textBookUpdate);
				return;
			}
		}	
	}

	public void deleteTextbook(Integer id){	
		textBooksCRUDRepo.deleteById(id);  
	}
}
