package com.bookstore.app.models;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bookstore.app.audit.Auditable;
import com.bookstore.app.models.interfaces.TextBooksDepartments;


@Entity
@Table(name="TEXTBOOKS")
@EntityListeners(AuditingEntityListener.class)
public class TextBooks extends Auditable<String> {
	
	private Student student;
	private Faculty faculty;
	private UUID textBookId;
	private String name,description,isbn;
	private Double unitPrice;
	
	private TextBooksDepartments department;
	/**
	 *  with all for update (all) Student
	 */
	public TextBooks(Student user, UUID id, TextBooksDepartments department, String name, String description, String isbn, Double unitPrice) {		
		super(); 
		this.textBookId = id;  
		this.department=department;  
		this.name = name;
		this.description = description; 
		this.isbn =isbn;	
		this.unitPrice = unitPrice;
		this.student = user;
	}	
	/**
	 *  with all for update (all) Faculty
	 */
	public TextBooks(Faculty user, UUID id, TextBooksDepartments department, String name, String description, String isbn, Double unitPrice) {		
		super(); 
		this.textBookId = id;  
		this.department=department;  
		this.name = name;
		this.description = description; 
		this.isbn =isbn;	
		this.unitPrice = unitPrice;
		this.faculty = user;
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
	 *  @Type(type="uuid-char") convert UUID to char string 
	 */
	@Id
	@Column(name = "textBookId",updatable = false, nullable = false) 
	@Type(type="uuid-char")
	@GeneratedValue(generator = "uuid2",strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	public UUID getTextBookId() {
		return textBookId;
	}

	public void setTextBookId(UUID textBookId) {
		this.textBookId = textBookId;
	}
	
	@Column(name = "name")
	@NotBlank(message = "name must not be empty")
	@Size(max = 50)
	public String getName() {
		return name;
	}
			
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "deacription")
	@NotBlank(message = "description must not be empty")
	@Size(max = 50)
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
	@NotBlank(message = "isbn must not be empty")
	@Size(max = 50)
	public String getIsbn() {
		return isbn;
	}
		
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="department", nullable = false)
	public TextBooksDepartments getDepartment() {
		return department;
	}

	public void setDepartment(TextBooksDepartments department) {
		this.department = department;
	}

	@Column(name = "UnitePrice", nullable = false)
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,targetEntity=Faculty.class)
	@JoinColumn(name="faculty_Id_FK")
	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,targetEntity=Student.class)
	@JoinColumn(name="student_Id_FK")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity=User.class)
//	@JoinColumn(name="userIdFK")
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}	
}
