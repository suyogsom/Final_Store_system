package com.bookstore.app.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="TEXTBOOKS")
public class TextBooks{
	
	private UserInfo user;
	private Integer textBookId;
	private String department,name,description,isbn;
	private Double unitPrice;	

	/**
	 *  with all for update (all)
	 */
	public TextBooks(UserInfo user,Integer id, String department, String name, String description, String isbn, Double unitPrice) {		
		super(); 
		this.textBookId = id;  
		this.department=department;  
		this.name = name;
		this.description = description; 
		this.isbn =isbn;	
		this.unitPrice = unitPrice;
		this.user = user;
	}

	/**
	 *  without id just to add in database
	 */
	public TextBooks(String department, String name, String description, String isbn, Double unitPrice) {		
		super(); 
		this.department=department;  
		this.name = name;
		this.description = description; 
		this.isbn =isbn;	
		this.unitPrice = unitPrice;
	}	

	/**
	 *  without user just to update book
	 */
	public TextBooks(Integer id, String department, String name, String description, String isbn, Double unitPrice) {		
		super(); 
		this.textBookId = id;  
		this.department=department;  
		this.name = name;
		this.description = description; 
		this.isbn =isbn;	
		this.unitPrice = unitPrice;
	}
	
	public TextBooks() {
	}	
	
	/**
	 *  to generate id automatically  @GeneratedValue(strategy=GenerationType.SEQUENCE) 
	 */
	@Id
	@Column(name = "textBookId")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)  
	public Integer getTextBookId() {
		return textBookId;
	}

	public void setTextBookId(Integer textBookId) {
		this.textBookId = textBookId;
	}
	
	@Column(name = "name")
	@Size(max = 50)
	public String getName() {
		return name;
	}
			
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "deacription")
	@Size(max = 50)
	public String getDescription() {
		return description;
	}
			
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "isbn")
	public String getIsbn() {
		return isbn;
	}
		
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Column(name = "department")
	@Size(max = 50)
	public String getDepartment() {
		return department;
	}
		
	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "UnitePrice")
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="userIdFK")
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}	
}
