package com.bookstore.app.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User
{
	@Id
	private String userId;
	private String department,name,address,phoneNumber,email;	

	public User(String id, String department, String name, String address, String phoneNumber, String email) {		
		super(); 
		this.userId = id;  
		this.department=department;  
		this.name = name;
		this.address = address; 
		this.phoneNumber =phoneNumber;	
		this.email = email;
	}	
	
	public User() {
	}	
		
	public String getName() {
		return name;
	}
			
	public void setName(String name) {
		this.name = name;
	}			
	
	public String getDepartment() {
		return department;
	}
		
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}		
}
