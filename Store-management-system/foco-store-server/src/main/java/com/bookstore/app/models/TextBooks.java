package com.bookstore.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
//@Table(name="TextBooks")
public class TextBooks{
	private Integer textBookId;
	private String department,name,description,isbn;
	private Double unitPrice;
	

	public TextBooks(Integer id, String department, String name, String description, String isbn, Double unitPrice) {		
		super(); 
		this.textBookId = id;  
		this.department=department;  
		this.name = name;
		this.description = description; 
		this.isbn =isbn;	
		this.unitPrice = unitPrice;
	}
	
	// constructor without id hibernate will give generate id for it 
	public TextBooks(String department, String name, String description, String isbn, Double unitPrice) {		
		super(); 
		this.department=department;  
		this.name = name;
		this.description = description; 
		this.isbn =isbn;	
		this.unitPrice = unitPrice;
	}
	
	
	public TextBooks() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)  // to generate id automatically
	public Integer getId() {
		return textBookId;
	}		
	
	public void setId(Integer id) {
		this.textBookId = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
			
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "deacription")
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

		
}
