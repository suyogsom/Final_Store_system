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

import com.bookstore.app.controllers.TextBooksController;
import com.bookstore.app.models.Faculty;
import com.bookstore.app.models.Student;
import com.bookstore.app.models.TextBooks;
import com.bookstore.app.models.UserAddress;
import com.bookstore.app.models.UserName;
import com.bookstore.app.models.interfaces.TextBooksDepartments;
import com.bookstore.app.models.interfaces.UserGender;
import com.bookstore.app.services.TextBooksService;


@RunWith(MockitoJUnitRunner.class)
public class TextBooksControllerTest {
	
	@InjectMocks
    TextBooksController textBooksController;
     
    @Mock
    TextBooksService textBooksService;
 
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
	  }
     
    @Test
    @DisplayName("Getting all textbooks (no user)")
    public void getAllTextBooksTest(){
    	UUID uuidBook1 = uuidGenerator();
    	UUID uuidBook2 = uuidGenerator();
  	
        List<TextBooks> list = new ArrayList<TextBooks>();
        TextBooks book1 = new TextBooks(uuidBook1,TextBooksDepartments.CS,"java","thid is java","2001",45.3);
        TextBooks book2 = new TextBooks(uuidBook2,TextBooksDepartments.CIS,"java","thid is java","2001",45.3);
         
        list.add(book1);
        list.add(book2);
         
        when(textBooksService.getAllTextbooks()).thenReturn(list);
         
        List<TextBooks> textBooksList = (List<TextBooks>) textBooksController.getAllTextbooks();
         
        assertEquals(2, textBooksList.size());
        assertEquals("java", textBooksList.get(1).getName());
      }
    
    @Test
    @DisplayName("Getting a textbook with ID (no user)")
    public void getTextBookById() {
    	UUID uuidBook = uuidGenerator();
    	
        TextBooks book = new TextBooks(uuidBook,TextBooksDepartments.CS,"java","thid is java","2001",45.3);

        ResponseEntity<TextBooks> responseBook = new ResponseEntity<>(book, HttpStatus.OK);
        
        when(textBooksService.getTextbook(uuidBook)).thenReturn(responseBook);
        
        ResponseEntity<TextBooks> bookResult = textBooksController.getTextbook(uuidBook);
        
        assertEquals("java", bookResult.getBody().getName());
	  }
    
    @Test
    @DisplayName("Adding a textbook (no user)")
    public void addTextBook() {
    	UUID uuidBook = uuidGenerator();
    	
        TextBooks book = new TextBooks(uuidBook,TextBooksDepartments.CS,"java","thid is java","2001",45.3);

        ResponseEntity<TextBooks> responseBook = new ResponseEntity<>(book, HttpStatus.OK);
        
        when(textBooksService.addTextbook(book)).thenReturn(responseBook);
        
        ResponseEntity<TextBooks> bookResult = textBooksController.addTextbook(book);
        
        assertEquals("java", bookResult.getBody().getName());
      }
    
    @Test
    @DisplayName("Update a textbook (no user)")
    public void updateTextBook() {
    	UUID uuidBook = uuidGenerator();
    	
        TextBooks book = new TextBooks(uuidBook,TextBooksDepartments.CS,"java","thid is java","2001",45.3);

        TextBooks bookUpdate = new TextBooks(uuidBook,TextBooksDepartments.CS,"java-update","thid is java","2001",45.3);

        ResponseEntity<TextBooks> responseBook = new ResponseEntity<>(bookUpdate, HttpStatus.OK);
        
        when(textBooksService.updateTextbook(book, uuidBook)).thenReturn(responseBook);
        
        ResponseEntity<TextBooks> bookResult = textBooksController.updateTextbook(book, uuidBook);
        
        assertEquals("java-update", bookResult.getBody().getName());
      }
    
    @Test
    @DisplayName("Delete a textbook (no user)")
    public void deleteTextBook() {
    	UUID uuidBook = uuidGenerator();    	
        
        ResponseEntity<String> responseBook = new ResponseEntity<>("TextBook deleted", HttpStatus.OK);
        
        when(textBooksService.deleteTextbook(uuidBook)).thenReturn(responseBook);
        
        ResponseEntity<String> bookResult = textBooksController.deleteTextbook(uuidBook);
        
        assertEquals("TextBook deleted", bookResult.getBody());
    }
    
    @Test
    @DisplayName("Update textbooks by adding faculty and student")
    public void updateTextBooksWithFacultyAndStudent(){
    	UUID uuidFaculty = uuidGenerator();
    	UUID uuidStudent = uuidGenerator();
    	
    	UserName nameFaculty = nameProvider();
    	UserName nameStudent = nameProvider();
    	
    	UserAddress addressFaculty = addressProvider();
    	UserAddress addressStudent = addressProvider();
    	
    	UUID uuidBook1 = uuidGenerator();
    	UUID uuidBook2 = uuidGenerator();
    	
    	Faculty faculty = new Faculty(uuidFaculty,"ECE",nameFaculty,addressFaculty,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"undergrad");
        Student student = new Student(uuidStudent,"ECE",nameStudent,addressStudent,"9705819659","anup.patil@gmail.com",UserGender.MALE,LocalDate.of(2019, 6, 11),LocalDate.of(2019, 9, 13),"resident","undergrds");
  	
        List<TextBooks> list = new ArrayList<TextBooks>();
        TextBooks book1 = new TextBooks(faculty,uuidBook1,TextBooksDepartments.CS,"java","thid is java","2001",45.3);
        TextBooks book2 = new TextBooks(student,uuidBook2,TextBooksDepartments.CIS,"java","thid is java","2001",45.3);               
         
        list.add(book1);
        list.add(book2);
        
        TextBooks book1Update = new TextBooks(faculty,uuidBook1,TextBooksDepartments.CS,"java","thid is java","2001",45.3);
        TextBooks book2Update = new TextBooks(student,uuidBook1,TextBooksDepartments.CS,"java","thid is java","2001",45.3);
        
        ResponseEntity<TextBooks> responseBook1 = new ResponseEntity<>(book1Update, HttpStatus.OK);
        ResponseEntity<TextBooks> responseBook2 = new ResponseEntity<>(book2Update, HttpStatus.OK);
        
        when(textBooksService.updateTextbook(book1, uuidBook1)).thenReturn(responseBook1);
        when(textBooksService.updateTextbook(book2, uuidBook2)).thenReturn(responseBook2);
        
        ResponseEntity<TextBooks> bookResult1 = textBooksController.updateTextbook(book1, uuidBook1);
        ResponseEntity<TextBooks> bookResult2 = textBooksController.updateTextbook(book2, uuidBook2);
        
        assertEquals("USA", bookResult1.getBody().getFaculty().getAddress().getCountry());
        assertEquals("anup", bookResult2.getBody().getStudent().getName().getFirstName());
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