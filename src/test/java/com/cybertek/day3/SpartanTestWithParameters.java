package com.cybertek.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvFileSource;

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

    @DisplayName("GET request to /api/spartan{id}")
    @Test
    public void test2(){
        Response response = given()
                .accept(ContentType.JSON).and().pathParam("id", 500).
                when().get("/api/spartan/{id}");

        assertEquals(404,response.statusCode());
        assertEquals("application/json", response.getContentType());
        assertEquals(response.body().asString().contains("Not Found"), true);

    }

    @DisplayName("GET request to /api/spartans/search")
    @Test
    public void test3() {

        Response response = given().log().all().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when().get("/api/spartans/search");
        assertEquals(200,response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }
}