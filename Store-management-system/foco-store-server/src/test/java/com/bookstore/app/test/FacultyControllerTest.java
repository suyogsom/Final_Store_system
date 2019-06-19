package com.bookstore.app.test;

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
    @DisplayName("Getting all users - faculty")
    public void getAllTextBooksTest(){
    	UUID uuid1 = uuidGenerator();
    	UUID uuid2 = uuidGenerator();
    	
    	UserName name1 = new UserName("anup","dinesh","patil");
    	UserName name2 = new UserName("pankaj","rajesh","jain");
    	
    	UserAddress address1 = new UserAddress("d-203","west plum","fort collins","CO","80521","USA");
    	UserAddress address2 = new UserAddress("d-203","west plum","fort collins","CO","80521","USA");   	

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
    
    public UUID uuidGenerator() {
    	UUID uuid = UUID.randomUUID();
    	return uuid;
    }
    
    @Test
    @DisplayName("Getting a textbook with ID")
    public void getFacultyById() {
    	UUID uuid = uuidGenerator();
    	
    	UserName name = new UserName("anup","dinesh","patil");
    	
    	UserAddress address = new UserAddress("d-203","west plum","fort collins","CO","80521","USA");

    	Faculty faculty = new Faculty(uuid,"ECE",name,address,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"undergrad");

    	ResponseEntity<Faculty> responseFaculty = new ResponseEntity<>(faculty, HttpStatus.OK);
    	
        when(facultyService.getUser(uuid)).thenReturn(responseFaculty);
        
        ResponseEntity<Faculty> facultyResult = facultyController.getUser(uuid);

        assertEquals("ECE", facultyResult.getBody().getDepartment());
	  }
    
    @Test
    @DisplayName("Adding a user - faculty")
    public void addFaculty() {
    	UUID uuid = uuidGenerator();
    	
    	UserName name = new UserName("anup","dinesh","patil");
    	
    	UserAddress address = new UserAddress("d-203","west plum","fort collins","CO","80521","USA");

    	Faculty faculty = new Faculty(uuid,"ECE",name,address,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"undergrad");

    	ResponseEntity<Faculty> responseFaculty = new ResponseEntity<>(faculty, HttpStatus.OK);
    	
        when(facultyService.addUser(faculty)).thenReturn(responseFaculty);
        
        ResponseEntity<Faculty> facultyResult = facultyController.addUser(faculty);

        assertEquals("ECE", facultyResult.getBody().getDepartment());
      }
   
//    @Test
//    @DisplayName("Update a faculty")
//    public void updateFaculty() {
//    	UUID uuid = uuidGenerator();
//    	
//    	UserName name = new UserName("anup","dinesh","patil");
//    	
//    	UserAddress address = new UserAddress("d-203","west plum","fort collins","CO","80521","USA");
//
//    	Faculty faculty = new Faculty(uuid,"ECE",name,address,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"undergrad");
//
//    	ResponseEntity<Faculty> responseFaculty = new ResponseEntity<>(faculty, HttpStatus.OK);
//    	
//        when(facultyService.addUser(faculty)).thenReturn(responseFaculty);
//        
//        ResponseEntity<Faculty> facultyResult = facultyController.addUser(faculty);
//
//        assertEquals("ECE", facultyResult.getBody().getDepartment());
//      }
//    
//    @Test
//    @DisplayName("Delete a faculty")
//    public void deleteFaculty() {
//    	UUID uuid1 = uuidGenerator();
//    	UUID uuid2 = uuidGenerator();
//    	
//    	UserName name1 = new UserName("anup","dinesh","patil");
//    	UserName name2 = new UserName("pankaj","rajesh","jain");
//    	
//    	UserAddress address1 = new UserAddress("d-203","west plum","fort collins","CO","80521","USA");
//    	UserAddress address2 = new UserAddress("d-203","west plum","fort collins","CO","80521","USA");   	
//
//        List<Faculty> list = new ArrayList<Faculty>();
//    	Faculty faculty1 = new Faculty(uuid1,"ECE",name1,address1,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"undergrad");
//    	Faculty faculty2 = new Faculty(uuid2,"CIS",name2,address2,"9705819699","anup.patil1@gmail.com",UserGender.FEMALE,LocalDate.of(2019, 4, 11),LocalDate.of(2019, 9, 13),"undergrad");
//         
//        list.add(faculty1);
//        list.add(faculty2);
//         
//        when(facultyService.getAllUsers()).thenReturn(list);
//         
//        List<Faculty> facultyList = (List<Faculty>) facultyController.getAllUsers();
//         
//        assertEquals(2, facultyList.size());
//        assertEquals("CIS", facultyList.get(1).getDepartment());
//       
//        ResponseEntity<String> responseFaculty = new ResponseEntity<>("Faculty deleted", HttpStatus.OK);
//        
//        when(facultyService.deleteUser(uuid1)).thenReturn(responseFaculty);
//        
//        ResponseEntity<String> facultyResult = facultyController.deleteTextbook(uuid2);
//        
//        assertEquals(1, facultyResult.getBody());
//    }
}