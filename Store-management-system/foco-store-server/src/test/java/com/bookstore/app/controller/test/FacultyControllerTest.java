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

import com.bookstore.app.controllers.FacultyController;
import com.bookstore.app.models.Faculty;
import com.bookstore.app.models.UserAddress;
import com.bookstore.app.models.UserName;
import com.bookstore.app.models.interfaces.UserGender;
import com.bookstore.app.services.FacultyService;


@RunWith(MockitoJUnitRunner.class)
public class FacultyControllerTest {
	
	@InjectMocks
    FacultyController facultyController;
     
    @Mock
    FacultyService facultyService;
 
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
	  }
 
    @Test
    @DisplayName("Getting all faculty user")
    public void getAllFaculty(){
    	UUID uuid1 = uuidGenerator();
    	UUID uuid2 = uuidGenerator();
    	
    	UserName name1 = nameProvider();
    	UserName name2 = nameProvider();
    	
    	UserAddress address1 = addressProvider();
    	UserAddress address2 = addressProvider();
    	
        List<Faculty> list = new ArrayList<Faculty>();
    	Faculty faculty1 = new Faculty(uuid1,"ECE",name1,address1,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"undergrad");
    	Faculty faculty2 = new Faculty(uuid2,"CIS",name2,address2,"9705819699","anup.patil1@gmail.com",UserGender.FEMALE,LocalDate.of(2019, 4, 11),LocalDate.of(2019, 9, 13),"undergrad");
         
        list.add(faculty1);
        list.add(faculty2);
         
        when(facultyService.getAllUsers()).thenReturn(list);
         
        List<Faculty> facultyList = (List<Faculty>) facultyController.getAllUsers();
         
        assertEquals(2, facultyList.size());
        assertEquals("CIS", facultyList.get(1).getDepartment());
      }
    
    @Test
    @DisplayName("Getting a faculty with ID")
    public void getFacultyById() {
    	UUID uuid = uuidGenerator();
    	
    	UserName name = nameProvider();
    	
    	UserAddress address = addressProvider();

    	Faculty faculty = new Faculty(uuid,"ECE",name,address,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"undergrad");

    	ResponseEntity<Faculty> responseFaculty = new ResponseEntity<>(faculty, HttpStatus.OK);
    	
        when(facultyService.getUser(uuid)).thenReturn(responseFaculty);
        
        ResponseEntity<Faculty> facultyResult = facultyController.getUser(uuid);

        assertEquals("ECE", facultyResult.getBody().getDepartment());
	  }
    
    @Test
    @DisplayName("Adding a faculty")
    public void addFaculty() {
    	UUID uuid = uuidGenerator();
    	
    	UserName name = nameProvider();
    	
    	UserAddress address = addressProvider();
    	
    	Faculty faculty = new Faculty(uuid,"ECE",name,address,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"undergrad");

    	ResponseEntity<Faculty> responseFaculty = new ResponseEntity<>(faculty, HttpStatus.OK);
    	
        when(facultyService.addUser(faculty)).thenReturn(responseFaculty);
        
        ResponseEntity<Faculty> facultyResult = facultyController.addUser(faculty);

        assertEquals("ECE", facultyResult.getBody().getDepartment());
      }
   
	@Test
    @DisplayName("Update a faculty")
    public void updateFaculty() {
    	UUID uuid = uuidGenerator();
    	
    	UserName name = nameProvider();
    	
    	UserAddress address = addressProvider();

    	Faculty faculty = new Faculty(uuid,"ECE",name,address,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"undergrad");
    	
    	Faculty facultyUpdate = new Faculty(uuid,"ECE-update",name,address,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"undergrad");
    	
    	ResponseEntity<Faculty> responseFaculty = new ResponseEntity<>(facultyUpdate, HttpStatus.OK);

        when(facultyService.updateUser(faculty, uuid)).thenReturn(responseFaculty);
        
        ResponseEntity<Faculty> facultyResult = facultyController.updateUser(faculty, uuid);

        assertEquals("ECE-update", facultyResult.getBody().getDepartment());
      }
    
    @Test
    @DisplayName("Delete a faculty")
    public void deleteFaculty() {
    	UUID uuid1 = uuidGenerator();    	
       
        ResponseEntity<String> responseFaculty = new ResponseEntity<>("Faculty deleted", HttpStatus.OK);
        
        when(facultyService.deleteUser(uuid1)).thenReturn(responseFaculty);
        
        ResponseEntity<String> facultyResult = facultyController.deleteUser(uuid1);
        
        assertEquals("Faculty deleted", facultyResult.getBody());
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