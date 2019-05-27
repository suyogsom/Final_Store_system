package com.bookstore.it;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TextBooksIT extends BaseTest {
	
	Response response;
	JsonPath js;
	Integer ID;

	@Test
	@DisplayName("Getting all textbooks")
	public void getAllBooks() {	
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books")
				   .get("/textbooks/")
				   .then().assertThat().statusCode(200).extract().response();
		System.out.print("\nAll text books : " + response.asString() + "\n");
	}
	
	@Test
	@Disabled
	@DisplayName("Getting a textbook with ID")
	public void getTextBookByName() {
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books")
				   .get("/textbooks/1")
				   .then().assertThat().statusCode(200).extract().response();
		System.out.print("\nText books with name java : " + response.asString() + "\n");
	}
	
	@Test
	@DisplayName("Adding a textbook")
	public void addTextBook() {		
		String body = "{ \"department\": \"CS-2\", \"name\": \"Java trial\", \"description\": \"this is java book\", \"isbn\": \"23134\", \"unitPrice\": 223.4, \"id\": \"1005\" }";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(body)
				   .when().post("/textbooks/add");
		
		Response postResponse = given().spec(restClient.getRecSpec())
				   				.basePath("/books")
				   				.get("/textbooks/")
				   				.then().assertThat().statusCode(200).extract().response();

		js = new JsonPath(postResponse.asString());
		ID = js.get("[2].id");
		
		System.out.println("\nID is " + ID);
		System.out.println("\nBook added is " + js.get("[2]") + "\n");
	}
	
	@Test
	@DisplayName("Updating a textbook")
	public void updateTextBook() {				
		String body = "{ \"department\": \"CS-23\", \"name\": \"Java trial\", \"description\": \"this is java book\", \"isbn\": \"23134\", \"unitPrice\": 223.4, \"id\": \"1005\" }";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(body)
				   .when().post("/textbooks/add");
		
		Response postResponse = given().spec(restClient.getRecSpec())
				   				.basePath("/books")
				   				.get("/textbooks/")
				   				.then().assertThat().statusCode(200).extract().response();

		js = new JsonPath(postResponse.asString());
		ID = js.get("[2].id");
		
		String bodyUpdate = "{ \"department\": \"CS-23-01\", \"name\": \"Java update\", \"description\": \"this is java book update\", \"isbn\": \"23123\", \"unitPrice\": 231123.3, \"id\": \"1006\" }";
		
		System.out.println("\nID is " + ID);
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(bodyUpdate)
				   .when().post("/textbooks/update/"+ ID);		
		
		Response postResponseUpdate = given().spec(restClient.getRecSpec())
				   					  .basePath("/books")
				   					  .get("/textbooks/");
		
		JsonPath jsNew = new JsonPath(postResponseUpdate.asString());
		
		System.out.print("\nAfter update" + jsNew.get("[2]") + "\n");
	}
	
	@Test
	@DisplayName("Deleting a textbook")
	public void deleteTextBook() {
		String body = "{ \"department\": \"CS-23\", \"name\": \"Java trial\", \"description\": \"this is java book\", \"isbn\": \"23134\", \"unitPrice\": 223.4, \"id\": \"1007\" }";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(body)
				   .when().post("/textbooks/add")
				   .then().assertThat().statusCode(200).extract().response();
		
		Response postResponse = given().spec(restClient.getRecSpec())
   								.basePath("/books")
   								.get("/textbooks/")
   								.then().assertThat().statusCode(200).extract().response();

		js = new JsonPath(postResponse.asString());
		ID = js.get("[2].id");	
		
		System.out.print("\nBefore delete book of ID : " + ID + "\t" + postResponse.asString() + "\n");
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(body)
				   .when().post("/textbooks/delete/"+ ID);
		
		
		Response responseAfterDelete = given().spec(restClient.getRecSpec())
				   					  .basePath("/books")
				   					  .get("/textbooks/");
		
		System.out.print("\nAfter delete book of ID : " + ID + "\t" + responseAfterDelete.asString() + "\n");
		
	}
}
