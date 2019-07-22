package com.example.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Courses extends FunctionalTest{
	
	@Test
	public void basicPingTest() {
		given().when().get("/courses").then().statusCode(200);
	}
	
	@Test
	public void verifyCourseCount(){
		
		given().when().get("/courses").then()
		.assertThat().
		
		body("page.totalElements", equalTo(3));
		
		
	}
	
	
	//@Test
	public void addCourse( String courseName, String courseCode ) {
		
		
		
		RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("courseName", courseName); // Cast
		requestParams.put("courseCode", courseCode);
		
		request.body(requestParams.toString());
		Response response = request.post("/courses");
		
		response.then().assertThat().statusCode(201);
		
		 
		 
	}
	
	
	
	@Test
	public void verifyNewCourse(){
		
		addCourse("Grade5", "Code5");
		
	
	
		given().
		      
			when().
			        get("/courses").
			        then().assertThat().
			body("_embedded.courses.findAll {it}.courseName",hasItems("Grade2"));
	
		    
		     
	/*List<Map<String,String>> content = new JsonPath(courses).get("_embedded.courses");
	
		//System.out.println(content);
		
		content.forEach(con->{
			con.entrySet().forEach(key->{
				System.out.println( key);
				
			});
		});
		
		*/
		
	}
	
	
	@Test
	
	public void deleteCourse() {
		
		addCourse("Grade7", "Code7");
		
	given().param("size","50").param("page", "0").
	      
		when().
		        get("/courses").
		        then().assertThat().
		body("_embedded.courses.findAll {it}.courseName",hasItems("Grade7"));

	/*	String json=given().param("size",50).param("page", 0).
			      
				when().
				        get("/courses").
				     asString();
		/*given().
		
		when().delete("/courses/5").then().assertThat().statusCode(200);

	    
		System.out.println(json);
		*/
		
		
		
		
		
	}
	
	
}