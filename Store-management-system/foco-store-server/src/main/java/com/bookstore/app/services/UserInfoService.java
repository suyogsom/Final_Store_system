package com.bookstore.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.app.exceptionHandling.TextBooksNotFoundException;
import com.bookstore.app.models.UserInfo;
import com.bookstore.app.repositories.UserInfoRepo;

@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoRepo userRepo;

	public List<UserInfo> getAllUsers(){		
		return userRepo.findAll();		
	}

	public ResponseEntity<UserInfo> getUser(UUID id){ 
		if(!availabilityCheck(id)) {
			throw new TextBooksNotFoundException();
		}
		else {
			return new ResponseEntity<>(userRepo.findById(id).get(), HttpStatus.OK);
		}
	}

	public ResponseEntity<UserInfo> addUser(UserInfo userInfo){	
		
		if(userInfo.getAddress()==null||userInfo.getDepartment()==null||userInfo.getEmail()==null||userInfo.getGender()==null||userInfo.getName()==null||userInfo.getPhoneNumber()==null||userInfo.getStatus()==null) {
			throw new TextBooksNotFoundException();
		}
		
		userRepo.save(userInfo);
		return new ResponseEntity<>(userInfo, HttpStatus.OK);
	}

	public ResponseEntity<UserInfo> updateUser(UserInfo userInfo, UUID id) {	
		if(!availabilityCheck(id)) {
			throw new TextBooksNotFoundException();
		}
		else {
			UserInfo userToUpdate = userRepo.findById(id).get();
			userToUpdate = userInfo;
			userRepo.save(userToUpdate);
		    return new ResponseEntity<>(userRepo.findById(id).get(), HttpStatus.OK);
		}	
	}

	public ResponseEntity<String> deleteUser(UUID id){	
		if(!availabilityCheck(id)) {
			throw new TextBooksNotFoundException();
		}
		else {
			userRepo.deleteById(id);
			return new ResponseEntity<>("Book with id " + id + " deleted", HttpStatus.OK);
		}
	}
	
	public boolean availabilityCheck(UUID id) {
		return userRepo.existsById(id);
	}

}
