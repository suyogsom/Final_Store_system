package com.bookstore.app.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.app.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, UUID>{ 

	@Query(value="SELECT email FROM BookStoreDB.user",nativeQuery=true)
	List<String> findByEmail();	
	
}


