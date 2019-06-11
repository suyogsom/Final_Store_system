package com.bookstore.app.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookstore.app.models.UserInfo;

@Repository
public interface UserRepo extends JpaRepository<UserInfo, UUID>{ //, CustomRepo{
	
	@Query(value="SELECT email FROM BookStoreDB.userinfo",nativeQuery=true)
	List<String> findByEmail();	
}
