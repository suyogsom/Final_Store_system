package com.bookstore.app.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bookstore.app.audit.Auditable;
import com.bookstore.app.models.interfaces.UserGender;

@Entity
@Table(name="User")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "USER_TYPE")
@EntityListeners(AuditingEntityListener.class)
public class User extends Auditable<String> implements Serializable{

	private static final long serialVersionUID = 1L;
	private UUID userId;
	private String department,phoneNumber,email;

	private UserGender gender;
	private UserName name;
	private UserAddress address;

	public User(UUID id, String department, UserName name, UserAddress address, String phoneNumber, String email, UserGender gender) {		
		super(); 
		this.userId = id;  
		this.department=department;  
		this.name = name;
		this.address = address; 
		this.phoneNumber =phoneNumber;	
		this.email = email;
		this.gender = gender;
	}
	
	public User(String department, UserName name, UserAddress address, String phoneNumber, String email,UserGender gender) {		
		super();   
		this.department=department;  
		this.name = name;
		this.address = address; 
		this.phoneNumber =phoneNumber;	
		this.email = email;
		this.gender = gender;
	}		
	
	public User() {
	}

	@Id
	@Column(name = "userId",updatable = false, nullable = false)
	@Type(type="uuid-char")
	@GeneratedValue(generator = "uuid2",strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}	
	
	@Column(name = "name")
	public UserName getName() {
		return name;
	}
			
	public void setName(UserName name) {
		this.name = name;
	}			
	
	@Column(name = "department")
	@NotBlank(message = "department must not be empty")
	@Size(max = 50)
	public String getDepartment() {
		return department;
	}
		
	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "address")
	public UserAddress getAddress() {
		return address;
	}


	public void setAddress(UserAddress address) {
		this.address = address;
	}

	@Column(name = "phoneNumber")
	@NotBlank(message = "phone number must not be empty")
	@Size(max = 50)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Email(message = "email should be a valid email")
	@NotBlank(message = "email must not be empty")
	@Column(name = "email",unique=true)
	@Size(max = 50)
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="gender")
	public UserGender getGender() {
		return gender;
	}

	public void setGender(UserGender gender) {
		this.gender = gender;
	}
}
