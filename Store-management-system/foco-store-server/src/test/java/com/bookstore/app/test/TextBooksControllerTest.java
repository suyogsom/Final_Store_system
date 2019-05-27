package com.bookstore.app.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.bookstore.app.controllers.TextBooksController;
import com.bookstore.app.models.TextBooks;
import com.bookstore.app.services.TextBooksServiceCRUDRepo;


@RunWith(MockitoJUnitRunner.class)
public class TextBooksControllerTest {
	
	@InjectMocks
    TextBooksController textBooksController;
     
    @Mock
    TextBooksServiceCRUDRepo textBooksService;
 
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
	  }
     
    @Test
    @DisplayName("Getting all textbooks")
    public void getAllTextBooksTest(){
        List<TextBooks> list = new ArrayList<TextBooks>();
        TextBooks book1 = new TextBooks(101,"CIS","java","thid is java","2001",45.3);
        TextBooks book2 = new TextBooks(102,"CIVIL","java EE","thid is java EE" ,"2002",4523.3);
        TextBooks book3 = new TextBooks(103,"MCIS","java SE","thid is java SE","2003",452334.3);
         
        list.add(book1);
        list.add(book2);
        list.add(book3);
         
        when(textBooksService.getAllTextbooks()).thenReturn(list);
         
        List<TextBooks> textBooksList = (List<TextBooks>) textBooksController.getAllTextbooks();
         
        assertEquals(3, textBooksList.size());

        for(int i=0;i<textBooksList.size();i++) {
        	System.out.print("\nid:" + textBooksList.get(i).getId());
        	System.out.print("\t\tname:" + textBooksList.get(i).getName());
        	System.out.print("\t\tdepartment:" + textBooksList.get(i).getDepartment());
        	System.out.print("\t\tdescription:" + textBooksList.get(i).getDescription());
          }
      }
    
    @Test
    @DisplayName("Getting a textbook with ID")
    @Disabled
    public void getTextBookById() {
        List<TextBooks> list = new ArrayList<TextBooks>();
       TextBooks book1 = new TextBooks(101,"CIS","java","thid is java","2001",45.3);
        
        list.add(book1);
        
    	when(textBooksController.getTextbook(101)).thenReturn(book1);
    	
    	TextBooks book = textBooksService.getTextbook(101);
    	
    	assertEquals("101", book.getId());
	  }
    
    @Test
    @DisplayName("Adding a textbook")
    public void addTextBook() {
       TextBooks book1 = new TextBooks(101,"CIS","java","thid is java","2001",45.3);
        
        textBooksController.addTextbook(book1);

        verify(textBooksService,times(1)).addTextbook(book1);
      }
    
//    @Test
//    @DisplayName("Update a textbook")
//    public void updateTextBook() {
//       TextBooks book1 = new TextBooks(101,"CIS","java","thid is java","2001",45.3);
//        
//        textBooksController.addTextbook(book1);
//
//        verify(textBooksService,times(1)).addTextbook(book1);
//      }
    
//    @Test
//    @DisplayName("Delete a textbook")
//    public void deleteTextBook() {
//    	List<TextBooks> list = new ArrayList<TextBooks>();
//        TextBooks book1 = new TextBooks(101,"CIS","java","thid is java","2001",45.3);
//        TextBooks book2 = new TextBooks(102,"CIVIL","java EE","thid is java EE" ,"2002",4523.3);
//        TextBooks book3 = new TextBooks(103,"MCIS","java SE","thid is java SE","2003",452334.3);
//         
//        list.add(book1);
//        list.add(book2);
//        list.add(book3);
//         
//        when(textBooksService.getAllTextbooks()).thenReturn(list);
//         
//        List<TextBooks> textBooksList = (List<TextBooks>) textBooksController.getAllTextbooks();
//         
//        assertEquals(3, textBooksList.size());
//        
//        
//
//     }
    
    
	     
	/*
	 * @Test public void getEmployeeByIdTest() {
	 * when(dao.getEmployeeById(1)).thenReturn(new
	 * EmployeeVO(1,"Lokesh","Gupta","user@email.com"));
	 * 
	 * EmployeeVO emp = manager.getEmployeeById(1);
	 * 
	 * assertEquals("Lokesh", emp.getFirstName()); assertEquals("Gupta",
	 * emp.getLastName()); assertEquals("user@email.com", emp.getEmail()); }
	 * 
	 * @Test public void createEmployeeTest() { EmployeeVO emp = new
	 * EmployeeVO(1,"Lokesh","Gupta","user@email.com");
	 * 
	 * manager.addEmployee(emp);
	 * 
	 * verify(dao, times(1)).addEmployee(emp); }
	 */
}