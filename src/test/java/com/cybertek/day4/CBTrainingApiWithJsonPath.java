package com.cybertek.day4;

import io.restassured.http.ContentType;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CBTrainingApiWithJsonPath {
    @BeforeAll
    public static void init() {

        //save baseURL inside this variable so that we do not need to type each http method
        baseURI = "http://api.cybertektraining.com";

    }
    @DisplayName("GET request to individual students")
    @Test
    public void test1() {
                        // send a get req to student id 32881 as a get parameter
        Response response = given().accept(ContentType.JSON)
                .pathParam("id",32881)
                .when().get("/student/{id}\n");

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());

        assertEquals("gzip",response.header("Content-Encoding"));
        assertTrue(response.headers().hasHeaderWithName("Date"));

        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.getString("students[0].firstName");
        assertEquals("Vera", firstName);

        String emailAddress = jsonPath.getString("students[0].contact.emailAddress");
        assertEquals("aaa@gmail.com",emailAddress);

        String state = jsonPath.getString("students[0].company.address.state");
        assertEquals("IL",state);
    }
}

// TASK:
//  send a get request to student id 23401 as a get parameter
//  verify status code=200 /content type= application/json;charset=UTF-8 / Content-Encoding = gzip
//  verify Date header exist
//  assert that
        /*
            firstName Vera
            batch 14
            section 12
            emailAddress aaa@gmail.com
            state IL
            zipCode 60606
            using JsonPath
        */
