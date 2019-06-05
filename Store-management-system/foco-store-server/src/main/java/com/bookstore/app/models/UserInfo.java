package com.bookstore.app.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.bookstore.app.models.interfaces.UserGender;
import com.bookstore.app.models.interfaces.UserStatus;

@Entity
@Table(name="USERINFO")
public class UserInfo {

	private UUID userId;
	private String department,name,address,phoneNumber,email;

	private UserGender gender;
	private UserStatus status; 

	public UserInfo(UUID id, String department, String name, String address, String phoneNumber, String email, UserGender gender, UserStatus status) {		
		super(); 
		this.userId = id;  
		this.department=department;  
		this.name = name;
		this.address = address; 
		this.phoneNumber =phoneNumber;	
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
	
	public UserInfo(String department, String name, String address, String phoneNumber, String email,UserGender gender, UserStatus status) {		
		super();   
		this.department=department;  
		this.name = name;
		this.address = address; 
		this.phoneNumber =phoneNumber;	
		this.email = email;
		this.gender = gender;
		this.status = status;
	}	

	@Id
	@Column(name = "userId",updatable = false, nullable = false, columnDefinition = "BINARY(16)" )   
	@GeneratedValue(generator = "uuid2",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
	public UserInfo() {
	}	
	
	@Column(name = "name")
	@Size(max = 50)
	@NotNull
	public String getName() {
		return name;
	}
			
	public void setName(String name) {
		this.name = name;
	}			
	
	@Column(name = "department")
	@Size(max = 50)
	@NotNull
	public String getDepartment() {
		return department;
	}
		
	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "address")
	@Size(max = 50)
	@NotNull
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "phoneNumber")
	@NotNull
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Email
	@Column(name = "email",unique=true)
	@Size(max = 100)
	@NotNull
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="gender")
	@NotNull
	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="status")
	@NotNull
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
		
//	this is for bi directional one to many and many to one
	
//	private List<TextBooks> booksList = new ArrayList<TextBooks>();
	
//	public UserInfo(List<TextBooks> textBooksList,Integer id, String department, String name, String address, String phoneNumber, String email) {		
//		super(); 
//		this.userId = id;  
//		this.department=department;  
//		this.name = name;
//		this.address = address; 
//		this.phoneNumber =phoneNumber;	
//		this.email = email;
//		this.booksList = textBooksList;
//	}
	
		
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@ElementCollection
//	public List<TextBooks> getTextBooks() {
//		return booksList;
//	}
//
//	public void setTextBooks(List<TextBooks> textBooks) {
//		this.booksList = textBooks;
//	}	
}
