package com.bookstore.app.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.app.models.Faculty;

@Repository
public interface FacultyRepo extends JpaRepository<Faculty, UUID>{ 

	@Query(value="SELECT email FROM BookStoreDB.user",nativeQuery=true)
	List<String> findByEmail();	
	
}


