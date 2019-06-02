package com.bookstore.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name="User")
public class UserInfo{
	 
	private Integer userId;
	private String department,name,address,phoneNumber,email;	

	public UserInfo(Integer id, String department, String name, String address, String phoneNumber, String email) {		
		super(); 
		this.userId = id;  
		this.department=department;  
		this.name = name;
		this.address = address; 
		this.phoneNumber =phoneNumber;	
		this.email = email;
	}
	
	public UserInfo(String department, String name, String address, String phoneNumber, String email) {		
		super();   
		this.department=department;  
		this.name = name;
		this.address = address; 
		this.phoneNumber =phoneNumber;	
		this.email = email;
	}	

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public UserInfo() {
	}	
	
	@Column(name = "name")
	@Size(max = 50)
	public String getName() {
		return name;
	}
			
	public void setName(String name) {
		this.name = name;
	}			
	
	@Column(name = "department")
	@Size(max = 50)
	public String getDepartment() {
		return department;
	}
		
	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "address")
	@Size(max = 50)
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Email
	@Column(name = "email",unique=true)
	@Size(max = 100)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}		
}
