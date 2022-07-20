package com.cybertek.day2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeGetTest {

    @BeforeAll
    public static void init() {
        //save baseUrl inside this varible so that  we do not need to type each http method.
        baseURI = "http://3.80.210.194:8000";

    }

    @DisplayName(("Get accept type application/xml"))
    @Test
    public void test1() {


        Response response = given().log().all().accept(ContentType.XML)
                .when()
                .get("/api/spartans/10");
        assertEquals(406, response.statusCode());

        //verify response Content Type
        assertEquals("application/xml;charset=UTF-8", response.contentType());


    }
}
