package com.example.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostsTest extends FunctionalTest{
	
	@Test
	public void basicPingTest() {
		given().when().get("/posts").then().statusCode(200);
	}
	
	@Test
	public void verifySpecificTitle() {
		given().when().get("/posts").then()
		.assertThat().
		
		body("findAll {it}.title", hasItems("Anushga"));
		
	}
	
	@Test
	public void verifyCommentsForPosts() {
		
		 
	   	isPostExistsTest(3);
				
				given().param("postId", 3).
			      
				when().
				        get("/comments").
				then().
				        contentType(ContentType.JSON).assertThat().
				       body("findAll {it}.name", hasItem("modi ut eos dolores illum nam dolor"));
				
		
						
		
	
		
		
	}
	
	public void isPostExistsTest(int postId) {
		given().
	      
		when().
		        get("/posts").
		then().
		        contentType(ContentType.JSON).assertThat().
		       body("findAll {it}.id", hasItem(postId));
	}
	
	@Test
	public void newTest() {
		System.out.println("testing");
		System.out.println("Testing one more time");
		System.out.println("Testing ad more time");
		System.out.println("Testing ad 4th line time");
		System.out.println("Testing 5th time");
		
	}
}
