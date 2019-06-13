package com.bookstore.app.models;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.bookstore.app.models.interfaces.UserGender;

@Entity
@Table(name="Student")
@DiscriminatorValue("S")
@PrimaryKeyJoinColumn(name="user_ID_FK")
public class Student extends User{

	/**
	 * 
	 */
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

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public LocalDate getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(LocalDate graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}	
}
