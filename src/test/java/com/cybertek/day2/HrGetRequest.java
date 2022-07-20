package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequest {

    //BeforeAll is an annotation equals to @BeforeClass in TestNg, we use static method name
    @BeforeAll
    public static void init() {
        // save baseurl inside this variable so that we don't need to type each https method
         baseURI = "http://3.80.210.194:1000/ords/hr";

    }

    @DisplayName(("GET request to / regions"))
    @Test
    public void test1() {
        Response response = get("/regions");
        System.out.println(response.statusCode());
    }

    @DisplayName("GET request to /regions/2")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .when()
                .get("/regions/2");
        assertEquals(200, response.statusCode());

        //verify content type
        assertEquals("application/json", response.contentType());

        response.prettyPrint();

        //verify body contains Americas

      //  Assertions.assertEquals(response.body().asString().contains("Americas"), true);
         Assertions.assertTrue(response.body().asString().contains("Americas"));

    }


}
