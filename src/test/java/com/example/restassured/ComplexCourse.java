package com.example.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ComplexCourse extends FunctionalTest{
	
	@Test
	public void basicPingTest() {
		given().when().get("courses").then().statusCode(200);
	}
	
	@Test
public void addCourse(  ) {
		
		
		
		RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Course 2"); // Cast
		requestParams.put("code", "Code_2");
		
		request.body(requestParams.toString());
		Response response = request.post("/courses");
		
		response.then().assertThat().statusCode(201);
		
		 
		 
	}

@Test
public void addChapter() {
	
	RequestSpecification request = RestAssured.given().contentType(ContentType.JSON);
	
	JSONObject requestParams = new JSONObject();
	requestParams.put("name", "Chapter 2"); // Cast
	requestParams.put("code", "Code_2");
	requestParams.put("course", new JSONObject());
	
	request.body(requestParams.toString());
	Response response = request.post("/chapters");
	
	response.then().assertThat().statusCode(201);
	
}




@Test

public void attachChapter() {
	String chapters=given().
    
when().
    get("/chapters").
then().
    contentType(ContentType.HTML).
    body("_embedded.chapters.findAll {it}.name",hasItems("Chapter 2")).
extract().
    path("_embedded.chapters.find {it.name=='Chapter 2'}._links.course.href");
	
	System.out.println(chapters);
	
	
	
	//System.out.println(chapterCourseURL);
	
	String courses=
	given().
    
	when().
	    get("/courses").
	then().
	    contentType(ContentType.JSON).
	    body("_embedded.courses.findAll {it}.name",hasItems("Course 2")).
	extract().
	    path("_embedded.courses.find {it.name=='Course 2'}._links.course.href");
	
	System.out.println(courses);
	
	RequestSpecification request = RestAssured.given().contentType("text/uri-list");
	
	
	
	request.body(courses);
	Response response = request.put(chapters.replace("http://localhost:8080", ""));
	
	response.then().assertThat().statusCode(204);
	
	
	
}


@Test

public void deleteCourse() {
	
	given().param("name", "Course 2)").
	
	when().
    get("/courses").
	
then().
    contentType(ContentType.JSON).assertThat().statusCode(200);
	
	System.out.println("Success");
	
	given().
	
	when().delete("/courses/5").then().assertThat().statusCode(200);
    
    
    
	
	
}










}