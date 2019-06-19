package com.bookstore.it;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TextBooksIT extends BaseTest {
	
	Response response;
	JsonPath js;
	String ID,isbn;

	@Test
	@DisplayName("Getting all textbooks")
	public void getAllBooks() {	
		response = gettingResponse();
		System.out.print("\nAll text books : " + response.asString() + "\n");
	}
	
	@Test
	@DisplayName("Getting a textbook with ID")
	public void getTextBookByID() {
		String isbn = randomIsbnGenerator();
		
		if(!isbnCheck(isbn)) {
			String body = "{ \"department\": \"ECE\", \"name\": \"Java\", \"description\": \"this is java book\", \"isbn\":" + isbn +", \"unitPrice\": 223.4 }";
			
			response = given().spec(restClient.getRecSpec())
					   .basePath("/books").body(body)
					   .when().post("/textbooks/add")
					   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(response.asString());
			ID = js.get("textBookId");		
			System.out.print("\nText books with id  : "+ ID + "\n\n");
			System.out.print("\nText books details  : "+ response.asString() + "\n\n");
		}
		else {
			System.out.print("\n\nISBN duplication error\n\n");
		}		
	}
	
	@Test
	@DisplayName("Add textbook without user")
	public void addTextBookNoUser() {				
		String isbn = randomIsbnGenerator();
		
		if(!isbnCheck(isbn)) {
			String body = "{ \"department\": \"ECE\", \"name\": \"Java\", \"description\": \"this is java book\", \"isbn\":" + isbn +", \"unitPrice\": 223.4 }";
			
			response = given().spec(restClient.getRecSpec())
					   .basePath("/books").body(body)
					   .when().post("/textbooks/add")
					   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(response.asString());
			ID = js.get("textBookId");		
			System.out.print("\nText books with id  : "+ ID + "\n\n");
			System.out.print("\nText books details  : "+ response.asString() + "\n\n");
		}
		else {
			System.out.print("\n\nISBN duplication error\n\n");
		}			
	}
	
	@Test
	@DisplayName("Update textbook without user")
	public void updateTextBookNoUser() {				
		String isbn = randomIsbnGenerator();
		
		if(!isbnCheck(isbn)) {
			String body = "{ \"department\": \"ECE\", \"name\": \"Java\", \"description\": \"this is java book\", \"isbn\":" + isbn +", \"unitPrice\": 223.4 }";
			
			response = given().spec(restClient.getRecSpec())
					   .basePath("/books").body(body)
					   .when().post("/textbooks/add")
					   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(response.asString());
			ID = js.get("textBookId");		
			System.out.print("\nText book with id  : "+ ID + "\n\n");
			System.out.print("\nText book details  : "+ response.asString() + "\n\n");
			
			isbn = js.get("isbn");
			System.out.print("\nText book ISBN  : "+ isbn + "\n\n");																															
			
			String bodyNew = "{\"textBookId\":\""+ ID +"\", \"name\": \"Java-update\", \"description\": \"this is java book\", \"isbn\": \""+ isbn +"\", \"unitPrice\": 223.4, \"department\": \"CIS\" }";
			System.out.print("\nText book new body  : "+ bodyNew + "\n\n");
		
			response = given().spec(restClient.getRecSpec())
				   	   .basePath("/books").body(bodyNew).contentType(ContentType.JSON)
				   	   .when().post("/textbooks/update/" + ID)
				   	   .then().assertThat().statusCode(200).extract().response();
			
			System.out.print("\nText books after update  : "+ response.asString() + "\n\n");
		}
		else {
			System.out.print("\n\nISBN duplication error\n\n");
		}			
	}
	
	@Test
	@DisplayName("Delete textbook without user")
	public void deleteTextBookNoUser() {				
		String isbn = randomIsbnGenerator();
		
		if(!isbnCheck(isbn)) {
			String body = "{ \"department\": \"ECE\", \"name\": \"Java\", \"description\": \"this is java book\", \"isbn\":" + isbn +", \"unitPrice\": 223.4 }";
			
			response = given().spec(restClient.getRecSpec())
					   .basePath("/books").body(body)
					   .when().post("/textbooks/add")
					   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(response.asString());
			ID = js.get("textBookId");		
			System.out.print("\nText book with id  : "+ ID + "\n\n");
			System.out.print("\nText book details  : "+ response.asString() + "\n\n");
		
			response = given().spec(restClient.getRecSpec())
				   	   .basePath("/books")
				   	   .when().post("/textbooks/delete/" + ID)
				   	   .then().assertThat().statusCode(200).extract().response();
		}
		else {
			System.out.print("\n\nISBN duplication error\n\n");
		}			
	}
	
	@Test
	@DisplayName("Update textbook by adding faculty")
	public void updateTextBookAddFaculty() {				
		String isbn = randomIsbnGenerator();
		String numForMail = randomIsbnGenerator();
		
		if((!isbnCheck(isbn)) && (!emailCheckFaculty(numForMail))) {
			String body = "{ \"department\": \"ECE\", \"name\": \"Java\", \"description\": \"this is java book\", \"isbn\":" + isbn +", \"unitPrice\": 223.4 }";
			
			response = given().spec(restClient.getRecSpec())
					   .basePath("/books").body(body)
					   .when().post("/textbooks/add")
					   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(response.asString());
			ID = js.get("textBookId");		
			System.out.print("\nText book with id  : "+ ID + "\n\n");
			System.out.print("\nText book details  : "+ response.asString() + "\n\n");
			
			isbn = js.get("isbn");
			System.out.print("\nText book ISBN  : "+ isbn + "\n\n");																															
			
			String userFaculty = "{\"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"asd\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"tenuarDate\": \"2019-06-11\", \"program\": \"undergrad\"}";
			System.out.print("\nFaculty  : "+ userFaculty + "\n\n");
			
			Response responseFaculty = given().spec(restClient.getRecSpec())
									   .basePath("/faculty").body(userFaculty)
									   .when().post("/add")
									   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(responseFaculty.asString());
			String userId = js.get("userId");	
			String bodyNew = "{\"faculty\":{ \"userId\": \""+ userId +"\", \"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"co\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"tenuarDate\": \"2019-06-11\", \"program\": \"undergrad\" }, \"textBookId\":\""+ ID +"\", \"name\": \"Java-update\", \"description\": \"this is java book\", \"isbn\": \""+ isbn +"\", \"unitPrice\": 223.4, \"department\": \"CIS\" }";
			System.out.print("\nText book new body  : "+ bodyNew + "\n\n");
		
			response = given().spec(restClient.getRecSpec())
				   	   .basePath("/books").body(bodyNew).contentType(ContentType.JSON)
				   	   .when().post("/textbooks/update/" + ID)
				   	   .then().assertThat().statusCode(200).extract().response();
			
			System.out.print("\nText books after update  : "+ response.asString() + "\n\n");

		}
		else {
			System.out.print("\n\nISBN duplication error\n\n");
		}			
	}
	
	@Test
	@DisplayName("Update textbook by removing faculty")
	public void updateTextBookRemoveFaculty() {				
		String isbn = randomIsbnGenerator();
		String numForMail = randomIsbnGenerator();
		
		if((!isbnCheck(isbn)) && (!emailCheckFaculty(numForMail))) {
			String body = "{ \"department\": \"ECE\", \"name\": \"Java\", \"description\": \"this is java book\", \"isbn\":" + isbn +", \"unitPrice\": 223.4 }";
			
			response = given().spec(restClient.getRecSpec())
					   .basePath("/books").body(body)
					   .when().post("/textbooks/add")
					   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(response.asString());
			ID = js.get("textBookId");		
			System.out.print("\nText book with id  : "+ ID + "\n\n");
			System.out.print("\nText book details  : "+ response.asString() + "\n\n");
			
			isbn = js.get("isbn");
			System.out.print("\nText book ISBN  : "+ isbn + "\n\n");																															
			
			String userFaculty = "{\"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"asd\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"tenuarDate\": \"2019-06-11\", \"program\": \"undergrad\"}";
			System.out.print("\nFaculty  : "+ userFaculty + "\n\n");
			
			Response responseFaculty = given().spec(restClient.getRecSpec())
									   .basePath("/faculty").body(userFaculty)
									   .when().post("/add")
									   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(responseFaculty.asString());
			String userId = js.get("userId");	
			String bodyNew = "{\"faculty\":{ \"userId\": \""+ userId +"\", \"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"co\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"tenuarDate\": \"2019-06-11\", \"program\": \"undergrad\" }, \"textBookId\":\""+ ID +"\", \"name\": \"Java-update\", \"description\": \"this is java book\", \"isbn\": \""+ isbn +"\", \"unitPrice\": 223.4, \"department\": \"CIS\" }";
			System.out.print("\nText book new body  : "+ bodyNew + "\n\n");
		
			response = given().spec(restClient.getRecSpec())
				   	   .basePath("/books").body(bodyNew).contentType(ContentType.JSON)
				   	   .when().post("/textbooks/update/" + ID)
				   	   .then().assertThat().statusCode(200).extract().response();
			
			String bodyFacultyNull = "{\"faculty\":" + null +", \"textBookId\":\""+ ID +"\", \"name\": \"Java-update\", \"description\": \"this is java book\", \"isbn\": \""+ isbn +"\", \"unitPrice\": 223.4, \"department\": \"CIS\" }";
			System.out.print("\nText book new body  : "+ bodyFacultyNull + "\n\n");
			
			response = given().spec(restClient.getRecSpec())
				   	   .basePath("/books").body(bodyFacultyNull).contentType(ContentType.JSON)
				   	   .when().post("/textbooks/update/" + ID)
				   	   .then().assertThat().statusCode(200).extract().response();
			
			System.out.print("\nText books after update  : "+ response.asString() + "\n\n");

		}
		else {
			System.out.print("\n\nISBN duplication error\n\n");
		}			
	}
	
	@Test
	@DisplayName("Update textbook by adding student")
	public void updateTextBookAddStudent() {				
		String isbn = randomIsbnGenerator();
		String numForMail = randomIsbnGenerator();
		
		if((!isbnCheck(isbn)) && (!emailCheckStudent(numForMail))) {
			String body = "{ \"department\": \"ECE\", \"name\": \"Java\", \"description\": \"this is java book\", \"isbn\":" + isbn +", \"unitPrice\": 223.4 }";
			
			response = given().spec(restClient.getRecSpec())
					   .basePath("/books").body(body)
					   .when().post("/textbooks/add")
					   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(response.asString());
			ID = js.get("textBookId");		
			System.out.print("\nText book with id  : "+ ID + "\n\n");
			System.out.print("\nText book details  : "+ response.asString() + "\n\n");
			
			isbn = js.get("isbn");
			System.out.print("\nText book ISBN  : "+ isbn + "\n\n");																															
			
			String userStudent = "{\"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"asd\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"graduationDate\": \"2019-06-11\", \"status\": \"Resident\",\"program\": \"undergrad\"}";
			System.out.print("\nFaculty  : "+ userStudent + "\n\n");
			
			Response responseFaculty = given().spec(restClient.getRecSpec())
									   .basePath("/student").body(userStudent)
									   .when().post("/add")
									   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(responseFaculty.asString());
			String userId = js.get("userId");	
			String bodyNew = "{\"student\":{ \"userId\": \""+ userId +"\", \"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"co\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"graduationDate\": \"2019-06-11\", \"status\": \"Resident\",\"program\": \"undergrad\"}, \"textBookId\":\""+ ID +"\", \"name\": \"Java-update\", \"description\": \"this is java book\", \"isbn\": \""+ isbn +"\", \"unitPrice\": 223.4, \"department\": \"CIS\" }";
			System.out.print("\nText book new body  : "+ bodyNew + "\n\n");
		
			response = given().spec(restClient.getRecSpec())
				   	   .basePath("/books").body(bodyNew).contentType(ContentType.JSON)
				   	   .when().post("/textbooks/update/" + ID)
				   	   .then().assertThat().statusCode(200).extract().response();
			
			System.out.print("\nText books after update  : "+ response.asString() + "\n\n");

		}
		else {
			System.out.print("\n\nISBN duplication error\n\n");
		}			
	}
	
	@Test
	@DisplayName("Update textbook by removing student")
	public void updateTextBookRemoveStudent() {				
		String isbn = randomIsbnGenerator();
		String numForMail = randomIsbnGenerator();
		
		if((!isbnCheck(isbn)) && (!emailCheckStudent(numForMail))) {
			String body = "{ \"department\": \"ECE\", \"name\": \"Java\", \"description\": \"this is java book\", \"isbn\":" + isbn +", \"unitPrice\": 223.4 }";
			
			response = given().spec(restClient.getRecSpec())
					   .basePath("/books").body(body)
					   .when().post("/textbooks/add")
					   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(response.asString());
			ID = js.get("textBookId");		
			System.out.print("\nText book with id  : "+ ID + "\n\n");
			System.out.print("\nText book details  : "+ response.asString() + "\n\n");
			
			isbn = js.get("isbn");
			System.out.print("\nText book ISBN  : "+ isbn + "\n\n");																															
			
			String userStudent = "{\"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"asd\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"graduationDate\": \"2019-06-11\", \"status\": \"Resident\",\"program\": \"undergrad\"}";
			System.out.print("\nFaculty  : "+ userStudent + "\n\n");
			
			Response responseFaculty = given().spec(restClient.getRecSpec())
									   .basePath("/student").body(userStudent)
									   .when().post("/add")
									   .then().assertThat().statusCode(200).extract().response();
			
			js = new JsonPath(responseFaculty.asString());
			String userId = js.get("userId");	
			String bodyNew = "{\"student\":{ \"userId\": \""+ userId +"\", \"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"co\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"graduationDate\": \"2019-06-11\", \"status\": \"Resident\",\"program\": \"undergrad\"}, \"textBookId\":\""+ ID +"\", \"name\": \"Java-update\", \"description\": \"this is java book\", \"isbn\": \""+ isbn +"\", \"unitPrice\": 223.4, \"department\": \"CIS\" }";
			System.out.print("\nText book new body  : "+ bodyNew + "\n\n");
		
			response = given().spec(restClient.getRecSpec())
				   	   .basePath("/books").body(bodyNew).contentType(ContentType.JSON)
				   	   .when().post("/textbooks/update/" + ID)
				   	   .then().assertThat().statusCode(200).extract().response();
			
			String bodyFacultyNull = "{\"student\":" + null +", \"textBookId\":\""+ ID +"\", \"name\": \"Java-update\", \"description\": \"this is java book\", \"isbn\": \""+ isbn +"\", \"unitPrice\": 223.4, \"department\": \"CIS\" }";
			System.out.print("\nText book new body  : "+ bodyFacultyNull + "\n\n");
			
			response = given().spec(restClient.getRecSpec())
				   	   .basePath("/books").body(bodyFacultyNull).contentType(ContentType.JSON)
				   	   .when().post("/textbooks/update/" + ID)
				   	   .then().assertThat().statusCode(200).extract().response();
			
			System.out.print("\nText books after update  : "+ response.asString() + "\n\n");

		}
		else {
			System.out.print("\n\nISBN duplication error\n\n");
		}			
	}
	
	public boolean isbnCheck(String isbn) {
		boolean isBookExist = false;
		Response postResponse = gettingResponse();

		List<String> isbnList = postResponse.jsonPath().getList("isbn");
		
		long isThere = isbnList.stream().filter(e->e.equalsIgnoreCase(isbn)).count();
		
		if(isThere>0) isBookExist = true;
		
		return isBookExist;
	}
	
	public boolean emailCheckFaculty(String numForMail) {
		boolean isEmailExist = false;
		Response postResponse = gettingFacultyResponse();

		List<String> emailList = postResponse.jsonPath().getList("email");
		
		long isThere = emailList.stream().filter(e->e.contains(numForMail)).count();
		
		if(isThere>0) isEmailExist = true;
		
		return isEmailExist;
	}
	
	public boolean emailCheckStudent(String numForMail) {
		boolean isEmailExist = false;
		Response postResponse = gettingStudentResponse();

		List<String> emailList = postResponse.jsonPath().getList("email");
		
		long isThere = emailList.stream().filter(e->e.contains(numForMail)).count();
		
		if(isThere>0) isEmailExist = true;
		
		return isEmailExist;
	}
	
	public String randomIsbnGenerator() {
		String isbn = "";
		long aNumber = 0; 
		aNumber = (long)((Math.random() * 900000000)+1000000000); 
		return isbn+aNumber;
	}
	
	public Response gettingResponse() {
		Response response = given().spec(restClient.getRecSpec())
				   				.basePath("/books")
				   				.get("/textbooks/all")
				   				.then().assertThat().statusCode(200).extract().response();
		return response;
	}
	
	public Response gettingFacultyResponse() {
		Response response = given().spec(restClient.getRecSpec())
				   				.basePath("/faculty")
				   				.get("/all")
				   				.then().assertThat().statusCode(200).extract().response();
		return response;
	}
	
	public Response gettingStudentResponse() {
		Response response = given().spec(restClient.getRecSpec())
				   				.basePath("/student")
				   				.get("/all")
				   				.then().assertThat().statusCode(200).extract().response();
		return response;
	}

}