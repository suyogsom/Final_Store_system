package com.bookstore.app.controller.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookstore.app.controllers.StudentController;
import com.bookstore.app.models.Student;
import com.bookstore.app.models.UserAddress;
import com.bookstore.app.models.UserName;
import com.bookstore.app.models.interfaces.UserGender;
import com.bookstore.app.services.StudentService;


@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {
	
	@InjectMocks
    StudentController studentController;
     
    @Mock
    StudentService studentService;
 
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
	  }
 
    @Test
    @DisplayName("Getting all student user")
    public void getAllStudent(){
    	UUID uuid1 = uuidGenerator();
    	UUID uuid2 = uuidGenerator();
    	
    	UserName name1 = nameProvider();
    	UserName name2 = nameProvider();
    	
    	UserAddress address1 = addressProvider();
    	UserAddress address2 = addressProvider(); 	

        List<Student> list = new ArrayList<Student>();
        Student student1 = new Student(uuid1,"ECE",name1,address1,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"resident","undergrds");
        Student student2 = new Student(uuid2,"CIS",name2,address2,"9705819699","anup.patil1@gmail.com",UserGender.FEMALE,LocalDate.of(2019, 4, 11),LocalDate.of(2019, 9, 13),"non-resident","undergrad");
         
        list.add(student1);
        list.add(student2);
         
        when(studentService.getAllUsers()).thenReturn(list);
         
        List<Student> facultyList = (List<Student>) studentController.getAllUsers();
         
        assertEquals(2, facultyList.size());
        assertEquals("CIS", facultyList.get(1).getDepartment());
      }
    
    @Test
    @DisplayName("Getting a student with ID")
    public void getStudentById() {
    	UUID uuid = uuidGenerator();
    	
    	UserName name = nameProvider();
    	
    	UserAddress address = addressProvider();

        Student student = new Student(uuid,"ECE",name,address,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"resident","undergrds");

    	ResponseEntity<Student> responseFaculty = new ResponseEntity<>(student, HttpStatus.OK);
    	
        when(studentService.getUser(uuid)).thenReturn(responseFaculty);
        
        ResponseEntity<Student> facultyResult = studentController.getUser(uuid);

        assertEquals("ECE", facultyResult.getBody().getDepartment());
	  }
    
    @Test
    @DisplayName("Adding a student")
    public void addStudent() {
    	UUID uuid = uuidGenerator();
    	
    	UserName name = nameProvider();
    	
    	UserAddress address = addressProvider();

        Student student = new Student(uuid,"ECE",name,address,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"resident","undergrds");

    	ResponseEntity<Student> responseFaculty = new ResponseEntity<>(student, HttpStatus.OK);
    	
        when(studentService.addUser(student)).thenReturn(responseFaculty);
        
        ResponseEntity<Student> facultyResult = studentController.addUser(student);

        assertEquals("ECE", facultyResult.getBody().getDepartment());
      }
   
	@Test
    @DisplayName("Update a student")
    public void updateStudent() {
    	UUID uuid = uuidGenerator();
    	
    	UserName name = nameProvider();
    	
    	UserAddress address = addressProvider();

        Student student = new Student(uuid,"ECE",name,address,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"resident","undergrds");
    	
        Student studentUpdate = new Student(uuid,"ECE-update",name,address,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"resident","undergrds");
    	
    	ResponseEntity<Student> responseFaculty = new ResponseEntity<>(studentUpdate, HttpStatus.OK);

        when(studentService.updateUser(student, uuid)).thenReturn(responseFaculty);
        
        ResponseEntity<Student> facultyResult = studentController.updateUser(student, uuid);

        assertEquals("ECE-update", facultyResult.getBody().getDepartment());
      }
    
    @Test
    @DisplayName("Delete a student")
    public void deleteStudent() {
    	UUID uuid1 = uuidGenerator();    	
       
        ResponseEntity<String> responseFaculty = new ResponseEntity<>("Student deleted", HttpStatus.OK);
        
        when(studentService.deleteUser(uuid1)).thenReturn(responseFaculty);
        
        ResponseEntity<String> facultyResult = studentController.deleteUser(uuid1);
        
        assertEquals("Student deleted", facultyResult.getBody());
    }   
    
    public UUID uuidGenerator() {
    	UUID uuid = UUID.randomUUID();
    	return uuid;
    }
    
    public UserName nameProvider() {
    	return new UserName("anup","dinesh","patil"); 
    }
    
    public UserAddress addressProvider() {
    	return new UserAddress("d-203","west plum","fort collins","CO","80521","USA");
    }
}