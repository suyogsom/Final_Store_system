package com.bookstore.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.app.models.TextBooks;

@Repository
public interface TextBooksCRUDRepo extends JpaRepository<TextBooks, Integer>{ 

}


