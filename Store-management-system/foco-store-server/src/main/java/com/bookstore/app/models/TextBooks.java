package com.bookstore.app.models;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.bookstore.app.models.interfaces.TextBooksDepartments;


@Entity
@Table(name="TEXTBOOKS")
public class TextBooks{
	
	private UserInfo user;
	private UUID textBookId;
	private String name,description,isbn;
	private Double unitPrice;
	
	private TextBooksDepartments department;

	/**
	 *  with all for update (all)
	 */
	public TextBooks(UserInfo user,UUID id, TextBooksDepartments department, String name, String description, String isbn, Double unitPrice) {		
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
	public TextBooks(TextBooksDepartments department, String name, String description, String isbn, Double unitPrice) {		
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
	public TextBooks(UUID id, TextBooksDepartments department, String name, String description, String isbn, Double unitPrice) {		
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
	 *  column definition is to convert UUID to binary
	 */
	@Id
	@Column(name = "textBookId",updatable = false, nullable = false,columnDefinition = "BINARY(16)" )    
	@GeneratedValue(generator = "uuid2",strategy = GenerationType.AUTO)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	public UUID getTextBookId() {
		return textBookId;
	}

	public void setTextBookId(UUID textBookId) {
		this.textBookId = textBookId;
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
	
	@Column(name = "deacription")
	@Size(max = 50)
	@NotNull
	public String getDescription() {
		return description;
	}
			
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 *  unique is to create unique record in database, no repetition
	 */
	@Column(name = "isbn",unique=true)
	@NotNull
	public String getIsbn() {
		return isbn;
	}
		
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="department")
	@NotNull
	public TextBooksDepartments getDepartment() {
		return department;
	}

	public void setDepartment(TextBooksDepartments department) {
		this.department = department;
	}

	@Column(name = "UnitePrice")
	@NotNull
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
