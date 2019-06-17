package com.bookstore.app.models;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bookstore.app.models.interfaces.UserGender;

@Entity
@Table(name="Student")
@DiscriminatorValue("S")
@PrimaryKeyJoinColumn(name="user_ID_FK")
public class Student extends User{

	private static final long serialVersionUID = 1L;
	private LocalDate joiningDate, graduationDate;
	private String status;
	
	private String program;

	public Student() {
		
	}

	public Student(UUID id, String department, UserName name, UserAddress address, String phoneNumber, String email,
			UserGender gender, LocalDate joiningDate, LocalDate graduationDate, String status, String program) {
		super(id, department, name, address, phoneNumber, email, gender);
		this.joiningDate = joiningDate;
		this.graduationDate = graduationDate;
		this.status = status;
		this.program = program;
	}

	@NotBlank(message = "joiningDate must not be empty")
	@Size(max = 50)	
	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	@NotBlank(message = "graduationDate must not be empty")
	@Size(max = 50)	
	public LocalDate getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(LocalDate graduationDate) {
		this.graduationDate = graduationDate;
	}

	@NotBlank(message = "status must not be empty")
	@Size(max = 50)	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@NotBlank(message = "program must not be empty")
	@Size(max = 50)	
	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}	
}
