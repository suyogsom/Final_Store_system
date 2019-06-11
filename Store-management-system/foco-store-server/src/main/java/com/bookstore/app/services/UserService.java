package com.bookstore.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.app.exceptions.EmailFormatException;
import com.bookstore.app.exceptions.EmptyBlankFieldsException;
import com.bookstore.app.exceptions.FieldValueNullException;
import com.bookstore.app.exceptions.NullFieldsException;
import com.bookstore.app.exceptions.PhoneNumberFormatException;
import com.bookstore.app.exceptions.ResourceNotFoundException;
import com.bookstore.app.exceptions.UUIDAdditionException;
import com.bookstore.app.exceptions.UUIDUpdateException;
import com.bookstore.app.models.UserInfo;
import com.bookstore.app.repositories.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;

	public List<UserInfo> getAllUsers(){		
		return userRepo.findAll();		
	}

	public ResponseEntity<UserInfo> getUser(UUID id){ 
		if(!availabilityCheck(id)) {
			throw new ResourceNotFoundException();
		}
		else {
			return new ResponseEntity<>(userRepo.findById(id).get(), HttpStatus.OK);
		}
	}

	public ResponseEntity<UserInfo> addUser(UserInfo userInfo){	
		int count = 0 ;
		if(userInfo.getUserId().toString().length()>0) {
			count++; throw new UUIDAdditionException(); 
		}
		if(userInfo.getAddress()==null||userInfo.getDepartment()==null||userInfo.getEmail()==null||userInfo.getGender()==null||userInfo.getName()==null||userInfo.getPhoneNumber()==null||userInfo.getStatus()==null) {
			count++; throw new NullFieldsException(); 
		}
		if(userInfo.getAddress().trim().length()==0||userInfo.getDepartment().trim().length()==0||userInfo.getEmail().trim().length()==0||userInfo.getName().trim().length()==0||userInfo.getPhoneNumber().trim().length()==0) {
			count++; throw new EmptyBlankFieldsException(); 
		}
		if(userInfo.getAddress().trim().equals("null")||userInfo.getDepartment().trim().equals("null")||userInfo.getEmail().trim().equals("null")||userInfo.getName().trim().equals("null")||userInfo.getPhoneNumber().trim().equals("null")) {
			count++; throw new FieldValueNullException(); 
		}
		if(!userInfo.getPhoneNumber().trim().matches("[0-9]+")) {
			count++; throw new PhoneNumberFormatException(); 
		}
		/*
		 * this email check is just for top level Internet domains,  does not includes all country domains
		 */
		if((!userInfo.getEmail().contains("@"))||(!userInfo.getEmail().contains(".com"))) {
			count++; throw new EmailFormatException(); 
		}
		if(count > 0) {
			return new ResponseEntity<>(userInfo, HttpStatus.BAD_REQUEST);
		}
		else {
			userRepo.save(userInfo);
			return new ResponseEntity<>(userInfo, HttpStatus.OK);
		}	
	}

	public ResponseEntity<UserInfo> updateUser(UserInfo userInfo, UUID id) {	
		if(!availabilityCheck(id)) {
			throw new ResourceNotFoundException();
		}
		else {			
			if(userInfo.getAddress()==null||userInfo.getDepartment()==null||userInfo.getEmail()==null||userInfo.getGender()==null||userInfo.getName()==null||userInfo.getPhoneNumber()==null||userInfo.getStatus()==null) {
				throw new NullFieldsException(); 
			}
			if(userInfo.getAddress().trim().length()==0||userInfo.getDepartment().trim().length()==0||userInfo.getEmail().trim().length()==0||userInfo.getName().trim().length()==0||userInfo.getPhoneNumber().trim().length()==0) {
				throw new EmptyBlankFieldsException(); 
			}
			if(userInfo.getAddress().trim().equals("null")||userInfo.getDepartment().trim().equals("null")||userInfo.getEmail().trim().equals("null")||userInfo.getName().trim().equals("null")||userInfo.getPhoneNumber().trim().equals("null")) {
				throw new FieldValueNullException(); 
			}
			if(!userInfo.getPhoneNumber().matches("[0-9]+")) {
				throw new PhoneNumberFormatException(); 
			}
			/*
			 * this email check is just for top level Internet domains,  does not includes all country domains
			 */
			if((!userInfo.getEmail().contains("@"))||(!userInfo.getEmail().contains(".com"))) {			
				throw new EmailFormatException(); 
			}
			
			if(!availabilityCheck(userInfo.getUserId())) { 
				throw new UUIDUpdateException(); 
			}			 
			
			UserInfo userToUpdate = userRepo.findById(id).get();
			userToUpdate = userInfo;
			userRepo.save(userToUpdate);
		    return new ResponseEntity<>(userRepo.findById(id).get(), HttpStatus.OK);
		}		
	}

	public ResponseEntity<String> deleteUser(UUID id){	
		if(!availabilityCheck(id)) {
			throw new ResourceNotFoundException();
		}
		else {
			userRepo.deleteById(id);
			return new ResponseEntity<>("[User id " + id + " deleted successfully]", HttpStatus.OK);
		}
	}
	
	public boolean availabilityCheck(UUID id) {
		return userRepo.existsById(id);
	}

}
