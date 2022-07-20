package com.cybertek.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithParameters {

    @BeforeAll
    public static void init() {

        //save baseURL inside this variable so that we do not need to type each http method
        baseURI = "http://3.80.210.194:1000/ords/hr";

    }

    @DisplayName("Get request to /countries with Query Param")
    @Test
    public void test1() {
        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}").
                    log().all()
                .when()
                .get("/countries");


       assertEquals(200, response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));

       response.prettyPrint();
    }



    @DisplayName("Get request to /employees and only employees who works as a IT_PROG")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON).log().all()
                .queryParams("q","{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees") ;

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();


    }




}
