package com.bookstore.app.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.app.models.TextBooks;

@Repository
public interface RentRepo extends JpaRepository<TextBooks, UUID>{ 
		
	@Query(value="SELECT text_book_id, faculty_id_fk, student_id_fk FROM BookStoreDB.textbooks",nativeQuery=true)
	List<String> findAllBooks();	
}


