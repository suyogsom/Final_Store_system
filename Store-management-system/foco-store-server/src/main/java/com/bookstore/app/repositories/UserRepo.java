package com.bookstore.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.app.models.UserInfo;

@Repository
public interface UserRepo extends JpaRepository<UserInfo, Integer>{	}
