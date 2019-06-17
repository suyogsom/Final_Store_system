package com.bookstore.app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.app.exceptions.EmailDuplicationException;
import com.bookstore.app.exceptions.EmailFormatException;
import com.bookstore.app.exceptions.EmptyBlankFieldsException;
import com.bookstore.app.exceptions.FieldValueNullException;
import com.bookstore.app.exceptions.NullFieldsException;
import com.bookstore.app.exceptions.NumberFormatException;
import com.bookstore.app.exceptions.ResourceNotFoundException;
import com.bookstore.app.exceptions.UUIDUpdateException;
import com.bookstore.app.models.Faculty;
import com.bookstore.app.repositories.FacultyRepo;

@Service
public class FacultyService {
	
	@Autowired
	private FacultyRepo userRepo;

	public List<Faculty> getAllUsers(){		
		return userRepo.findAll();		
	}

	public ResponseEntity<Faculty> getUser(UUID id){ 
		if(!availabilityCheck(id)) {
			throw new ResourceNotFoundException();
		}
		else {
			return new ResponseEntity<>(userRepo.findById(id).get(), HttpStatus.OK);
		}
	}

	public ResponseEntity<Faculty> addUser(Faculty user){	
		int count = 0 ;
		/*
		 * if(userInfo.getUserId().toString().length()>0) { count++; throw new
		 * UUIDAdditionException(); }
		 */
		if(user.getProgram()==null||user.getJoiningDate()==null||user.getTenuarDate()==null||user.getAddress().getApartment()==null||user.getAddress().getCity()==null||user.getAddress().getCountry()==null||user.getAddress().getState()==null||user.getAddress().getStreet()==null||user.getAddress().getZipcode()==null||user.getDepartment()==null||user.getEmail()==null||user.getGender()==null||user.getName().getFirstName()==null||user.getName().getLastName()==null||user.getName().getMiddleName()==null||user.getPhoneNumber()==null) {
			count++ ; throw new NullFieldsException(); 
		}
		if(user.getProgram().trim().length()==0||user.getAddress().getApartment().trim().length()==0||user.getAddress().getCity().trim().length()==0||user.getAddress().getCountry().trim().length()==0||user.getAddress().getState().trim().length()==0||user.getAddress().getStreet().trim().length()==0||user.getAddress().getZipcode().trim().length()==0||user.getDepartment().trim().length()==0||user.getName().getFirstName().trim().length()==0||user.getName().getLastName().trim().length()==0||user.getName().getMiddleName().trim().length()==0||user.getEmail().trim().length()==0||user.getPhoneNumber().trim().length()==0) {
			count++ ; throw new EmptyBlankFieldsException(); 
		}
		if(user.getProgram().trim().equalsIgnoreCase("null")||user.getAddress().getApartment().trim().equalsIgnoreCase("null")||user.getAddress().getCity().trim().equalsIgnoreCase("null")||user.getAddress().getCountry().trim().equalsIgnoreCase("null")||user.getAddress().getState().trim().equalsIgnoreCase("null")||user.getAddress().getStreet().trim().equalsIgnoreCase("null")||user.getAddress().getZipcode().trim().equalsIgnoreCase("null")||user.getName().getFirstName().trim().equalsIgnoreCase("null")||user.getName().getLastName().trim().equalsIgnoreCase("null")||user.getName().getMiddleName().trim().equalsIgnoreCase("null")||user.getDepartment().trim().equalsIgnoreCase("null")||user.getEmail().trim().equalsIgnoreCase("null")||user.getPhoneNumber().trim().equalsIgnoreCase("null")) {
			throw new FieldValueNullException(); 
		}
		if((!user.getPhoneNumber().matches("[0-9]+"))||(!user.getPhoneNumber().matches("[0-9]+"))) {
			count++; throw new NumberFormatException(); 
		}
		/*
		 * this email check is just for top level Internet domains,  does not includes all country domains
		 */
		if((!user.getEmail().contains("@"))||(!user.getEmail().contains(".com"))) {
			count++; throw new EmailFormatException(); 
		}
		/*
		 * checking for duplicate email's in database
		 */
		List<String> userList = userRepo.findByEmail();	
		long emailCount = userList.stream().filter(e->e.equalsIgnoreCase(user.getEmail())).count();
		if(emailCount > 0) {
			count++; throw new EmailDuplicationException();
		}
		
		if(count > 0) {
			return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
		}
		else {
			userRepo.save(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}	
	}

	public ResponseEntity<Faculty> updateUser(Faculty user, UUID id) {	
		if(!availabilityCheck(id)) {
			throw new ResourceNotFoundException();
		}
		else {			
			if(user.getProgram()==null||user.getTenuarDate()==null||user.getJoiningDate()==null||user.getAddress().getApartment()==null||user.getAddress().getCity()==null||user.getAddress().getCountry()==null||user.getAddress().getState()==null||user.getAddress().getStreet()==null||user.getAddress().getZipcode()==null||user.getDepartment()==null||user.getEmail()==null||user.getGender()==null||user.getName().getFirstName()==null||user.getName().getLastName()==null||user.getName().getMiddleName()==null||user.getPhoneNumber()==null) {
				throw new NullFieldsException(); 
			}
			if(user.getProgram().trim().length()==0||user.getAddress().getApartment().trim().length()==0||user.getAddress().getCity().trim().length()==0||user.getAddress().getCountry().trim().length()==0||user.getAddress().getState().trim().length()==0||user.getAddress().getStreet().trim().length()==0||user.getAddress().getZipcode().trim().length()==0||user.getDepartment().trim().length()==0||user.getName().getFirstName().trim().length()==0||user.getName().getLastName().trim().length()==0||user.getName().getMiddleName().trim().length()==0||user.getEmail().trim().length()==0||user.getPhoneNumber().trim().length()==0) {
				throw new EmptyBlankFieldsException(); 
			}
			if(user.getProgram().trim().equalsIgnoreCase("null")||user.getAddress().getApartment().trim().equalsIgnoreCase("null")||user.getAddress().getCity().trim().equalsIgnoreCase("null")||user.getAddress().getCountry().trim().equalsIgnoreCase("null")||user.getAddress().getState().trim().equalsIgnoreCase("null")||user.getAddress().getStreet().trim().equalsIgnoreCase("null")||user.getAddress().getZipcode().trim().equalsIgnoreCase("null")||user.getName().getFirstName().trim().equalsIgnoreCase("null")||user.getName().getLastName().trim().equalsIgnoreCase("null")||user.getName().getMiddleName().trim().equalsIgnoreCase("null")||user.getDepartment().trim().equalsIgnoreCase("null")||user.getEmail().trim().equalsIgnoreCase("null")||user.getPhoneNumber().trim().equalsIgnoreCase("null")) {
				throw new FieldValueNullException(); 
			}
			if((!user.getPhoneNumber().matches("[0-9]+"))||(!user.getPhoneNumber().matches("[0-9]+"))) {
				throw new NumberFormatException(); 
			}
			/*
			 * this email check is just for top level Internet domains,  does not includes all country domains
			 */
			if((!user.getEmail().contains("@"))||(!user.getEmail().contains(".com"))) {			
				throw new EmailFormatException(); 
			}
			
			if(!availabilityCheck(user.getUserId())) { 
				throw new UUIDUpdateException(); 
			}			 
			
			Faculty userToUpdate = userRepo.findById(id).get();
			userToUpdate = user;
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
			return new ResponseEntity<>("[ User id " + id + " deleted successfully ]", HttpStatus.OK);
		}
	}
	
	public boolean availabilityCheck(UUID id) {
		return userRepo.existsById(id);
	}

}
