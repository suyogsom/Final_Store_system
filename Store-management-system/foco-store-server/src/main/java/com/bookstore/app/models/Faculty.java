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
@Table(name="Faculty")
@DiscriminatorValue("F")
@PrimaryKeyJoinColumn(name="user_ID_FK")
public class Faculty extends User{
	
	private static final long serialVersionUID = 1L;
	private LocalDate joiningDate, tenuarDate;
	private String program;
	
	public Faculty() {
		
	}
	
	public Faculty(UUID id, String department, UserName name, UserAddress address, String phoneNumber, String email,
			UserGender gender, LocalDate joiningDate, LocalDate tenuarDate, String program) {
		super(id, department, name, address, phoneNumber, email, gender);
		this.joiningDate = joiningDate;
		this.tenuarDate = tenuarDate;
		this.program = program;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public LocalDate getTenuarDate() {
		return tenuarDate;
	}

	public void setTenuarDate(LocalDate tenuarDate) {
		this.tenuarDate = tenuarDate;
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
