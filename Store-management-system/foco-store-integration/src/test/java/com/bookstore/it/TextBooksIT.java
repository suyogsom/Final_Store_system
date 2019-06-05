package com.bookstore.it;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.List;

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
	@DisplayName("Getting a textbook with ID")
	@Disabled
	public void getTextBookByName() {
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books")
				   .get("/textbooks/1")
				   .then().assertThat().statusCode(200).extract().response();
		
		System.out.print("\nText books with id 1 : " + response.asString() + "\n");
	}
	
	@Test
	@DisplayName("Adding a textbook with out user")
	@Disabled
	public void addTextBookNoUser() {		
		String body = "{ \"department\": \"ECE\", \"name\": \"Java intsdfsfro\", \"description\": \"this is java book\", \"isbn\": \"23134\", \"unitPrice\": 223.4 }";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(body)
				   .when().post("/textbooks/add");
		
		Response postResponse = given().spec(restClient.getRecSpec())
				   				.basePath("/books")
				   				.get("/textbooks/" )
				   				.then().assertThat().statusCode(200).extract().response();

		List<Integer> idList = postResponse.jsonPath().getList("id");
		
		Integer idAdded = 0;
		
		for(Integer id : idList) {
			idAdded = id;			
		}
		
		System.out.println("\nID added is : " + idAdded);
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books")
				   .when().get("/textbooks/" + idAdded);
		
		System.out.println("\nBook added is : " + response.asString());
		
		js = new JsonPath(response.asString());
		String department = js.get("department");

		assertThat(department,containsString("ECE"));
	}
	
	@Test
	@DisplayName("Updating a textbook with out user")
	@Disabled
	public void updateTextBookNoUser() {				
		String body = "{ \"department\": \"CS-23 update trial\", \"name\": \"Java trial\", \"description\": \"this is java book\", \"isbn\": \"23134\", \"unitPrice\": 223.4 }";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(body)
				   .when().post("/textbooks/add");
		
		Response postResponse = given().spec(restClient.getRecSpec())
				   				.basePath("/books")
				   				.get("/textbooks/")
				   				.then().assertThat().statusCode(200).extract().response();
		
		List<Integer> idList = postResponse.jsonPath().getList("id");
		
		Integer idToUpdate = 0;
		
		for(Integer id : idList) {
			idToUpdate = id;			
		}
		
		System.out.print("\n\nID to update is : "+idToUpdate+"\n\n");		
		
		String bodyUpdate = "{ \"department\": \"CS-23-01 update done\", \"name\": \"Java update\", \"description\": \"this is java book update\", \"isbn\": \"23123\", \"unitPrice\": 231123.3, \"id\":" + idToUpdate + " }";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(bodyUpdate)
				   .when().post("/textbooks/update/"+ idToUpdate);		
		
		Response postResponseUpdate = given().spec(restClient.getRecSpec())
				   					  .basePath("/books")
				   					  .get("/textbooks/" + idToUpdate);
		
		System.out.print("\nAfter update" + postResponseUpdate.asString());
		
		js = new JsonPath(postResponseUpdate.asString());
		String department = js.get("department");

		assertThat(department,containsString("CS-23-01 update done"));
	}
	
	@Test
	@DisplayName("Updating a textbook with user")
	@Disabled
	public void updateTextBookWithUser() {				
		String body = "{ \"department\": \"CS-23 update trial\", \"name\": \"Java trial\", \"description\": \"this is java book\", \"isbn\": \"23134\", \"unitPrice\": 223.4 }";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(body)
				   .when().post("/textbooks/add");
		
		Response postResponse = given().spec(restClient.getRecSpec())
				   				.basePath("/books")
				   				.get("/textbooks/")
				   				.then().assertThat().statusCode(200).extract().response();
		
		List<Integer> idList = postResponse.jsonPath().getList("id");
		
		Integer idToUpdate = 0;
		
		for(Integer id : idList) {
			idToUpdate = id;			
		}
		
		System.out.print("\n\nID to update is : "+idToUpdate+"\n\n");		
		
		String bodyUpdate = "{ \"department\": \"CS-23-01 update done\", \"name\": \"Java update\", \"description\": \"this is java book update\", \"isbn\": \"23123\", \"unitPrice\": 231123.3, \"id\":" + idToUpdate + " }";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(bodyUpdate)
				   .when().post("/textbooks/update/"+ idToUpdate);		
		
		Response postResponseUpdate = given().spec(restClient.getRecSpec())
				   					  .basePath("/books")
				   					  .get("/textbooks/" + idToUpdate);
		
		System.out.print("\nAfter update" + postResponseUpdate.asString());
		
		js = new JsonPath(postResponseUpdate.asString());
		String department = js.get("department");

		assertThat(department,containsString("CS-23-01 update done"));
	}
	
	@Test
	@DisplayName("Deleting a textbook")
	@Disabled
	public void deleteTextBook() {
		String body = "{ \"department\": \"CS-23\", \"name\": \"Java trial\", \"description\": \"this is java book\", \"isbn\": \"23134\", \"unitPrice\": 223.4 }";
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(body)
				   .when().post("/textbooks/add")
				   .then().assertThat().statusCode(200).extract().response();
		
		Response postResponse = given().spec(restClient.getRecSpec())
   								.basePath("/books")
   								.get("/textbooks/")
   								.then().assertThat().statusCode(200).extract().response();

		List<Integer> idList = postResponse.jsonPath().getList("id");
		
		Integer idToDelete = 0;
		
		for(Integer id : idList) {
			idToDelete = id;			
		}
		
		System.out.print("\n\nID to delete is : "+idToDelete+"\n\n");
		
		response = given().spec(restClient.getRecSpec())
				   .basePath("/books").body(body)
				   .when().post("/textbooks/delete/"+ idToDelete);
		
		
		Response responseAfterDelete = given().spec(restClient.getRecSpec())
				   					  .basePath("/books")
				   					  .get("/textbooks/");
		
		System.out.print("\nAfter delete book of ID : " + idToDelete + "\t" + responseAfterDelete.asString() + "\n");
		
	}
}
