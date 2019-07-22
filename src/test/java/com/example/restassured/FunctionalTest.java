package com.example.restassured;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class FunctionalTest {
	@BeforeClass
    public static void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
          RestAssured.port = Integer.valueOf(8080);
        }
        else{
        //    RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        RestAssured.baseURI = baseHost;
        

    }
}
