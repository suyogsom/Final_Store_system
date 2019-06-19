package com.bookstore.it;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FacultyIT extends BaseTest {
	
	Response response;
	JsonPath js;
	String ID;

	@Test
	@DisplayName("Getting all users - faculty")
	public void getAllFacultyUser() {	
		response = gettingResponse();
		System.out.print("\nAll faculty members : " + response.asString() + "\n");
	}
	
	@Test
	@DisplayName("Getting a faculty with ID")
	public void getFacultyByID() {
		String numForMail = randomNumberGenerator();
		
		if(!emailCheck(numForMail)) {
		String userFaculty = "{\"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"asd\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"tenuarDate\": \"2019-06-11\", \"program\": \"undergrad\"}";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/faculty").body(userFaculty)
				   .when().post("/add")
				   .then().assertThat().statusCode(200).extract().response();
		
		js = new JsonPath(response.asString());
		ID = js.get("userId");		
		System.out.print("\nFaculty with id  : "+ ID + "\n\n");
		System.out.print("\nFaculty details  : "+ response.asString() + "\n\n");
		}
		else {
			System.out.print("\n\nEmail duplication error\n\n");
		}		
	}
	
	@Test
	@DisplayName("Add faculty")
	public void addFaculty() {				
		String numForMail = randomNumberGenerator();
		
		if(!emailCheck(numForMail)) {
		String userFaculty = "{\"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"asd\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"tenuarDate\": \"2019-06-11\", \"program\": \"undergrad\"}";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/faculty").body(userFaculty)
				   .when().post("/add")
				   .then().assertThat().statusCode(200).extract().response();
		
		js = new JsonPath(response.asString());
		ID = js.get("userId");		
		System.out.print("\nFaculty with id  : "+ ID + "\n\n");
		System.out.print("\nFaculty details  : "+ response.asString() + "\n\n");
		}
		else {
			System.out.print("\n\nEmail duplication error\n\n");
		}			
	}
	
	@Test
	@DisplayName("Update faculty")
	public void updateFaculty() {						
		String numForMail = randomNumberGenerator();
		
		if(!emailCheck(numForMail)) {
		String userFaculty = "{\"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"asd\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"tenuarDate\": \"2019-06-11\", \"program\": \"undergrad\"}";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/faculty").body(userFaculty)
				   .when().post("/add")
				   .then().assertThat().statusCode(200).extract().response();
		
		js = new JsonPath(response.asString());
		ID = js.get("userId");		
		System.out.print("\nFaculty with id  : "+ ID + "\n\n");
		System.out.print("\nFaculty details  : "+ response.asString() + "\n\n");

		String bodyNew = "{\"userId\": \""+ ID +"\", \"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"co\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"tenuarDate\": \"2019-06-11\", \"program\": \"undergrad\"}";
	
		response = given().spec(restClient.getRecSpec())
			   	   .basePath("/faculty").body(bodyNew)
			   	   .when().post("/update/" + ID)
			   	   .then().assertThat().statusCode(200).extract().response();
		
		System.out.print("\nFaculty after update  : "+ response.asString() + "\n\n");
		}
		else {
			System.out.print("\n\nEmail duplication error\n\n");
		}
	}
	
	@Test
	@DisplayName("Delete faculty")
	public void deleteFaculty() {				
		String numForMail = randomNumberGenerator();
		
		if(!emailCheck(numForMail)) {
		String userFaculty = "{\"department\": \"ECE\", \"phoneNumber\": \"9705819659\", \"email\": \"anup.patil"+ numForMail +"@gmail.com\", \"gender\": \"MALE\", \"name\": { \"firstName\": \"anup\", \"middleName\": \"dinesh\", \"lastName\": \"patil\" }, \"address\": { \"apartment\": \"d-203\", \"street\": \"1113 west plum\", \"city\": \"fort collins\", \"state\": \"asd\", \"zipcode\": \"80521\", \"country\": \"USA\" }, \"joiningDate\": \"2019-06-10\", \"tenuarDate\": \"2019-06-11\", \"program\": \"undergrad\"}";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/faculty").body(userFaculty)
				   .when().post("/add")
				   .then().assertThat().statusCode(200).extract().response();
		
		js = new JsonPath(response.asString());
		ID = js.get("userId");		
		System.out.print("\nFaculty with id  : "+ ID + "\n\n");
		System.out.print("\nFaculty details  : "+ response.asString() + "\n\n");
	
		response = given().spec(restClient.getRecSpec())
			   	   .basePath("/faculty")
			   	   .when().post("/delete/" + ID)
			   	   .then().assertThat().statusCode(200).extract().response();
		}
		else {
			System.out.print("\n\nEmail duplication error\n\n");
		}		
	}
	
	public boolean emailCheck(String numForMail) {
		boolean isEmailExist = false;
		Response postResponse = gettingResponse();

		List<String> emailList = postResponse.jsonPath().getList("email");
		
		long isThere = emailList.stream().filter(e->e.contains(numForMail)).count();
		
		if(isThere>0) isEmailExist = true;
		
		return isEmailExist;
	}
	
	public String randomNumberGenerator() {
		String number = "";
		long aNumber = 0; 
		aNumber = (long)((Math.random() * 900000000)+1000000000); 
		return number+aNumber;
	}
	
	public Response gettingResponse() {
		Response response = given().spec(restClient.getRecSpec())
				   				.basePath("/faculty")
				   				.get("/all")
				   				.then().assertThat().statusCode(200).extract().response();
		return response;
	}

}