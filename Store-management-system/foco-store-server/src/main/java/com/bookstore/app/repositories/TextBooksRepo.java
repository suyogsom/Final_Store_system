package com.bookstore.app.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.app.models.TextBooks;

@Repository
public interface TextBooksRepo extends JpaRepository<TextBooks, UUID>{ 

}


