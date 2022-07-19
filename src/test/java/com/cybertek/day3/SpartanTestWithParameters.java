package com.cybertek.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestWithParameters {

    //BeforeAll is an annotation equals to @BeforeClass in TestNg, we use static method name
    @BeforeAll
    public static void init() {
        // save baseurl inside this variable so that we don't need to type each https method
        baseURI = "http://3.80.210.194:8000";

    }

    @DisplayName("GET request to /api/spartans{id} with ID 5")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",5)
                .when().get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        // verify Blythe in the json payload/body
        assertTrue(response.body().asString().contains("Blythe"));

    }
}