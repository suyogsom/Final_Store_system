package com.bookstore.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.app.models.UserInfo;
import com.bookstore.app.repositories.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;

	public List<UserInfo> getAllUsers(){		
		return userRepo.findAll();		
	}

	public UserInfo getUser(Integer userId){ 
		return userRepo.findById(userId).get();
	}

	public void addUser(UserInfo userInfo){	
		userRepo.save(userInfo);
	}

	public void updateUser(UserInfo userInfo, Integer id) {	
		for(int i=0;i<userRepo.count();i++) {
			UserInfo userInfoUpdate = userRepo.findById(id).get();
			if(userInfoUpdate.getUserId().equals(id)) {
				userInfoUpdate = userInfo;
				userRepo.save(userInfoUpdate);
				return;
			}
		}	
	}

	public void deleteUser(Integer id){	
		userRepo.deleteById(id);	   
	}

}
